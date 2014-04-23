package main;

import java.util.ArrayList;
import java.util.List;

/**
 * User: atscott
 * Date: 4/23/14
 * Time: 2:01 PM
 */
public class Node {
  protected List<NodeConnection> connections = new ArrayList<>();

  public void addConnection(Node node, int distance) {
    connections.add(new NodeConnection(node, distance));
    node.connections.add(new NodeConnection(this, distance));
  }

  public java.util.Iterator<NodeConnection> getConnectionsIterator() {
    return connections.iterator();
  }

}
