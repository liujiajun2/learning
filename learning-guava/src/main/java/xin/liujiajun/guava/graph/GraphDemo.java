package xin.liujiajun.guava.graph;

import com.google.common.graph.*;

import java.util.Set;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-15 15:27
 **/
public class GraphDemo {

    public static void main(String[] args) {
        //构建一个无向图
        MutableGraph<String> graph = GraphBuilder.undirected().build();
        //增加a,b,c,d 四个节点
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");
        //增加 a-b  a-b c-d 三条边
        graph.putEdge("a","c");
        graph.putEdge("a","b");
        graph.putEdge("c","d");

        //查看a节点所在的边
        Set<EndpointPair<String>> a = graph.incidentEdges("a");
        // [[b, a], [c, a]]
        System.out.println(a);
        // 查看b节点的后继节点
        Set<String> b = graph.successors("b");
        // [a]
        System.out.println(b);
        //查看所有边
        Set<EndpointPair<String>> edges = graph.edges();
        // [[b, a], [c, a], [d, c]]
        System.out.println(edges);

        //c节点出度数量  2
        System.out.println(graph.outDegree("c"));
        //获取c 的邻接点
        Set<String> c = graph.adjacentNodes("c");
        //[d, a]
        System.out.println(c);

    }
}
