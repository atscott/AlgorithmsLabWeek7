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

  @Test
  void findBestPathFromThreePossibilities() {
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    Node node3 = new Node("node3");
    Node node4 = new Node("node4");
    NodeConnection n23 = new NodeConnection(node2, node3, 3);
    NodeConnection n12 = new NodeConnection(node1, node2, 4);
    NodeConnection n13 = new NodeConnection(node1, node3, 8);
    NodeConnection n24 = new NodeConnection(node2, node4, 3);
    NodeConnection n34 = new NodeConnection(node3, node4, 1);
    //possible options are 1-2-3-4, 1-2-4, 1-3-4
    NodeCollection collection = new NodeCollection(n13, n12, n23, n24, n34);

    PathFinder finder = new PathFinder();
    List<NodeConnection> actualConnectionsUsed = finder.findPath(collection, node1, node4);
    //expect found path to be 1-2-4
    List<NodeConnection> expectedConnectionsUsed = new ArrayList<>(Arrays.asList(n12, n24));
    Assert.assertEquals(actualConnectionsUsed, expectedConnectionsUsed);
  }

  @Test
  void findBestPathFromTwoPossibilitiesButShortestPathUsesTooManyEdges() {
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    Node node3 = new Node("node3");
    NodeConnection n12 = new NodeConnection(node1, node2, 1);
    NodeConnection n13 = new NodeConnection(node1, node3, 9);
    NodeConnection n23 = new NodeConnection(node2, node3, 1);
    //paths are 1-3 (distance 9), 1-2-3 (distance 2) - maybe there's construction on the road from 1 - 3 so it's slower
    NodeCollection collection = new NodeCollection(n13, n12, n23);

    PathFinder finder = new PathFinder();
    List<NodeConnection> actualConnectionsUsed = finder.findPath(1, collection, node1, node3);
    List<NodeConnection> expectedConnectionsUsed = new ArrayList<>(Arrays.asList(n13));
    Assert.assertEquals(actualConnectionsUsed, expectedConnectionsUsed);
  }
}
