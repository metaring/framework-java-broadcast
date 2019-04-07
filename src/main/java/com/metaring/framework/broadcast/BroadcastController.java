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
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;

import com.metaring.framework.SysKB;

public interface BroadcastController {

    public static final String BROADCAST_KEY = "broadcast.key";
    public static final String CFG_BROADCAST = "broadcast";
    public static final String CFG_CONTROLLER = "controller";

    static final String CALLBACK = "callback";
    static final String CALLBACK_TO = "callback/to/";
    static final String REGISTER = "callback/register/";
    static final String UNREGISTER = "callback/unregister/";
    static final String IS_SUBSCRIBED = "callback/isSubscribed/";

    default CompletableFuture<BroadcastController> init(SysKB sysKB) {
        return CompletableFuture.completedFuture(this);
    }

    CompletableFuture<Void> callback(SingleCallback singleCallback, Executor asyncExecutor);

    default CompletableFuture<Void> callback(MultipleCallback multipleCallback, Executor asyncExecutor) {
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
        action[0].run();
        return run;
    }

    CompletableFuture<Void> callback(Event event, Executor asyncExecutor);

    CompletableFuture<Void> subscribe(BroadcastAddressData addressData, Executor asyncExecutor);

    CompletableFuture<Void> unsubscribe(BroadcastAddressData addressData, Executor asyncExecutor);

    CompletableFuture<Boolean> isSubscribed(BroadcastAddressData addressData, Executor asyncExecutor);

    BroadcastController register(String key, BiConsumer<String, Exception> messageHandler);

    BroadcastController unregister(String key);
}