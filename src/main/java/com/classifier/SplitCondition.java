package com.classifier;

import com.classifier.attributes.Attribute;

/**
 * Created by vinay.madhusudhan on 31/12/15.
 */
public class SplitCondition {

    Attribute attribute;
    String[] values;

    public SplitCondition(Attribute attr, String[] values) {
        this.attribute = attr;
        this.values = values;
    }

    // This will return the right child for the given data, based on the condition.
    public Integer getChildPointerPos(String attributeValue) {

        for (int i = 0; i < values.length; ++i) {
            if (attributeValue.equals(values[i])) {
                return i;
            }
        }

        throw new RuntimeException("Invalid attribute value : " + attributeValue);
    }
}
