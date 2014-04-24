package main.nodesAndEdges;


/**
 * User: atscott
 * Date: 4/23/14
 * Time: 2:01 PM
 */
public class Node {
  public final String name;
  public boolean visited = false;

  public Node(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
