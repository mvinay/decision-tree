package com.classifier;


import java.util.List;

public class DecisionTree {

    private final List<Attribute> attributeList;

    public DecisionTree(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public Node constructTree(List<Data> dataList) {
        if (stopSplitting(dataList)) {
            Node leaf = new Node();
            leaf.isLeaf = true;
            leaf.label = classifyData(dataList);
            return leaf;
        }

        Node root = new Node();
        root.condition = findBestSplitFor(dataList);
        return new Node();
    }


    /* This function determines which attribute should be used for
     test condition in this node.
     */
    private SplitCondition findBestSplitFor(List<Data> dataList) {


        return null;
    }


    /* Decide a class for the given list of data. */
    private String classifyData(List<Data> dataList) {
        return "";
    }

    private boolean stopSplitting(List<Data> dataList) {
        return true;
    }
}