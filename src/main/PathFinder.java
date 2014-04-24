package main;

import java.util.ArrayList;
import java.util.List;

/**
 * User: atscott
 * Date: 4/23/14
 * Time: 2:48 PM
 */
public class PathFinder {

  public List<NodeConnection> findPath(int maxEdgesUsed, NodeCollection collection, Node startNode, Node goalNode) {
    if(startNode == goalNode){
      return new ArrayList<>();
    }

    int maxEdgesToUse = maxEdgesUsed;
    List<List<NodeConnection>> candidatePaths = new ArrayList<>();
    List<NodeConnection> connectionsForStartNode = collection.getConnectionsRelatedToNode(startNode);
    for (NodeConnection connection : connectionsForStartNode) {
      Node targetNode;
      if (connection.node1 != startNode) {
        targetNode = connection.node1;
      } else {
        targetNode = connection.node2;
      }
      if (!targetNode.visited) {
        startNode.visited = true;
        List<NodeConnection> candidatePath = new ArrayList<>();
        candidatePath.add(connection);
        candidatePath.addAll(findPath(maxEdgesUsed, collection, targetNode, goalNode));
        candidatePaths.add(candidatePath);
        startNode.visited = false;
      }
    }

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

  public List<NodeConnection> findPath(NodeCollection nodeCollection, Node startNode, Node goalNode) {
    return findPath(Integer.MAX_VALUE, nodeCollection, startNode, goalNode);
  }
}
