package com.metaring.framework.broadcast;

import com.metaring.framework.functionality.FunctionalityInfo;
import com.metaring.framework.functionality.FunctionalitiesManager;
import com.metaring.framework.functionality.GeneratedFunctionalitiesManager;
import com.metaring.framework.functionality.Functionality;
import java.util.concurrent.CompletableFuture;
import com.metaring.framework.broadcast.Event;
import com.metaring.framework.broadcast.SingleCallback;
import com.metaring.framework.broadcast.MultipleCallback;
import java.lang.String;
import java.lang.Boolean;

public class BroadcastFunctionalitiesManager extends FunctionalitiesManager implements GeneratedFunctionalitiesManager {

    public static final FunctionalityInfo CALLBACK = FunctionalityInfo.create("com.metaring.framework.broadcast.callback", true, false, false, "com.metaring.framework.broadcast.Event", null);

    public static final FunctionalityInfo CONTACT = FunctionalityInfo.create("com.metaring.framework.broadcast.contact", true, false, false, "com.metaring.framework.broadcast.SingleCallback", null);

    public static final FunctionalityInfo CONTACT_ALL = FunctionalityInfo.create("com.metaring.framework.broadcast.contactAll", true, false, false, "com.metaring.framework.broadcast.MultipleCallback", null);

    public static final FunctionalityInfo EMIT = FunctionalityInfo.create("com.metaring.framework.broadcast.emit", true, false, false, "com.metaring.framework.broadcast.Event", null);

    public static final FunctionalityInfo IS_SUBSCRIBED = FunctionalityInfo.create("com.metaring.framework.broadcast.isSubscribed", true, false, false, "java.lang.String", "java.lang.Boolean");

    public static final FunctionalityInfo SUBSCRIBE = FunctionalityInfo.create("com.metaring.framework.broadcast.subscribe", true, false, false, "java.lang.String", null);

    public static final FunctionalityInfo UNSUBSCRIBE = FunctionalityInfo.create("com.metaring.framework.broadcast.unsubscribe", true, false, false, "java.lang.String", null);

    public static final CompletableFuture<Void> callback(Event event) {
        return call(CALLBACK, CallbackFunctionality.class, getCallingFunctionality(), event, null);
    }

    public static final CompletableFuture<Void> callback(Functionality functionality, Event event) {
        return call(CALLBACK, CallbackFunctionality.class, functionality, event, null);
    }

    public static final CompletableFuture<Void> callbackFromJson(String eventJson) {
        return callFromJson(CALLBACK, CallbackFunctionality.class, getCallingFunctionality(), eventJson, null);
    }

    public static final CompletableFuture<Void> callbackFromJson(Functionality callingFunctionality, String eventJson) {
        return callFromJson(CALLBACK, CallbackFunctionality.class, callingFunctionality, eventJson, null);
    }

    public static final CompletableFuture<Void> contact(SingleCallback singleCallback) {
        return call(CONTACT, ContactFunctionality.class, getCallingFunctionality(), singleCallback, null);
    }

    public static final CompletableFuture<Void> contact(Functionality functionality, SingleCallback singleCallback) {
        return call(CONTACT, ContactFunctionality.class, functionality, singleCallback, null);
    }

    public static final CompletableFuture<Void> contactFromJson(String singleCallbackJson) {
        return callFromJson(CONTACT, ContactFunctionality.class, getCallingFunctionality(), singleCallbackJson, null);
    }

    public static final CompletableFuture<Void> contactFromJson(Functionality callingFunctionality, String singleCallbackJson) {
        return callFromJson(CONTACT, ContactFunctionality.class, callingFunctionality, singleCallbackJson, null);
    }

    public static final CompletableFuture<Void> contactAll(MultipleCallback multipleCallback) {
        return call(CONTACT_ALL, ContactAllFunctionality.class, getCallingFunctionality(), multipleCallback, null);
    }

    public static final CompletableFuture<Void> contactAll(Functionality functionality, MultipleCallback multipleCallback) {
        return call(CONTACT_ALL, ContactAllFunctionality.class, functionality, multipleCallback, null);
    }

