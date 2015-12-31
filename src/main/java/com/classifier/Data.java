package com.classifier;


import java.util.HashMap;

/**
 * Created by vinay.madhusudhan on 31/12/15.
 */
public class Data {
    private HashMap<String, Object> map;
    private String classType;


    public Data(HashMap<String, Object> map, String classType) {
        this.map = map;
        this.classType = classType;
    }

    private Object get(String attrName) {
        return map.get(attrName);
    }

    public <T> T getAttr(String attrName) {
        return (T) get(attrName);
    }

    public String getClassType() {
        return this.classType;
    }
}
