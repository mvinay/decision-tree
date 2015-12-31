package com.classifier;

/**
 * Created by vinay.madhusudhan on 31/12/15.
 */
public class Attribute {
    private String name;
    private AttributeType type;
    private AttributeDataType  dataType;

    public Attribute(String name, AttributeType type, AttributeDataType dataType) {
        this.name = name;
        this.type = type;
        this.dataType = dataType;
    }
}