    public static final CompletableFuture<Void> contactAllFromJson(String multipleCallbackJson) {
        return callFromJson(CONTACT_ALL, ContactAllFunctionality.class, getCallingFunctionality(), multipleCallbackJson, null);
    }

    public static final CompletableFuture<Void> contactAllFromJson(Functionality callingFunctionality, String multipleCallbackJson) {
        return callFromJson(CONTACT_ALL, ContactAllFunctionality.class, callingFunctionality, multipleCallbackJson, null);
    }

    public static final CompletableFuture<Void> emit(Event event) {
        return call(EMIT, EmitFunctionality.class, getCallingFunctionality(), event, null);
    }

    public static final CompletableFuture<Void> emit(Functionality functionality, Event event) {
        return call(EMIT, EmitFunctionality.class, functionality, event, null);
    }

    public static final CompletableFuture<Void> emitFromJson(String eventJson) {
        return callFromJson(EMIT, EmitFunctionality.class, getCallingFunctionality(), eventJson, null);
    }

    public static final CompletableFuture<Void> emitFromJson(Functionality callingFunctionality, String eventJson) {
        return callFromJson(EMIT, EmitFunctionality.class, callingFunctionality, eventJson, null);
    }

    public static final CompletableFuture<Boolean> isSubscribed(String string) {
        return call(IS_SUBSCRIBED, IsSubscribedFunctionality.class, getCallingFunctionality(), string, result -> result.asTruth());
    }

    public static final CompletableFuture<Boolean> isSubscribed(Functionality functionality, String string) {
        return call(IS_SUBSCRIBED, IsSubscribedFunctionality.class, functionality, string, result -> result.asTruth());
    }

    public static final CompletableFuture<Boolean> isSubscribedFromJson(String stringJson) {
        return callFromJson(IS_SUBSCRIBED, IsSubscribedFunctionality.class, getCallingFunctionality(), stringJson, result -> result.asTruth());
    }

    public static final CompletableFuture<Boolean> isSubscribedFromJson(Functionality callingFunctionality, String stringJson) {
        return callFromJson(IS_SUBSCRIBED, IsSubscribedFunctionality.class, callingFunctionality, stringJson, result -> result.asTruth());
    }

    public static final CompletableFuture<Void> subscribe(String string) {
        return call(SUBSCRIBE, SubscribeFunctionality.class, getCallingFunctionality(), string, null);
    }

    public static final CompletableFuture<Void> subscribe(Functionality functionality, String string) {
        return call(SUBSCRIBE, SubscribeFunctionality.class, functionality, string, null);
    }

    public static final CompletableFuture<Void> subscribeFromJson(String stringJson) {
        return callFromJson(SUBSCRIBE, SubscribeFunctionality.class, getCallingFunctionality(), stringJson, null);
    }

    public static final CompletableFuture<Void> subscribeFromJson(Functionality callingFunctionality, String stringJson) {
        return callFromJson(SUBSCRIBE, SubscribeFunctionality.class, callingFunctionality, stringJson, null);
    }

    public static final CompletableFuture<Void> unsubscribe(String string) {
        return call(UNSUBSCRIBE, UnsubscribeFunctionality.class, getCallingFunctionality(), string, null);
    }

    public static final CompletableFuture<Void> unsubscribe(Functionality functionality, String string) {
        return call(UNSUBSCRIBE, UnsubscribeFunctionality.class, functionality, string, null);
    }

    public static final CompletableFuture<Void> unsubscribeFromJson(String stringJson) {
        return callFromJson(UNSUBSCRIBE, UnsubscribeFunctionality.class, getCallingFunctionality(), stringJson, null);
    }

    public static final CompletableFuture<Void> unsubscribeFromJson(Functionality callingFunctionality, String stringJson) {
        return callFromJson(UNSUBSCRIBE, UnsubscribeFunctionality.class, callingFunctionality, stringJson, null);
    }

}
