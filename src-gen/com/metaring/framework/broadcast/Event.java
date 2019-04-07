package com.metaring.framework.broadcast;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class Event implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.framework.broadcast.event";

    private String topic;
    private DataRepresentation payload;

    private Event(String topic, DataRepresentation payload) {
        this.topic = topic;
        this.payload = payload;
    }

    public String getTopic() {
        return this.topic;
    }

    public DataRepresentation getPayload() {
        return this.payload;
    }

    public static Event create(String topic, DataRepresentation payload) {
        return new Event(topic, payload);
    }

    public static Event fromJson(String jsonString) {

        if(jsonString == null) {
            return null;
        }

        jsonString = jsonString.trim();
        if(jsonString.isEmpty()) {
            return null;
        }

        if(jsonString.equalsIgnoreCase("null")) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromJson(jsonString);

        String topic = null;
        if(dataRepresentation.hasProperty("topic")) {
            try {
                topic = dataRepresentation.getText("topic");
            } catch (Exception e) {
            }
        }

        DataRepresentation payload = null;
        if(dataRepresentation.hasProperty("payload")) {
            try {
                payload = dataRepresentation.get("payload");
            } catch (Exception e) {
            }
        }

        Event event = create(topic, payload);
        return event;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (topic != null) {
            dataRepresentation.add("topic", topic);
        }

        if (payload != null) {
            dataRepresentation.add("payload", payload);
        }

        return dataRepresentation;
    }

    @Override
    public String toJson() {
        return toDataRepresentation().toJson();
    }

    @Override
    public String toString() {
        return this.toJson();
    }
}