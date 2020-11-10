package netty.wechart.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author gaoxing
 * @Date 2020-06-16 20:52
 */
public class WeChartServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "：" + msg);
        channelGroup.forEach(c -> {
            if(c == channel) {
                c.writeAndFlush("Me：" + msg);
            } else {
                c.writeAndFlush(channel.remoteAddress() + "：" + msg);
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String msg = channel.remoteAddress() + " - On Line";
        System.out.println(msg);
        channelGroup.writeAndFlush(msg);
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String msg = channel.remoteAddress() + " - Down Line";
        System.out.println(msg);
        channelGroup.writeAndFlush(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}
