package com.metaring.framework.broadcast;

import com.metaring.framework.broadcast.Event;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class SingleCallback implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.framework.broadcast.singleCallback";

    private String address;
    private Event data;

    private SingleCallback(String address, Event data) {
        this.address = address;
        this.data = data;
    }

    public String getAddress() {
        return this.address;
    }

    public Event getData() {
        return this.data;
    }

    public static SingleCallback create(String address, Event data) {
        return new SingleCallback(address, data);
    }

    public static SingleCallback fromJson(String jsonString) {

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

        String address = null;
        if(dataRepresentation.hasProperty("address")) {
            try {
                address = dataRepresentation.getText("address");
            } catch (Exception e) {
            }
        }

        Event data = null;
        if(dataRepresentation.hasProperty("data")) {
            try {
                data = dataRepresentation.get("data", Event.class);
            } catch (Exception e) {
            }
        }

        SingleCallback singleCallback = create(address, data);
        return singleCallback;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (address != null) {
            dataRepresentation.add("address", address);
        }

        if (data != null) {
            dataRepresentation.add("data", data);
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