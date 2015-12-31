package com.classifier;

import com.classifier.attributes.Attribute;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DecisionTree {

    public static final Integer THRESHOLD_SIZE_TO_STOP = 1;
    private final List<Attribute> attributeList;

    private Node root = null;

    public DecisionTree(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public void generateDecisionTreeFor(List<Data> dataList) {
        root = constructTree(dataList);
        GraphVizPrinter.printTree(root);
    }

    private Node constructTree(List<Data> dataList) {

        if (stopSplitting(dataList)) {
            return new Node(classifyData(dataList), null);
        }

        SplitCondition condition = findBestSplitFor(dataList);
        Node root = new Node(condition.attribute.getName() + "?", condition);

        final String attributeName = condition.attribute.getName();
        // Now group the data list based on the condition.
        Map<String, List<Data>> splitMap = dataList.stream().collect(Collectors.groupingBy((data) ->
                        data.getStringAttr(attributeName)
        ));

        splitMap.keySet().stream().forEach((key) -> {
            List<Data> currList = splitMap.get(key);
            Node child = constructTree(currList);
            root.placeChildNode(child, key);
        });

        return root;
    }

    /* This function determines which attribute should be used for
     test condition for the given dataList. The test condition with
     the minimum weighted Gini index will be used.
     */
    private SplitCondition findBestSplitFor(List<Data> dataList) {

        float minGiniIndex = Float.MAX_VALUE;
        Attribute attributeToUse = null;

        for (Attribute each : attributeList) {
            float giniIndex = getWeightedGiniIndexFor(each, dataList);
            if (giniIndex < minGiniIndex) {
                minGiniIndex = giniIndex;
                attributeToUse = each;
            }
        }

        return getSplitConditionFor(attributeToUse, dataList);
    }

    // TODO: Add the split conditions for the continuous attributes.
    // FIXME: Consider only the values of the attributes in the current node?
    private SplitCondition getSplitConditionFor(final Attribute attribute, final List<Data> dataList) {
        Set<String> attributeValueSet = new HashSet<>();
        dataList.stream().forEach((data) -> attributeValueSet.add(data.getStringAttr(attribute.getName())));
        String[] values = attributeValueSet.stream().toArray(String[]::new);
        return new SplitCondition(attribute, values);
    }

    //TODO: We need to find the best split for the given attribute.
    // For now we will go ahead with the splitting the values as present
    // without grouping the values, etc.
    private float getWeightedGiniIndexFor(final Attribute attribute, final List<Data> dataList) {

        // Group the data based on the different values of the attribute.
        // Note that each key-value pair in the below map will end up as different nodes.
        Map<String, List<Data>> splitMap = dataList.stream().collect(Collectors.groupingBy((data) ->
                        data.getStringAttr(attribute.getName())
        ));

        int totalRecords = dataList.size();
        Float weightedIndex = 0.0f;

        // Now calculate the weighted Gini index of potential child nodes.
        for (String key : splitMap.keySet()) {

            List<Data> currDataList = splitMap.get(key);
            int currRecords = currDataList.size();
            weightedIndex += ((float) currRecords / totalRecords) * getGiniIndexFor(currDataList);
        }

        return weightedIndex;
    }

    // Calculating the Gini index for the given data list.
    private float getGiniIndexFor(final List<Data> dataList) {

        // Group the data list based on the class type.
        Map<String, List<Data>> splitMap = dataList.stream().collect(Collectors.groupingBy((data) ->
                        data.getClassType()
        ));

        int totalRecords = dataList.size();
        Double giniIndex = 1.0d;

        for (String key : splitMap.keySet()) {
            int currClassSize = splitMap.get(key).size();
            Float currClassRatio = ((float) currClassSize / totalRecords);
            giniIndex -= Math.pow(currClassRatio, 2);
        }

        return giniIndex.floatValue();
    }

    /* Decide a class for the given list of data.
     * Class is the one which appears max times in the given data list.
     *
     * */
    private String classifyData(List<Data> dataList) {
        // Group the data list based on the class type.
        Map<String, Long> splitMap = dataList.stream().collect(Collectors.groupingBy((data) ->
                data.getClassType()
                , Collectors.counting()));

        if (splitMap.size() == 1) {
            return "Class : " + dataList.get(0).getClassType();
        }

        String maxClassType = null;
        Long maxValue = 0L;

        for (String key : splitMap.keySet()) {
            Long value = splitMap.get(key);
            if (value > maxValue) {
                maxClassType = key;
                maxValue = value;
            }
        }
        return "Class : " + maxClassType;
    }

    private boolean stopSplitting(List<Data> dataList) {

        // Stop, based on the size of the records.
        if (dataList.size() < THRESHOLD_SIZE_TO_STOP) {
            return true;
        }
        // Group the data list size based on the class type.
        Map<String, Long> splitMap = dataList.stream().collect(Collectors.groupingBy((data) ->
                data.getClassType()
                , Collectors.counting()));

        if (splitMap.size() == 1) {
            return true;
        }

        Data reference = dataList.get(0);

        // Make sure that all the records doesn't have the same set of attributes.
        for (int i = 1; i < dataList.size(); ++i) {
            if (!reference.checkAttrs(dataList.get(i))) {
                return false;
            }
        }

        // If all the set of attributes are same, stop the split.
        return true;
    }
}