package com.cc.netty.ch08;

import java.nio.charset.Charset;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class DataGramBootstrap {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        
        Bootstrap bs = new Bootstrap();
        bs
        .group(group)
        .channel(NioDatagramChannel.class)
        //use UDP
        .handler(new SimpleChannelInboundHandler<DatagramPacket>() {
            @Override
            protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
                System.out.println("--------receive----------");
                System.out.println(msg.content().toString(Charset.forName("UTF-8")));
            }
        });
        //bind 9999 port
        bs.bind(9999).sync();
    }
}
