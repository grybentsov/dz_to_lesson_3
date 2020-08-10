package Serialization;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClient connected");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClient disconnected");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
        System.out.println(message.getClass().getName());
        if (message instanceof MyMessage) {
            System.out.println("MyClient text message: " + ((MyMessage) message).getText());
            ctx.writeAndFlush(new MyMessage("Hello MyClient!"));
        } else {
            System.out.printf("MyServer received wrong object!!!");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
