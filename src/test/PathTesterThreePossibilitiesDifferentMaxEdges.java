import main.*;
import main.nodesAndEdges.Edge;
import main.nodesAndEdges.EdgeCollection;
import main.nodesAndEdges.Node;
import main.nodesAndEdges.NodePath;
import org.testng.Assert;
import org.testng.annotations.Test;

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
  Edge n12 = new Edge(node1, node2, 5);
  Edge n24 = new Edge(node2, node4, 3);
  Edge n46 = new Edge(node4, node6, 2);
  Edge n23 = new Edge(node2, node3, 1);
  Edge n34 = new Edge(node3, node4, 1);
  Edge n15 = new Edge(node1, node5, 2);
  Edge n56 = new Edge(node5, node6, 9);
  //possible paths are 1-2-3-4-6 (distance 9), 1-5-6 (distance 11), and 1-2-4-6 (distance 10)
  EdgeCollection collection = new EdgeCollection(n12, n24, n46, n23, n34, n15, n56);

  @Test
  public void testPathWithMaxTwoEdges() {
    PathFinder finder = new PathFinder();
    NodePath actualPathTaken = finder.findPath(2, collection, node1, node6);
    NodePath expectedPath = new NodePath(new EdgeCollection(n15, n56), 11);
    Assert.assertEquals(actualPathTaken, expectedPath);
  }

  @Test
  public void testPathWithMaxThreeEdges() {
    PathFinder finder = new PathFinder();
    NodePath actualPathTaken = finder.findPath(3, collection, node1, node6);
    NodePath expectedPath = new NodePath(new EdgeCollection(n12, n24, n46), 10);
    Assert.assertEquals(actualPathTaken, expectedPath);
  }

  @Test
  public void testPathWithMaxFourEdges() {
    PathFinder finder = new PathFinder();
    NodePath actualPathTaken = finder.findPath(4, collection, node1, node6);
    NodePath expectedPath = new NodePath(new EdgeCollection(n12, n23, n34, n46), 9);
    Assert.assertEquals(actualPathTaken, expectedPath);
  }
}
