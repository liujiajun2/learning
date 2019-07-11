package xin.liujiajun.guava.graph;

import com.google.common.graph.Graph;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;

import java.util.Optional;
import java.util.Set;

/**
 * @author LiuJiaJun
 * @date 2019/3/15 20:31
 */
public class NetworkDemo {

    public static void main(String[] args) {
        MutableNetwork<String, String> network = NetworkBuilder.undirected().build();

        network.addNode("a");
        network.addNode("b");
        network.addNode("c");
        network.addNode("d");

        network.addEdge("a","c","d");
        network.addEdge("b","d","c");

        Graph<String> stringGraph = network.asGraph();
        System.out.println(stringGraph);

        Set<String> a = network.adjacentNodes("a");
        System.out.println(a);
        Optional<String> s = network.edgeConnecting("a", "c");
        if (s.isPresent()){
            System.out.println(s.get());
        }
    }
}
