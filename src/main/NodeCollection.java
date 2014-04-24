package main;

import java.util.*;

/**
 * User: atscott
 * Date: 4/24/14
 * Time: 12:50 PM
 */
public class NodeCollection {
  List<NodeConnection> connections;
  Map<Node, List<NodeConnection>> connectionsByNode = new HashMap<>();

  public NodeCollection(NodeConnection... connections) {
    this(Arrays.asList(connections));
  }

  public NodeCollection(List<NodeConnection> connections) {
    this.connections = new ArrayList<>(connections);
    NodeConnection[] temp = new NodeConnection[connections.size()];
    for (NodeConnection connection : connections) {
      addNodeToConnectionsByNode(connection, connection.node1);
      addNodeToConnectionsByNode(connection, connection.node2);
    }
  }

  private void addNodeToConnectionsByNode(NodeConnection connection, Node node) {
    List<NodeConnection> connectionsForNode = connectionsByNode.get(node);
    if (connectionsForNode == null) {
      connectionsForNode = new ArrayList<>();
    }
    connectionsForNode.add(connection);
    connectionsByNode.put(node, connectionsForNode);
  }

  public List<NodeConnection> getConnectionsRelatedToNode(Node targetNode) {
    return connectionsByNode.get(targetNode);
  }

  public void removeConnectionsRelatedToNode(Node startNode) {
    List<NodeConnection> connectionsRemoved = connectionsByNode.remove(startNode);
    connections.removeAll(connectionsRemoved);
    for(NodeConnection connection : connectionsRemoved){
      connection.
    }
  }
}
