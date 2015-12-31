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

    public String getStringAttr(String attrName) {
        return (String) get(attrName);
    }

    public String getClassType() {
        return this.classType;
    }

    public HashMap<String, Object> getMap() {
        return this.map;
    }

    @Override
    public String toString() {
        return "Class = " + this.getClassType() + "   " +  map.toString();
    }

    public boolean checkAttrs(Data data1) {
        return data1.getMap().equals(this.map);
    }
}
