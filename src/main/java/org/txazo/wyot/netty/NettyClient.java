package org.txazo.wyot.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class NettyClient {

    private static final Logger LOGGER = LogManager.getLogger(NettyClient.class);

    private volatile boolean closed = false;
    private String host;
    private int port;
    private int reconnectTimes;
    private int maxReconnectTimes;
    private int reconnectInterval;
    private EventLoopGroup group;
    private ByteEncoder encoder;
    private ChannelFuture channelFuture;

    public NettyClient(String host, int port, int maxReconnectTimes, int reconnectInterval, EventLoopGroup group, ByteEncoder encoder) {
        this.host = host;
        this.port = port;
        this.maxReconnectTimes = maxReconnectTimes;
        this.reconnectInterval = reconnectInterval;
        this.group = group;
        this.encoder = encoder;
    }

    public void connect() {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new MessageToByteEncoderAdapter(encoder))
                                    .addLast(
                                            new IdleStateHandler(10, 10, 10, TimeUnit.SECONDS),
                                            new ChannelInboundHandlerAdapter() {

                                                @Override
                                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                                    reconnectTimes = 0;
                                                    super.channelActive(ctx);
                                                }

                                                @Override
                                                public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                                    if (!closed && reconnectTimes < maxReconnectTimes) {
                                                        reconnectTimes++;
                                                        ctx.channel().eventLoop().schedule(() -> reconnect(), reconnectInterval, TimeUnit.SECONDS);
                                                    }
                                                    super.channelInactive(ctx);
                                                }

                                            }
                                    );
                        }

                    });
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);


            ChannelFuture channelFuture = bootstrap.connect(host, port)
                    .addListener(new ChannelFutureListener() {

                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            if (!future.isSuccess()) {
                                future.channel().pipeline().fireChannelInactive();
                            }
                        }

                    }).sync();

            ChannelFuture oldChannelFuture = this.channelFuture;
            if (oldChannelFuture != null && oldChannelFuture.channel() != null) {
                try {
                    oldChannelFuture.channel().close();
                } catch (Exception e) {
                    LOGGER.error("Close old channel error", e);
                }
            }

            this.channelFuture = channelFuture;
        } catch (Throwable t) {
            LOGGER.error("Netty Client connect error", t);
        }
    }

    private void reconnect() {
        connect();
    }

    public void send(Object msg) throws NettyRemotingException {
        if (!closed && channelFuture != null) {
            Channel channel = channelFuture.channel();
            if (channel != null && channel.isActive()) {
                channel.writeAndFlush(msg);
                return;
            }
        }

        throw new NettyRemotingException("Channel is unavailable");
    }

    public void close() {
        if (!closed) {
            closed = true;
            if (channelFuture != null) {
                Channel channel = channelFuture.channel();
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (Exception e) {
                        LOGGER.error("Close channel error", e);
                    }
                }
            }
        }
    }

}
