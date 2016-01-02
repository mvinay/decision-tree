package com.classifier.test;

import com.classifier.Data;
import com.classifier.DecisionTree;
import com.classifier.attributes.Attribute;
import com.classifier.attributes.AttributeDataType;
import com.classifier.attributes.AttributeType;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by vinay.madhusudhan on 01/12/15.
 */
public class ClassifierTest {

    @Test
    public void testDecisionTree() throws Exception {

        // FIXME: Convert this to JSON format
        Attribute[] attribute = {new Attribute("Body Temparature", AttributeType.CATEGORICAL, AttributeDataType.STRING)
                , new Attribute("Skin Cover", AttributeType.BINARY, AttributeDataType.STRING)
                , new Attribute("Gives Birth", AttributeType.BINARY, AttributeDataType.STRING)
                , new Attribute("Aquatic Creature", AttributeType.BINARY, AttributeDataType.STRING)
                , new Attribute("Aerial Creature", AttributeType.BINARY, AttributeDataType.STRING)
                , new Attribute("Has Legs", AttributeType.BINARY, AttributeDataType.STRING)
                , new Attribute("Hibernates", AttributeType.BINARY, AttributeDataType.STRING)};


        int size = 15;
        Data[] datas = new Data[size];

        for (int i = 0; i < datas.length; ++i) {
            datas[i] = new Data();
        }

        // Get the test data from the file.

        String[][] line = new String[][]{
                {"warm-blooded", "cold-blooded", "cold-blooded", "warm-blooded", "cold-blooded", "cold-blooded",
                        "warm-blooded", "warm-blooded", "warm-blooded",
                        "cold-blooded", "cold-blooded", "warm-blooded", "warm-blooded", "cold-blooded", "cold-blooded"},
                {"hair", "scales", "scales", "hair", "none", "scales", "hair", "feathers", "fur", "scales", "scales",
                        "feathers", "quills", "scales", "none"},
                {"yes", "no", "no", "yes", "no", "no", "yes", "no", "yes", "yes", "no", "no", "yes", "no", "no"},
                {"no", "no", "yes", "yes", "semi", "no", "no", "no", "no", "yes", "semi", "semi", "no", "yes", "semi"},
                {"no", "no", "no", "no", "no", "no", "yes", "yes", "no", "no", "no", "no", "no", "no", "no"},
                {"yes", "no", "no", "no", "yes", "yes", "yes", "yes", "yes", "no", "yes", "yes", "yes", "no", "yes"},
                {"no", "yes", "no", "no", "yes", "no", "yes", "no", "no", "no", "no", "no", "yes", "no", "yes"},
                {"mammal", "reptile", "fish", "mammal", "amphibian", "reptile", "mammal",
                        "bird", "mammal", "fish", "reptile", "bird", "mammal", "fish", "amphibian"}
        };

        for (int i = 0; i < line.length - 1; ++i) {
            String[] values = line[i];
            for (int j = 0; j < size; ++j) {
                datas[j].getMap().put(attribute[i].getName(), values[j]);
            }
        }

        String[] values = line[line.length - 1];
        for (int j = 0; j < size; ++j) {
            datas[j].setClassType(values[j]);
        }

        DecisionTree tree = new DecisionTree(Arrays.asList(attribute));
        tree.generateDecisionTreeFor(Arrays.asList(datas));
    }
}
