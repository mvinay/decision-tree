package com.classifier;

/**
 * Created by vinay.madhusudhan on 31/12/15.
 */
public class Node {
    private String label;
    private boolean isLeaf;
    private SplitCondition condition;
    private Node[] pointers;

    public Node(String label, SplitCondition condition) {
        this.label = label;
        this.condition = condition;

        if (condition == null) {
            isLeaf = true;
        } else {
            this.pointers = new Node[condition.values.length];
            isLeaf = false;
        }
    }

    public void placeChildNode(Node child, String attributeValue) {
        int index = condition.getChildPointerPos(attributeValue);
        assert (index < pointers.length);
        this.pointers[index] = child;
    }

    public Node getNodeForData(Data data) {
        String attributeValue = data.getStringAttr(this.condition.attribute.getName());
        int index = condition.getChildPointerPos(attributeValue);
        return this.pointers[index];
    }

    public String getLabel() {
        return this.label;
    }

    public Boolean isLeafNode() {
        return this.isLeaf;
    }

    public SplitCondition getCondition() {
        return condition;
    }

    public Node[] getPointers() {
        return this.pointers;
    }
}
