package ch11;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WebSocketServer {

    public static void main(String[] args) throws InterruptedException {
        WebSocketServer server = new WebSocketServer();
        server.start(9999);
    }

    public void start(int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
        ServerBootstrap bs = new ServerBootstrap();
        bs
            .channel(NioServerSocketChannel.class)
            .group(group)
            .localAddress(port)
            .childHandler(new WebSocketServerInitializer());
        ChannelFuture f = bs.bind().sync();
        f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().syncUninterruptibly();
        }
    }
}
