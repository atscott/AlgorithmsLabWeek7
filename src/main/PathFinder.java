package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: atscott
 * Date: 4/23/14
 * Time: 2:48 PM
 */
public class PathFinder {

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
      Node targetNode = getTargetNodeForedge(startNode, edge);
      if (!targetNode.visited) {
        startNode.visited = true;
        NodePath candidatePath = new NodePath(new EdgeCollection(Arrays.asList(edge)), edge.distance);
        if (targetNode == goalNode) {
          candidatePath.containsGoalNode = true;
        }
        candidatePath.appendPath(findPathRecursiveImplementation(maxEdgesToUse, collection, targetNode, goalNode, edgesAlreadyUsed + 1));
        startNode.visited = false;
        candidatePaths.add(candidatePath);
      }
    }

    return getShortestPathFromCandidates(candidatePaths);
  }

  private Node getTargetNodeForedge(Node startNode, Edge edge) {
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
