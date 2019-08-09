package xin.liujiajun.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @create 2019-07-31 17:46
 **/
public class ParameterDemo {

    public static void main(String[] args) {

        init();

        HashMap<String, String> map = new HashMap<>();
        map.put("test","张三");
        map.put("testd","李四");


        Entry entry = null;

        for (int i = 0; i < 20; i++) {
            try {
                entry = SphU.entry("resource", EntryType.IN,1,"testd");
                String test = map.get("testd");
                System.out.println(test);
                TimeUnit.SECONDS.sleep(1);
            } catch (BlockException e) {
//                e.printStackTrace();
                System.out.println(map.get("test"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (entry != null) {
                    entry.exit(1,"test");
                }
            }
        }

    }

    private static void init(){
        ParamFlowRule rule = new ParamFlowRule("resource").setParamIdx(0).setCount(18);
        ParamFlowRuleManager.loadRules(Collections.singletonList(rule));

        List<ParamFlowRule> rules = ParamFlowRuleManager.getRules();

    }
}
