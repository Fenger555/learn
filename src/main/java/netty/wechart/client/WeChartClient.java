package netty.wechart.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author gaoxing
 * @Date 2020-06-17 09:17
 */
public class WeChartClient {

    public static void main(String[] args) {
        EventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new WeChartClientInitializer());

        try {
            Channel channel = bootstrap.connect("localhost", 9999).sync().channel();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String line = br.readLine();
                channel.writeAndFlush(line + "\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
