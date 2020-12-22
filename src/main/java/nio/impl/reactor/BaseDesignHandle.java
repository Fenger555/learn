package nio.impl.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author Fenger
 * @date 2020-12-21 11:03
 */
public class BaseDesignHandle implements Runnable {

    private SocketChannel socketChannel;
    private SelectionKey selectionKey;

    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);

    private State state = State.READING;

    public BaseDesignHandle(Selector selector, SocketChannel socketChannel) throws IOException {

        this.socketChannel = socketChannel;
        socketChannel.configureBlocking(false);

        selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
        selectionKey.attach(this);

        selector.wakeup();

    }

    @Override
    public void run() {
        switch (state) {
            case READING:
                read();
                break;
            case SENDING:
                send();
                break;
            default:
                throw new RuntimeException("no supported");
        }
    }

    private void read() {
        try {
            socketChannel.read(input);
            if (inputIsComplete()) {
                // todo: main logic
                process();
                state = State.SENDING;
                selectionKey.interestOps(SelectionKey.OP_WRITE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send() {
        try {
            socketChannel.write(output);
            if (outputIsComplete()) {
                selectionKey.cancel();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean inputIsComplete() {
        return false;
    }

    private boolean outputIsComplete() {
        return false;
    }

    private void process() {

    }

    private enum State {
        READING(0),
        SENDING(1);

        State(int state) {
        }
    }

}
