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

import java.util.concurrent.CompletableFuture;

import com.ea.async.Async;

import com.metaring.framework.SysKB;

public class CallbackFunctionalityImpl extends CallbackFunctionality {

    protected CallbackFunctionalityImpl(SysKB sysKB) {
        super(sysKB);
    }

    @Override
    protected CompletableFuture<Void> preConditionCheck(Event input) throws Exception {
        return end;
    }

    @Override
    protected CompletableFuture<Void> call(Event input) throws Exception {
        return Async.await(BroadcastControllerManager.INSTANCE).callback(SingleCallback.create(getContextData(BroadcastController.BROADCAST_KEY), input), EXECUTOR);
    }

    @Override
    protected CompletableFuture<Void> postConditionCheck(Event input) throws Exception {
        return end;
    }
}