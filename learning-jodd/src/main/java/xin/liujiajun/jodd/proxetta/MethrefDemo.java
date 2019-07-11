package xin.liujiajun.jodd.proxetta;

import jodd.methref.Methref;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-23 16:33
 **/
public class MethrefDemo {
    public static void main(String[] args) {
        Methref<Foo> meth = Methref.on(Foo.class);

        meth.to().say();

        System.out.println(meth.ref());

    }
}
