package com.metaring.framework.broadcast;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;

final class DefaultBroadcastController implements BroadcastController {

    static final CompletableFuture<BroadcastController> INSTANCE = CompletableFuture.completedFuture(new DefaultBroadcastController());

    private static final Map<String, Map<String, BiConsumer<String, Exception>>> DATABASE = new HashMap<>();

    private static final CompletableFuture<Void> END = CompletableFuture.completedFuture(null);

    private DefaultBroadcastController() {
    }

    @Override
    public final CompletableFuture<Void> callback(SingleCallback singleCallback, Executor asyncExecutor) {
        String payload = singleCallback.getData().toJson();
        if(!DATABASE.containsKey(singleCallback.getAddress())) {
            return END;
        }
        return CompletableFuture.allOf(
                DATABASE
                .get(singleCallback.getAddress())
                .values()
                .stream()
                .map(it -> CompletableFuture.runAsync(() -> it.accept(payload, null), asyncExecutor))
                .toArray(CompletableFuture[]::new)
        );
    }

    @Override
    public final CompletableFuture<Void> callback(Event event, Executor asyncExecutor) {
        String payload = event.toJson();
        return CompletableFuture.allOf(
                DATABASE
                .values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .distinct()
                .map(it -> CompletableFuture.runAsync(() -> it.accept(payload, null), asyncExecutor))
                .toArray(CompletableFuture[]::new)
        );
    }

    @Override
    public final CompletableFuture<Void> subscribe(BroadcastAddressData addressData, Executor asyncExecutor) {
        String key = addressData.getAddress();
        String topic = addressData.getNewAddress();
        if(!DATABASE.containsKey(topic)) {
            DATABASE.put(topic, new HashMap<>());
        }
        DATABASE.get(topic).put(key, DATABASE.get(key).get(key));
        return END;
    }

    @Override
    public final CompletableFuture<Void> unsubscribe(BroadcastAddressData addressData, Executor asyncExecutor) {
        String key = addressData.getAddress();
        String topic = addressData.getNewAddress();
        try {
            Map<String, BiConsumer<String, Exception>> map = DATABASE.get(topic);
            map.remove(key);
            if(map.isEmpty()) {
                DATABASE.remove(topic);
            }
        } catch(Exception e) {
        }
        return END;
    }

    @Override
    public final CompletableFuture<Boolean> isSubscribed(BroadcastAddressData addressData, Executor asyncExecutor) {
        return CompletableFuture.completedFuture(DATABASE.get(addressData.getNewAddress()).containsKey(addressData.getAddress()));
    }

    @SuppressWarnings("serial")
    @Override
    public final BroadcastController register(String key, BiConsumer<String, Exception> messageHandler) {
        DATABASE.put(key, new HashMap<String, BiConsumer<String, Exception>>() {
            {
                put(key, messageHandler);
            }
        });
        return this;
    }

    @Override
    public final BroadcastController unregister(String key) {
        DATABASE.remove(key);
        return this;
    }
}