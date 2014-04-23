package main;

/**
 * User: atscott
 * Date: 4/23/14
 * Time: 2:14 PM
 */
public class NodeConnection
{
  public int distance;
  public Node connectedNode;

  public NodeConnection(Node node, int distance)
  {
    this.distance = distance;
    this.connectedNode = node;
  }
}
