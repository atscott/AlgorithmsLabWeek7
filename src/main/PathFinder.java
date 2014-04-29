package main;

import main.nodesAndEdges.Edge;
import main.nodesAndEdges.EdgeCollection;
import main.nodesAndEdges.Node;
import main.nodesAndEdges.NodePath;

import java.util.*;

/**
 * User: atscott
 * Date: 4/23/14
 * Time: 2:48 PM
 */
public class PathFinder {

  Map<Node, NodePath> shortestPathsByStartNode = new HashMap<>();

  public NodePath findPath(EdgeCollection edgeCollection, Node startNode, Node goalNode) {
    return findPath(Integer.MAX_VALUE, edgeCollection, startNode, goalNode);
  }

  public NodePath findPath(int maxEdgesToUse, EdgeCollection collection, Node startNode, Node goalNode) {
    return findPathRecursiveImplementation(maxEdgesToUse, collection, startNode, goalNode, 0);
  }

  private NodePath findPathRecursiveImplementation(int maxEdgesToUse, EdgeCollection collection, Node startNode, Node goalNode, int edgesAlreadyUsed) {
    if (startNode == goalNode || edgesAlreadyUsed >= maxEdgesToUse) {
      return new NodePath(new EdgeCollection(new ArrayList<Edge>()), 0);
    }

    List<NodePath> candidatePaths = new ArrayList<>();
    for (Edge edge : collection.getedgesRelatedToNode(startNode)) {
      Node targetNode = getTargetNodeForEdge(startNode, edge);
      if (!targetNode.visited) {
        NodePath candidatePath = new NodePath(new EdgeCollection(Arrays.asList(edge)), edge.distance);
        if (shortestPathsByStartNode.containsKey(targetNode)) {
          candidatePath.appendPath(shortestPathsByStartNode.get(targetNode));
        } else {
          startNode.visited = true;
          if (targetNode == goalNode) {
            candidatePath.containsGoalNode = true;
          }
          candidatePath.appendPath(findPathRecursiveImplementation(maxEdgesToUse, collection, targetNode, goalNode, edgesAlreadyUsed + 1));
          startNode.visited = false;
        }
        candidatePaths.add(candidatePath);
      }
    }

    return getShortestPathFromCandidates(candidatePaths);
  }

  private Node getTargetNodeForEdge(Node startNode, Edge edge) {
    Node targetNode;
    if (edge.node1 != startNode) {
      targetNode = edge.node1;
    } else {
      targetNode = edge.node2;
    }
    return targetNode;
  }

  private NodePath getShortestPathFromCandidates(List<NodePath> candidatePaths) {
    NodePath bestPath = null;
    for (NodePath path : candidatePaths) {
      if (path.containsGoalNode && (bestPath == null || path.getTotalDistance() < bestPath.getTotalDistance())) {
        bestPath = path;
      }
    }
    if (bestPath == null) {
      return new NodePath(new EdgeCollection(new ArrayList<Edge>()), 0);
    }
    return bestPath;
  }


}
