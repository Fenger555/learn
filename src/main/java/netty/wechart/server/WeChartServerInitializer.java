package netty.wechart.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author gaoxing
 * @Date 2020-06-16 20:47
 */
public class WeChartServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(
                new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()),
                new StringDecoder(),
                new StringEncoder(),
                new WeChartServerHandler()
        );
    }

}
