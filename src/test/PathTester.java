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
 * Date: 4/23/14
 * Time: 2:20 PM
 */
public class PathTester {

  @Test
  public void findPathReturnsEmptyListIfStartNodeIsGoalNode() {
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    NodeConnection n12 = new NodeConnection(node1, node2, 1);
    NodeCollection collection = new NodeCollection(n12);

    PathFinder finder = new PathFinder();
    List<NodeConnection> actualConnectionsUsed = finder.findPath(collection, node1, node1);
    List<NodeConnection> expectedConnectionsUsed = new ArrayList<>();
    Assert.assertEquals(actualConnectionsUsed, expectedConnectionsUsed);
  }

  @Test
  public void findOnlyPathSingleConnection() {
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    NodeConnection n12 = new NodeConnection(node1, node2, 1);
    NodeCollection collection = new NodeCollection(n12);

    PathFinder finder = new PathFinder();
    List<NodeConnection> actualConnectionsUsed = finder.findPath(collection, node1, node2);
    List<NodeConnection> expectedConnectionsUsed = new ArrayList<>(Arrays.asList(n12));
    Assert.assertEquals(actualConnectionsUsed, expectedConnectionsUsed);
  }

  @Test
  public void findOnlyPathTwoConnections() {
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    Node node3 = new Node("node3");
    NodeConnection n12 = new NodeConnection(node1, node2, 1);
    NodeConnection n23 = new NodeConnection(node2, node3, 2);
    NodeCollection collection = new NodeCollection(n12, n23);

    PathFinder finder = new PathFinder();
    List<NodeConnection> actualConnectionsUsed = finder.findPath(collection, node1, node3);
    List<NodeConnection> expectedConnectionsUsed = new ArrayList<>(Arrays.asList(n12, n23));
    Assert.assertEquals(actualConnectionsUsed, expectedConnectionsUsed);
  }

  @Test
  void findBestPathFromTwoPossibilities() {
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    Node node3 = new Node("node3");
    NodeConnection n13 = new NodeConnection(node1, node3, 1);
    NodeConnection n12 = new NodeConnection(node1, node2, 1);
    NodeConnection n23 = new NodeConnection(node2, node3, 1);
    NodeCollection collection = new NodeCollection(n13, n12, n23);

    PathFinder finder = new PathFinder();
    List<NodeConnection> actualConnectionsUsed = finder.findPath(collection, node1, node3);
    List<NodeConnection> expectedConnectionsUsed = new ArrayList<>(Arrays.asList(n13));
    Assert.assertEquals(actualConnectionsUsed, expectedConnectionsUsed);
  }

}
