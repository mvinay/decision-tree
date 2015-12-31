package com.classifier;

import org.hamcrest.Condition;

import java.util.List;

/**
 * Created by vinay.madhusudhan on 31/12/15.
 */
public class Node {
    String label;
    boolean isLeaf;
    SplitCondition condition;
    Node[] pointers;
}
