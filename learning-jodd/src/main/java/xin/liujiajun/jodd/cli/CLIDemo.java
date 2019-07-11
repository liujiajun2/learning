package xin.liujiajun.jodd.cli;

import jodd.cli.Cli;

import java.util.function.Consumer;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-23 16:01
 **/
public class CLIDemo {


    public static void main(String[] args) {

        Cli cli = new Cli();

        cli.option().shortName("a").with(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("aaaa");
                System.out.println(s);
            }
        });

        cli.accept(args);

    }

}
