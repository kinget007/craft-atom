package org.craft.atom.nio;

import java.net.SocketException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SocketChannel;

import org.craft.atom.nio.api.AbstractConfig;
import org.craft.atom.nio.spi.SizePredictor;

/**
 * @author Hu Feng
 * @version 1.0, 2012-2-26
 */
public class TcpSession extends AbstractSession {
	
	private SocketChannel socketChannel;

	public TcpSession(SocketChannel socketChannel, AbstractConfig config, SizePredictor sizePredictor) {
		super(config, sizePredictor);
		this.socketChannel = socketChannel;
		this.localAddress = socketChannel.socket().getLocalSocketAddress();
		this.remoteAddress = socketChannel.socket().getRemoteSocketAddress();
		try {
			this.socketChannel.socket().setReceiveBufferSize(config.getReadBufferSize());
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public SelectableChannel getChannel() {
		return socketChannel;
	}

}