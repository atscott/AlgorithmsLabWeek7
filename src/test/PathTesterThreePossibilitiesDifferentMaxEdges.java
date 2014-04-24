import main.Node;
import main.NodeCollection;
import main.NodeConnection;
import main.PathFinder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: atscott
 * Date: 4/24/14
 * Time: 4:11 PM
 */
public class PathTesterThreePossibilitiesDifferentMaxEdges {
  Node node1 = new Node("node1");
  Node node2 = new Node("node2");
  Node node3 = new Node("node3");
  Node node4 = new Node("node4");
  Node node5 = new Node("node5");
  Node node6 = new Node("node6");
  NodeConnection n12 = new NodeConnection(node1, node2, 5);
  NodeConnection n24 = new NodeConnection(node2, node4, 3);
  NodeConnection n46 = new NodeConnection(node4, node6, 2);
  NodeConnection n23 = new NodeConnection(node2, node3, 1);
  NodeConnection n34 = new NodeConnection(node3, node4, 1);
  NodeConnection n15 = new NodeConnection(node1, node5, 2);
  NodeConnection n56 = new NodeConnection(node5, node6, 9);
  //possible paths are 1-2-3-4-6 (distance 9), 1-5-6 (distance 11), and 1-2-4-6 (distance 10)
  NodeCollection collection = new NodeCollection(n12, n24, n46, n23, n34, n15, n56);

  @Test
  public void testPathWithMaxTwoEdges() {
    PathFinder finder = new PathFinder();
    List<NodeConnection> actualConnectionsUsed = finder.findPath(2, collection, node1, node6);
    List<NodeConnection> expectedConnectionsUsed = new ArrayList<>(Arrays.asList(n15, n56));
    Assert.assertEquals(actualConnectionsUsed, expectedConnectionsUsed);
  }

  @Test
  public void testPathWithMaxThreeEdges() {
    PathFinder finder = new PathFinder();
    List<NodeConnection> actualConnectionsUsed = finder.findPath(3, collection, node1, node6);
    List<NodeConnection> expectedConnectionsUsed = new ArrayList<>(Arrays.asList(n12, n24, n46));
    Assert.assertEquals(actualConnectionsUsed, expectedConnectionsUsed);
  }

  @Test
  public void testPathWithMaxFourEdges() {
    PathFinder finder = new PathFinder();
    List<NodeConnection> actualConnectionsUsed = finder.findPath(4, collection, node1, node6);
    List<NodeConnection> expectedConnectionsUsed = new ArrayList<>(Arrays.asList(n12, n23, n34, n46));
    Assert.assertEquals(actualConnectionsUsed, expectedConnectionsUsed);
  }
}
