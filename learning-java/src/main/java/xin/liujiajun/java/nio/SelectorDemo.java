package xin.liujiajun.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * @author LiuJiaJun
 * @date 2019/5/9 12:54
 */
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio/data.txt", "rw");
        FileChannel channel = aFile.getChannel();

        Selector selector = Selector.open();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()){
            SelectionKey next = iterator.next();
            next.selector();
            next.channel();
        }
        int opWrite = SelectionKey.OP_WRITE;
    }
}
