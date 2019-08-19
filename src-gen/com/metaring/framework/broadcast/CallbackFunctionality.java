package com.metaring.framework.broadcast;

import java.util.concurrent.CompletableFuture;
import com.metaring.framework.functionality.AbstractFunctionality;
import com.metaring.framework.functionality.GeneratedFunctionality;
import com.metaring.framework.functionality.FunctionalityInfo;
import com.metaring.framework.broadcast.Event;

abstract class CallbackFunctionality extends AbstractFunctionality implements GeneratedFunctionality {

    static final FunctionalityInfo INFO = FunctionalityInfo.create("com.metaring.framework.broadcast.callback", true, false, false, "com.metaring.framework.broadcast.Event", null);

    static final CallbackFunctionality INSTANCE = new CallbackFunctionalityImpl();

    protected CallbackFunctionality() {
        super(INFO, null);
    }

    @Override
    protected final CompletableFuture<Void> beforePreConditionCheck(Object input) throws Exception {
        CompletableFuture<Void> response = beforePreConditionCheck(input == null ? null : (Event) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> beforePreConditionCheck(Event input) throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Void> preConditionCheck(Object input) throws Exception {
        CompletableFuture<Void> response = preConditionCheck(input == null ? null : (Event) input);
        return response == null ? end : response;
    }

    protected abstract CompletableFuture<Void> preConditionCheck(Event input) throws Exception;

    @Override
    protected final CompletableFuture<Void> afterPreConditionCheck(Object input) throws Exception {
        CompletableFuture<Void> response = afterPreConditionCheck(input == null ? null : (Event) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> afterPreConditionCheck(Event input) throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Void> beforeCall(Object input) throws Exception {
        CompletableFuture<Void> response = beforeCall(input == null ? null : (Event) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> beforeCall(Event input) throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Object> call(Object input) throws Exception {
        CompletableFuture<Void> call = call((Event) input);
        if(call == null) {
            return end(null);
        }
        final CompletableFuture<Object> response = new CompletableFuture<>();
        call.whenCompleteAsync((result, error) -> {
            if(error != null) {
                response.completeExceptionally(error);
                return;
            }
            response.complete(result);
        }, EXECUTOR);
        return response;
    }

    protected abstract CompletableFuture<Void> call(Event input) throws Exception;

    @Override
    protected final CompletableFuture<Void> afterCall(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = afterCall(input == null ? null : (Event) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> afterCall(Event input) throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Void> beforePostConditionCheck(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = beforePostConditionCheck(input == null ? null : (Event) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> beforePostConditionCheck(Event input) throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Void> postConditionCheck(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = postConditionCheck(input == null ? null : (Event) input);
        return response == null ? end : response;
    }

    protected abstract CompletableFuture<Void> postConditionCheck(Event input) throws Exception;

    @Override
    protected final CompletableFuture<Void> afterPostConditionCheck(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = afterPostConditionCheck(input == null ? null : (Event) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> afterPostConditionCheck(Event input) throws Exception {
        return end;
    }

    @Override
    protected final Object getInputFromJsonWork(String inputJson) {
        return Event.fromJson(inputJson);
    }
}