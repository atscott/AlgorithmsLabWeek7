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

  public List<NodeConnection> findPath(int maxEdgesUsed, NodeCollection collection, Node startNode, Node goalNode) {
    int maxEdgesToUse = maxEdgesUsed;
    List<List<NodeConnection>> candidatePaths = new ArrayList<>();
    List<NodeConnection> connectionsForStartNode = collection.getConnectionsRelatedToNode(startNode);
    for (NodeConnection connection : connectionsForStartNode) {
      NodeCollection remainingCollection = new NodeCollection(collection.connections);
      remainingCollection.removeConnectionsRelatedToNode(startNode);
      Node targetNode;
      if (connection.node1 != startNode) {
        targetNode = connection.node1;
      } else {
        targetNode = connection.node2;
      }
      candidatePaths.add(findPath(maxEdgesUsed, remainingCollection, targetNode, goalNode));
    }

    int bestPathDistance = -1;
    List<NodeConnection> bestPath = new ArrayList<>();
    for (List<NodeConnection> path : candidatePaths) {
      int distanceForPath = 0;
      for (NodeConnection connection : path) {
        distanceForPath += connection.distance;
      }
      if (distanceForPath < bestPathDistance || bestPathDistance == -1) {
        bestPathDistance = distanceForPath;
        bestPath.clear();
        bestPath.addAll(path);
      }
    }

    return bestPath;
  }

  public List<NodeConnection> findPath(NodeCollection nodeCollection, Node startNode, Node goalNode) {
    return findPath(Integer.MAX_VALUE, nodeCollection, startNode, goalNode);
  }
}
