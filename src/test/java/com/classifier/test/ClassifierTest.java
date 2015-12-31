package com.classifier.test;

import com.classifier.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by vinay.madhusudhan on 01/12/15.
 */
public class ClassifierTest {

    @Test
    public void testDecisionTree() {

        Attribute attribute = new Attribute("attr1", AttributeType.BINARY, AttributeDataType.BOOLEAN);

        HashMap<String, Object> map = new HashMap<>();
        map.put("attr1", false);
        Data data = new Data(map, "false");

        DecisionTree tree = new DecisionTree(Arrays.asList(attribute));
        tree.constructTree(Arrays.asList(data));

    }
}
