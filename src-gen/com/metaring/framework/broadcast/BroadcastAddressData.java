package com.metaring.framework.broadcast;

import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.GeneratedCoreType;

public class BroadcastAddressData implements GeneratedCoreType {

    public static final String FULLY_QUALIFIED_NAME = "com.metaring.framework.broadcast.broadcastAddressData";

    private String address;
    private String newAddress;

    private BroadcastAddressData(String address, String newAddress) {
        this.address = address;
        this.newAddress = newAddress;
    }

    public String getAddress() {
        return this.address;
    }

    public String getNewAddress() {
        return this.newAddress;
    }

    public static BroadcastAddressData create(String address, String newAddress) {
        return new BroadcastAddressData(address, newAddress);
    }

    public static BroadcastAddressData fromJson(String jsonString) {

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

        String newAddress = null;
        if(dataRepresentation.hasProperty("newAddress")) {
            try {
                newAddress = dataRepresentation.getText("newAddress");
            } catch (Exception e) {
            }
        }

        BroadcastAddressData broadcastAddressData = create(address, newAddress);
        return broadcastAddressData;
    }

    public DataRepresentation toDataRepresentation() {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        if (address != null) {
            dataRepresentation.add("address", address);
        }

        if (newAddress != null) {
            dataRepresentation.add("newAddress", newAddress);
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