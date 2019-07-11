package xin.liujiajun.guava.graph;

import com.google.common.graph.Graph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.util.Optional;

/**
 * @author LiuJiaJun
 * @date 2019/3/15 20:20
 */
public class ValueGraphDemo {

    public static void main(String[] args) {

        MutableValueGraph<String, Integer> graph = ValueGraphBuilder.directed().build();
        //添加三个节点
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        //添加两条有向边
        graph.putEdgeValue("a","b",2);
        graph.putEdgeValue("a","c",3);

        //返回一个Graph视图
        Graph<String> stringGraph = graph.asGraph();
        //isDirected: true, allowsSelfLoops: false, nodes: [a, b, c], edges: [<a -> b>, <a -> c>]
        System.out.println(stringGraph);
        //取权值
        Optional<Integer> integer = graph.edgeValue("a", "b");
        if (integer.isPresent()){
            //2
            System.out.println(integer.get());
        }
        Integer integer1 = graph.edgeValueOrDefault("b", "c", -1);
        //-1
        System.out.println(integer1);
    }
}
