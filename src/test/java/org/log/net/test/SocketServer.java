/**
 * 
 */
package org.log.net.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhen.pan
 *
 */
public class SocketServer {

	
	public void startServer() throws IOException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				ServerSocket server = null;
				try {
					server = new ServerSocket(514);
					System.out.println("Start server on port:514");
					while(true) {
						Socket socket = server.accept();
						invoke(socket);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(null != server) {
						try {
							server.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		 }).start();
		
	}
	
	public void invoke(final Socket socket) {
		 new Thread(new Runnable() {
			@Override
			public void run() {
				java.io.BufferedInputStream in = null;
				try {
					in = new java.io.BufferedInputStream(socket.getInputStream());
//					System.out.println(".");
					byte[] cache = new byte[10240];
					int length = in.read(cache);
					while(length > 0) {
						System.out.println(new String(cache, 0, length));
						length = in.read(cache);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		 }).start();
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SocketServer server = new SocketServer();
		server.startServer();
	}

}
