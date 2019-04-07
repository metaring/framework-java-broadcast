package com.metaring.framework.broadcast;

import com.metaring.framework.SysKB;
import java.util.concurrent.CompletableFuture;
import com.metaring.framework.functionality.AbstractFunctionality;
import com.metaring.framework.functionality.GeneratedFunctionality;
import com.metaring.framework.broadcast.SingleCallback;

public abstract class ContactFunctionality extends AbstractFunctionality implements GeneratedFunctionality {

    protected ContactFunctionality(SysKB sysKB) {
        super(sysKB, BroadcastFunctionalitiesManager.CONTACT, null);
    }

    @Override
    protected final CompletableFuture<Void> beforePreConditionCheck(Object input) throws Exception {
        CompletableFuture<Void> response = beforePreConditionCheck(input == null ? null : (SingleCallback) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> beforePreConditionCheck(SingleCallback input) throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Void> preConditionCheck(Object input) throws Exception {
        CompletableFuture<Void> response = preConditionCheck(input == null ? null : (SingleCallback) input);
        return response == null ? end : response;
    }

    protected abstract CompletableFuture<Void> preConditionCheck(SingleCallback input) throws Exception;

    @Override
    protected final CompletableFuture<Void> afterPreConditionCheck(Object input) throws Exception {
        CompletableFuture<Void> response = afterPreConditionCheck(input == null ? null : (SingleCallback) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> afterPreConditionCheck(SingleCallback input) throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Void> beforeCall(Object input) throws Exception {
        CompletableFuture<Void> response = beforeCall(input == null ? null : (SingleCallback) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> beforeCall(SingleCallback input) throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Object> call(Object input) throws Exception {
        CompletableFuture<Void> call = call((SingleCallback) input);
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

    protected abstract CompletableFuture<Void> call(SingleCallback input) throws Exception;

    @Override
    protected final CompletableFuture<Void> afterCall(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = afterCall(input == null ? null : (SingleCallback) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> afterCall(SingleCallback input) throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Void> beforePostConditionCheck(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = beforePostConditionCheck(input == null ? null : (SingleCallback) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> beforePostConditionCheck(SingleCallback input) throws Exception {
        return end;
    }

    @Override
    protected final CompletableFuture<Void> postConditionCheck(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = postConditionCheck(input == null ? null : (SingleCallback) input);
        return response == null ? end : response;
    }

    protected abstract CompletableFuture<Void> postConditionCheck(SingleCallback input) throws Exception;

    @Override
    protected final CompletableFuture<Void> afterPostConditionCheck(Object input, Object output) throws Exception {
        CompletableFuture<Void> response = afterPostConditionCheck(input == null ? null : (SingleCallback) input);
        return response == null ? end : response;
    }

    protected CompletableFuture<Void> afterPostConditionCheck(SingleCallback input) throws Exception {
        return end;
    }

    @Override
    protected final Object getInputFromJsonWork(String inputJson) {
        return SingleCallback.fromJson(inputJson);
    }

    protected static final ContactFunctionality create(SysKB sysKB) {
        return new ContactFunctionalityImpl(sysKB);
    }
}