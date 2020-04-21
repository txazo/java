package org.txazo.wyot.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageToByteEncoderAdapter extends MessageToByteEncoder {

    private ByteEncoder encoder;

    public MessageToByteEncoderAdapter(ByteEncoder encoder) {
        super();
        this.encoder = encoder;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        encoder.encode(ctx, msg, out);
    }

}
