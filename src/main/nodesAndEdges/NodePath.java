package main.nodesAndEdges;

/**
 * User: atscott
 * Date: 4/24/14
 * Time: 4:43 PM
 */
public class NodePath {
  private EdgeCollection edgesInPath;
  private int totalDistance = 0;
  public boolean containsGoalNode = false;


  public NodePath(EdgeCollection edgesInPath, int totalDistance) {
    this.edgesInPath = edgesInPath;
    this.totalDistance = totalDistance;
  }

  public int getEdgeCount(){
    return edgesInPath.edges.size();
  }

  public void appendPath(NodePath path) {
    containsGoalNode = path.containsGoalNode || containsGoalNode;
    totalDistance += path.totalDistance;
    edgesInPath.edges.addAll(path.edgesInPath.edges);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof NodePath)) return false;
    NodePath otherNodePath = (NodePath) other;
    boolean edgesAreEqual = otherNodePath.edgesInPath.edges.equals(edgesInPath.edges);
    boolean distancesAreEqual = otherNodePath.totalDistance == totalDistance;
    return distancesAreEqual && edgesAreEqual;
  }

  @Override
  public String toString() {
    String output = "";
    for (Edge edge : edgesInPath.edges) {
      output += edge.toString() + ", ";
    }
    output += "distance " + totalDistance;
    return output;
  }

  public int getTotalDistance() {
    return totalDistance;
  }
}
