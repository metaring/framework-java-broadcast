package com.metaring.framework.broadcast;

import com.metaring.framework.type.series.TextSeries;
import com.metaring.framework.broadcast.Event;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class MultipleCallback implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.framework.broadcast.multipleCallback";

    private TextSeries addresses;
    private Event data;

    private MultipleCallback(TextSeries addresses, Event data) {
        this.addresses = addresses;
        this.data = data;
    }

    public TextSeries getAddresses() {
        return this.addresses;
    }

    public Event getData() {
        return this.data;
    }

    public static MultipleCallback create(TextSeries addresses, Event data) {
        return new MultipleCallback(addresses, data);
    }

    public static MultipleCallback fromJson(String jsonString) {

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

        TextSeries addresses = null;
        if(dataRepresentation.hasProperty("addresses")) {
            try {
                addresses = dataRepresentation.getTextSeries("addresses");
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

        MultipleCallback multipleCallback = create(addresses, data);
        return multipleCallback;
    }

    public static MultipleCallback fromObject(Object object) {

        if(object == null) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromObject(object);

        TextSeries addresses = null;
        if(dataRepresentation.hasProperty("addresses")) {
            try {
                addresses = dataRepresentation.getTextSeries("addresses");
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

        MultipleCallback multipleCallback = create(addresses, data);
        return multipleCallback;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (addresses != null) {
            dataRepresentation.add("addresses", addresses.toArray());
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