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

import static java.util.concurrent.CompletableFuture.completedFuture;

import java.util.concurrent.CompletableFuture;

import com.metaring.framework.Core;
import com.metaring.framework.SysKB;
import com.metaring.framework.type.DataRepresentation;

@SuppressWarnings("unchecked")
public final class BroadcastControllerManager {

    public static final CompletableFuture<BroadcastController> INSTANCE;

    static {
        SysKB sysKB = Core.SYSKB;
        if (sysKB == null) {
            INSTANCE = completedFuture(null);
        } else {
            if (!sysKB.hasProperty(BroadcastController.CFG_BROADCAST)) {
                INSTANCE = completedFuture(null);
            } else {
                DataRepresentation functionalityDataRepresentation = sysKB.get(BroadcastController.CFG_BROADCAST);
                if (!functionalityDataRepresentation.hasProperty(BroadcastController.CFG_CONTROLLER)) {
                    INSTANCE = null;
                } else {
                    String className = functionalityDataRepresentation.getText(BroadcastController.CFG_CONTROLLER);
                    Class<? extends BroadcastController> clazz = null;
                    try {
                        clazz = (Class<? extends BroadcastController>) Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        throw new IllegalArgumentException("An error occurred while creating Broadcast Controller class " + className, e);
                    }
                    try {
                        CompletableFuture<BroadcastController> future = new CompletableFuture<>();
                        clazz.newInstance().init(sysKB).whenCompleteAsync((result, error) -> {
                            if (error != null) {
                                future.completeExceptionally(error);
                            } else {
                                future.complete(result);
                            }
                        });
                        INSTANCE = future;
                    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
                        throw new IllegalArgumentException("An error occurred while trying to access to Broadcast Controller init " + className, e);
                    }
                }
            }
        }
    }
}