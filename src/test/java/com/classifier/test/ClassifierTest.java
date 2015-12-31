package com.classifier.test;

import com.classifier.*;
import com.classifier.attributes.Attribute;
import com.classifier.attributes.AttributeDataType;
import com.classifier.attributes.AttributeType;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by vinay.madhusudhan on 01/12/15.
 */
public class ClassifierTest {

    @Test
    public void testDecisionTree() {

        Attribute attribute1 = new Attribute("attr1", AttributeType.NOMINAL, AttributeDataType.STRING);
        Attribute attribute2 = new Attribute("attr2", AttributeType.BINARY, AttributeDataType.STRING);


        HashMap<String, Object> map = new HashMap<>();
        map.put("attr1", "false");
        map.put("attr2", "true");
        Data data1 = new Data(map, /*class=*/"NO");

        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("attr1", "true");
        map2.put("attr2", "false");
        Data data2 = new Data(map2, "YES");

        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("attr1", "SOMETHING");
        map3.put("attr2", "true");
        Data data3 = new Data(map3, "YES");

        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("attr1", "SOMETHING");
        map4.put("attr2", "false");
        Data data4 = new Data(map4, "NO");

        DecisionTree tree = new DecisionTree(Arrays.asList(attribute1, attribute2));
        tree.generateDecisionTreeFor(Arrays.asList(data1, data2, data3, data4));
    }
}
