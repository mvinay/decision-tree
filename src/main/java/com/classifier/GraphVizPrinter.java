package com.classifier;

import org.kohsuke.graphviz.Edge;
import org.kohsuke.graphviz.Graph;
import org.kohsuke.graphviz.Node;
import org.kohsuke.graphviz.Style;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by vinay.madhusudhan on 31/12/15.
 */
public class GraphVizPrinter {

    private static int i = 0;

    private static Node createNodeFor(com.classifier.Node node) {
        Node gNode = new Node();

        Style style = new Style();

        if (node.isLeafNode()) {
            style.attr("shape", "record");
        }
        gNode.style(style);
        gNode.id("Node" + node.hashCode());

        String label = node.getLabel();
        if (node.isLeafNode()) {
            label = "Class = " + label;
        } else {
            label += "?";
        }
        gNode.attr("label", label);
        return gNode;
    }

    private static void writeGraphToFile(Graph graph) {
        try (OutputStream out = new FileOutputStream("dotfile", false)) {
            graph.writeTo(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTree(com.classifier.Node root) {
        Graph graph = new Graph();
        graph.id("Graph" + i++);

        graph.attr("labelloc", "t");
        final com.classifier.Node temp = root;

        graph.node(createNodeFor(temp));
        if (temp.isLeafNode()) {
            writeGraphToFile(graph);
            return;
        }

        Queue<com.classifier.Node> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(temp);

        while (!nodeQueue.isEmpty()) {

            final com.classifier.Node curr = nodeQueue.remove();
            Node gSrcNode = createNodeFor(curr);
            graph.node(gSrcNode);

            com.classifier.Node[] array = curr.getPointers();
            for (int i = 0; i < array.length; ++i) {
                com.classifier.Node node = array[i];
                Node gDstNode = createNodeFor(node);
                graph.node(gDstNode);

                Edge edge = new Edge(gSrcNode, gDstNode);
                edge.attr("label", curr.getCondition().values[i]);
                graph.edge(edge);

                if (!node.isLeafNode()) {
                    nodeQueue.add(node);
                } else {

                }
            }
        }
        writeGraphToFile(graph);
    }

}
