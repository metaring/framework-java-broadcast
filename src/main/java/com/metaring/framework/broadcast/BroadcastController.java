/**
 *    Copyright 2019 MetaRing s.r.l.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.metaring.framework.broadcast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.metaring.framework.GeneratedCoreType;

public final class BroadcastController {

    public static final String BROADCAST_KEY = "broadcast.key";

    private static final Map<String, Map<String, Consumer<String>>> DATABASE = new HashMap<>();

    private static final Map<String, BiConsumer<String, GeneratedCoreType>> GLOBAL_HOOKS = new HashMap<>();

    private static final CompletableFuture<Void> END = CompletableFuture.completedFuture(null);

    public static final CompletableFuture<Void> callback(SingleCallback singleCallback, Executor asyncExecutor) {
        String payload = singleCallback.getData().toJson();
        if(!DATABASE.containsKey(singleCallback.getAddress())) {
            return END;
        }
        callHooks("singleCallback", singleCallback, asyncExecutor);
        return CompletableFuture.allOf(
                DATABASE
                .get(singleCallback.getAddress())
                .values()
                .stream()
                .map(it -> CompletableFuture.runAsync(() -> it.accept(payload), asyncExecutor))
                .toArray(CompletableFuture[]::new)
        );
    }

    public static final CompletableFuture<Void> callback(MultipleCallback multipleCallback, Executor asyncExecutor) {
        final List<String> addresses = new ArrayList<>(multipleCallback.getAddresses());
        final CompletableFuture<Void> run = new CompletableFuture<Void>();
        final Runnable[] action = new Runnable[1];
        action[0] = () -> {
            if(addresses.isEmpty()) {
                run.complete(null);
                return;
            }
            callback(SingleCallback.create(addresses.remove(0), multipleCallback.getData()), asyncExecutor).thenRunAsync(action[0], asyncExecutor);
        };
        callHooks("multipleCallback", multipleCallback, asyncExecutor);
        action[0].run();
        return run;
    }

    public static final CompletableFuture<Void> callback(Event event, Executor asyncExecutor) {
        String payload = event.toJson();
        callHooks("event", event, asyncExecutor);
        return CompletableFuture.allOf(
                DATABASE
                .values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .distinct()
                .map(it -> CompletableFuture.runAsync(() -> it.accept(payload), asyncExecutor))
                .toArray(CompletableFuture[]::new)
        );
    }

    public static final CompletableFuture<Void> subscribe(BroadcastAddressData addressData, Executor asyncExecutor) {
        String key = addressData.getAddress();
        String topic = addressData.getNewAddress();
        if(!DATABASE.containsKey(topic)) {
            DATABASE.put(topic, new HashMap<>());
        }
        DATABASE.get(topic).put(key, DATABASE.get(key).get(key));
        return END;
    }

    public static final void unsubscribe(BroadcastAddressData addressData) {
        try {
            String topic = addressData.getNewAddress();
            Map<String, Consumer<String>> map = DATABASE.get(topic);
            map.remove(addressData.getAddress());
            if(map.isEmpty()) {
                DATABASE.remove(topic);
            }
        } catch(Exception e) {
        }
    }

    public static final boolean isSubscribed(BroadcastAddressData addressData) {
        try {
            return DATABASE.get(addressData.getNewAddress()).containsKey(addressData.getAddress());
        } catch(Exception e) {
            return false;
        }
    }

    @SuppressWarnings("serial")
    public static final void register(String key, Consumer<String> messageHandler) {
        DATABASE.put(key, new HashMap<String, Consumer<String>>() {
            {
                put(key, messageHandler);
            }
        });
    }

    public static final void unregister(String key) {
        try {
            DATABASE.remove(key);
            for(String topic : new HashSet<>(DATABASE.keySet())) {
                Map<String, Consumer<String>> map = DATABASE.get(topic);
                map.remove(key);
                if(map.isEmpty()) {
                    DATABASE.remove(topic);
                }
            }
        } catch(Exception e) {
        }
    }

    public static final String register(BiConsumer<String, GeneratedCoreType> messageHandler) {
        if(messageHandler == null) {
            return null;
        }
        String key = (("" + System.currentTimeMillis() * Math.random()) + messageHandler.toString());
        GLOBAL_HOOKS.put(key, messageHandler);
        return key;
    }

    private static final void callHooks(String type, GeneratedCoreType element, Executor asyncExecutor) {
        CompletableFuture.allOf(GLOBAL_HOOKS.values().stream()
                .map(it -> CompletableFuture.runAsync(() -> it.accept(type, element), asyncExecutor))
                .toArray(CompletableFuture[]::new));
    }
}