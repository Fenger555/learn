package nio;

import java.nio.IntBuffer;

/**
 * @Author gaoxing
 * @Date 2020-06-17 17:47
 */
public class NioTest {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i=1; i<5; i++) {
            System.out.println("size: " + buffer.remaining());
            buffer.put(i);
        }

        buffer.flip();


        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

        buffer.flip();
        buffer.put(4);
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());

    }
}
