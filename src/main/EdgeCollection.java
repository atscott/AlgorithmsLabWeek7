package main;

import java.util.*;

/**
 * User: atscott
 * Date: 4/24/14
 * Time: 12:50 PM
 */
public class EdgeCollection {
  List<Edge> edges;
  Map<Node, List<Edge>> edgesByNode = new HashMap<>();

  public EdgeCollection(Edge... edges) {
    this(Arrays.asList(edges));
  }

  public EdgeCollection(List<Edge> edges) {
    this.edges = new ArrayList<>(edges);
    for (Edge edge : edges) {
      addNodeToEdgesByNode(edge, edge.node1);
      addNodeToEdgesByNode(edge, edge.node2);
    }
  }

  private void addNodeToEdgesByNode(Edge edge, Node node) {
    List<Edge> edgesForNode = edgesByNode.get(node);
    if (edgesForNode == null) {
      edgesForNode = new ArrayList<>();
    }
    edgesForNode.add(edge);
    edgesByNode.put(node, edgesForNode);
  }

  public List<Edge> getedgesRelatedToNode(Node targetNode) {
    return edgesByNode.get(targetNode);
  }

}
