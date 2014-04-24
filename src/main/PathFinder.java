package main;

import java.util.ArrayList;
import java.util.List;

/**
 * User: atscott
 * Date: 4/23/14
 * Time: 2:48 PM
 */
public class PathFinder {

  public List<NodeConnection> findPath(NodeCollection nodeCollection, Node startNode, Node goalNode) {
    return findPath(Integer.MAX_VALUE, nodeCollection, startNode, goalNode);
  }

  public List<NodeConnection> findPath(int maxEdgesToUse, NodeCollection collection, Node startNode, Node goalNode) {
    return findPathRecursiveImplementation(maxEdgesToUse, collection, startNode, goalNode, 0);
  }

  private List<NodeConnection> findPathRecursiveImplementation(int maxEdgesToUse, NodeCollection collection, Node startNode, Node goalNode, int edgesAlreadyUsed) {
    if (startNode == goalNode || edgesAlreadyUsed >= maxEdgesToUse) {
      return new ArrayList<>();
    }

    List<List<NodeConnection>> candidatePaths = new ArrayList<>();
    for (NodeConnection connection : collection.getConnectionsRelatedToNode(startNode)) {
      Node targetNode = getTargetNodeForConnection(startNode, connection);
      if (!targetNode.visited) {
        startNode.visited = true;
        List<NodeConnection> candidatePath = new ArrayList<>();
        candidatePath.add(connection);
        candidatePath.addAll(findPathRecursiveImplementation(maxEdgesToUse, collection, targetNode, goalNode, edgesAlreadyUsed + 1));
        startNode.visited = false;
        candidatePaths.add(candidatePath);
      }
    }

    return getShortestPathFromCandidates(goalNode, candidatePaths);
  }

  private Node getTargetNodeForConnection(Node startNode, NodeConnection connection) {
    Node targetNode;
    if (connection.node1 != startNode) {
      targetNode = connection.node1;
    } else {
      targetNode = connection.node2;
    }
    return targetNode;
  }

  private List<NodeConnection> getShortestPathFromCandidates(Node goalNode, List<List<NodeConnection>> candidatePaths) {
    int bestPathDistance = -1;
    List<NodeConnection> bestPath = new ArrayList<>();
    for (List<NodeConnection> path : candidatePaths) {
      int distanceForPath = 0;
      boolean pathHasGoalNode = false;
      for (NodeConnection connection : path) {
        if (connection.node1 == goalNode || connection.node2 == goalNode) {
          pathHasGoalNode = true;
        }
        distanceForPath += connection.distance;
      }
      if (pathHasGoalNode && (distanceForPath < bestPathDistance || bestPathDistance == -1)) {
        bestPathDistance = distanceForPath;
        bestPath.clear();
        bestPath.addAll(path);
      }
    }

    return bestPath;
  }


}
