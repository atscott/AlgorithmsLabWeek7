import main.Node;
import main.PathFinder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: atscott
 * Date: 4/23/14
 * Time: 2:20 PM
 */
public class PathTester {
  @Test
  public void doSomething() {
    Node node1 = new Node();
    Node node2 = new Node();
    Node node3 = new Node();
    Node node4 = new Node();
    Node node5 = new Node();
    Node node6 = new Node();
    node1.addConnection(node2, 5);
    node1.addConnection(node5, 2);
    node5.addConnection(node6, 9);
    node2.addConnection(node3, 1);
    node2.addConnection(node4, 4);
    node3.addConnection(node4, 1);
    node4.addConnection(node6, 3);
    List<Node> nodeCollection = new ArrayList<>();
    nodeCollection.addAll(Arrays.asList(node1, node2, node3, node4, node5, node6));

    PathFinder.findPath(2, nodeCollection, node1, node6);

  }
}
