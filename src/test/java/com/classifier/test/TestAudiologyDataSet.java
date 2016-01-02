package com.classifier.test;

import com.classifier.Data;
import com.classifier.DecisionTree;
import com.classifier.attributes.Attribute;
import com.classifier.attributes.AttributeDataType;
import com.classifier.attributes.AttributeType;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Link: https://archive.ics.uci.edu/ml/datasets/Audiology+%28Standardized%29
 *
 */
public class TestAudiologyDataSet {

    private static final Logger logger = Logger.getLogger(TestAudiologyDataSet.class);

    public static List<Data> loadDataSet(Attribute[] attributes, String fileLocation) throws Exception {
        // Open the file
        FileInputStream fstream = new FileInputStream(fileLocation);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        List<Data> dataList = new ArrayList<>();

        while ((strLine = br.readLine()) != null) {
            String[] values = strLine.split(",");
            Data data = new Data();
            for (int i = 0; i < values.length - 2; ++i) {
                data.getMap().put(attributes[i].getName(), values[i]);
            }
            data.setClassType(values[values.length - 1]);
            dataList.add(data);
        }

        br.close();
        return dataList;
    }

    public static void main(String args[]) throws Exception {
        Attribute[] attributes = {
                new Attribute("age_gt_60", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("air()", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("airBoneGap", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("ar_c()", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("ar_u()", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("bone()", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("boneAbnormal", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("bser()", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("history_buzzing", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("history_dizziness", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("history_fluctuating", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("history_fullness", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("history_heredity", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("history_nausea", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("history_noise", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("history_recruitment", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("history_ringing", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("history_roaring", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("history_vomiting", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("late_wave_poor", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_at_2k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_cond_lt_1k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_gt_1k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_m_gt_2k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_m_sn", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_m_sn_gt_1k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_m_sn_gt_2k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_m_sn_gt_500", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_p_sn_gt_2k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_s_gt_500", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_s_sn", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_s_sn_gt_1k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_s_sn_gt_2k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_s_sn_gt_3k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_s_sn_gt_4k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_sn_2_3k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_sn_gt_1k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_sn_gt_2k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_sn_gt_3k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_sn_gt_4k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_sn_gt_500", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_sn_gt_6k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_sn_lt_1k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_sn_lt_2k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("m_sn_lt_3k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("middle_wave_poor", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("mod_gt_4k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("mod_mixed", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("mod_s_mixed", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("mod_s_sn_gt_500", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("mod_sn", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("mod_sn_gt_1k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("mod_sn_gt_2k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("mod_sn_gt_3k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("mod_sn_gt_4k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("mod_sn_gt_500", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("notch_4k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("notch_at_4k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("o_ar_c()", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("o_ar_u()", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("s_sn_gt_1k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("s_sn_gt_2k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("s_sn_gt_4k", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("speech()", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("static_normal", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("tymp()", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("viith_nerve_signs", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("wave_V_delayed", AttributeType.CATEGORICAL, AttributeDataType.STRING),
                new Attribute("waveform_ItoV_prolonged", AttributeType.CATEGORICAL, AttributeDataType.STRING)
        };

        logger.info("Attributes size = " + attributes.length);
        // load training data.
        List<Data> dataList = loadDataSet(attributes, "audiology.standardized.data");
        logger.info("Training set size = " + dataList.size());

        // Create a new decision tree.
        DecisionTree tree = new DecisionTree(Arrays.asList(attributes));

        // Build the tree using the training data.
        tree.generateDecisionTreeFor(dataList);

        // Test the tree built.
        List<Data> testDataList = loadDataSet(attributes, "audiology.standardized.test");
        tree.validateTree(testDataList);
    }
}
