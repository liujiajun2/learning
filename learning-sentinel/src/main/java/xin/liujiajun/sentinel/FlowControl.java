package xin.liujiajun.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @create 2019-07-23 08:46
 **/
public class FlowControl {

    public static void main(String[] args) {
        initFlowRules();

        while (true) {
            try(Entry entry = SphU.entry("HelloWorld")){
                System.out.println("hello world");
            }catch (BlockException e) {
                System.out.println("blocked!");
            }
        }
    }

    private static void initFlowRules(){
        ArrayList<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();

        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //QPS 20
        rule.setCount(20);
        rules.add(rule);


        FlowRuleManager.loadRules(rules);
    }
//            rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);

}
