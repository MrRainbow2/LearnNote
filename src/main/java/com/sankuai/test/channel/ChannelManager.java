package com.sankuai.test.channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/6/5 15:05
 */
public class ChannelManager {

    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("10.24.36.177", 8230);
        Channel channel = ChannelManager.getChannel(inetSocketAddress);
        System.out.println(channel);
    }

    public static Channel getChannel(InetSocketAddress inetSocketAddress) {
        Channel channel = null;
        if (null == channel) {
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.SO_KEEPALIVE, true);
                channel = bootstrap.connect(inetSocketAddress.getHostName(), inetSocketAddress.getPort()).sync()
                        .channel();
            } catch (Exception e) {
            }
        }
        return channel;
    }
}
