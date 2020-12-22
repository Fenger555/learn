package nio.impl.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author Fenger
 * @date 2020-12-21 11:01
 */
public class Reactor implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {

        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel
                .bind(new InetSocketAddress(port))
                .configureBlocking(false);

        // 监听ACCEPT事件
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 绑定时间对应处理单元
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                // 阻塞
                selector.select();
                // 获取事件
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    // 分发事件
                    dispatch(iter.next());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey sk) {
        Runnable r = (Runnable) sk.attachment();
        if (r != null) {
            r.run();
        }
    }

    class Acceptor implements Runnable {
        @Override
        public void run() {

            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
