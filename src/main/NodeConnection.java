package main;

/**
 * User: atscott
 * Date: 4/23/14
 * Time: 2:14 PM
 */
public class NodeConnection {
  public int distance;
  public Node node1;
  public Node node2;

  public NodeConnection(Node node1, Node node2, int distance) {
    this.distance = distance;
    this.node1 = node1;
    this.node2 = node2;
  }

  @Override
  public String toString() {
    return String.format("%s to %s (distance %s)", node1, node2, distance);
  }
}
