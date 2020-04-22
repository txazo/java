package org.txazo.wyot.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public interface ByteEncoder {

    void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception;

}
