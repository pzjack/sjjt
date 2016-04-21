/**
 * 
 */
package org.log.net.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author zhen.pan
 *
 */
public class SocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket  socket = null;
		socket = new Socket();
		SocketAddress socketAddress = new InetSocketAddress("localhost", 514);
		try {
			socket.connect(socketAddress, 10 * 1000);
			
			java.io.BufferedOutputStream out = null;
			out = new java.io.BufferedOutputStream(socket.getOutputStream());
			out.write("Send a Message from socket Client.".getBytes());
			out.flush();
			out.close();
			
//			java.io.BufferedInputStream in = null;
//			try {
//				in = new java.io.BufferedInputStream(socket.getInputStream());
//				byte[] cache = new byte[10240];
//				int length = in.read(cache);
//				while(length > 0) {
//					System.out.println(new String(cache, 0, length));
//					length = in.read(cache);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}