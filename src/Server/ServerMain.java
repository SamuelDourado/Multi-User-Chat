package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class ServerMain {
	public static void main(String[] args) throws UnknownHostException {
		Server server = new Server(3566);
		server.start();
	}
}
