package xin.liujiajun.java.nashron;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author LiuJiaJun
 * @date 2019/5/12 12:45
 */
public class NashornDemo {

    public static void main(String[] args) {
        hello();
    }

    public static void hello(){
        ScriptEngineManager m = new ScriptEngineManager();
        ScriptEngine engine = m.getEngineByName("nashorn");
        try {
            engine.eval("print('Hello world!')");
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        try {
            engine.eval("i = 27;");
            engine.put("j",15);
            engine.eval("var z = i + j;");
            System.out.println(((Number)engine.get("z")).intValue());
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }
}
