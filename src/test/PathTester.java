import main.*;
import main.nodesAndEdges.Edge;
import main.nodesAndEdges.EdgeCollection;
import main.nodesAndEdges.Node;
import main.nodesAndEdges.NodePath;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    Edge n12 = new Edge(node1, node2, 1);
    EdgeCollection collection = new EdgeCollection(n12);

    PathFinder finder = new PathFinder();
    NodePath actualPathTaken = finder.findPath(collection, node1, node1);
    NodePath expectedPath = new NodePath(new EdgeCollection(), 0);
    Assert.assertEquals(actualPathTaken, expectedPath);
  }

  @Test
  public void findOnlyPathSingleEdge() {
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    Edge n12 = new Edge(node1, node2, 1);
    EdgeCollection collection = new EdgeCollection(n12);

    PathFinder finder = new PathFinder();
    NodePath actualPathTaken = finder.findPath(collection, node1, node2);
    NodePath expectedPath = new NodePath(new EdgeCollection(n12), 1);
    Assert.assertEquals(actualPathTaken, expectedPath);
  }

  @Test
  public void findOnlyPathTwoEdges() {
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    Node node3 = new Node("node3");
    Edge n12 = new Edge(node1, node2, 1);
    Edge n23 = new Edge(node2, node3, 2);
    EdgeCollection collection = new EdgeCollection(n12, n23);

    PathFinder finder = new PathFinder();
    NodePath actualPathTaken = finder.findPath(collection, node1, node3);
    NodePath expectedPath = new NodePath(new EdgeCollection(n12, n23), 3);
    Assert.assertEquals(actualPathTaken, expectedPath);
  }

  @Test
  void findBestPathFromTwoPossibilities() {
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    Node node3 = new Node("node3");
    Edge n13 = new Edge(node1, node3, 1);
    Edge n12 = new Edge(node1, node2, 1);
    Edge n23 = new Edge(node2, node3, 1);
    EdgeCollection collection = new EdgeCollection(n13, n12, n23);

    PathFinder finder = new PathFinder();
    NodePath actualPathTaken = finder.findPath(collection, node1, node3);
    NodePath expectedPath = new NodePath(new EdgeCollection(n13), 1);
    Assert.assertEquals(actualPathTaken, expectedPath);
  }

  @Test
  void findBestPathFromThreePossibilities() {
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    Node node3 = new Node("node3");
    Node node4 = new Node("node4");
    Edge n23 = new Edge(node2, node3, 3);
    Edge n12 = new Edge(node1, node2, 4);
    Edge n13 = new Edge(node1, node3, 8);
    Edge n24 = new Edge(node2, node4, 3);
    Edge n34 = new Edge(node3, node4, 1);
    //possible options are 1-2-3-4, 1-2-4, 1-3-4
    EdgeCollection collection = new EdgeCollection(n13, n12, n23, n24, n34);

    PathFinder finder = new PathFinder();
    NodePath actualPathTaken = finder.findPath(collection, node1, node4);
    //expect found path to be 1-2-4
    NodePath expectedPath = new NodePath(new EdgeCollection(n12, n24), 7);
    Assert.assertEquals(actualPathTaken, expectedPath);
  }

  @Test
  void findBestPathFromTwoPossibilitiesButShortestPathUsesTooManyEdges() {
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    Node node3 = new Node("node3");
    Edge n12 = new Edge(node1, node2, 1);
    Edge n13 = new Edge(node1, node3, 9);
    Edge n23 = new Edge(node2, node3, 1);
    //paths are 1-3 (distance 9), 1-2-3 (distance 2) - maybe there's construction on the road from 1 - 3 so it's slower
    EdgeCollection collection = new EdgeCollection(n13, n12, n23);

    PathFinder finder = new PathFinder();
    NodePath actualPathTaken = finder.findPath(1, collection, node1, node3);
    NodePath expectedPath = new NodePath(new EdgeCollection(n13), 9);
    Assert.assertEquals(actualPathTaken, expectedPath);
  }
}
