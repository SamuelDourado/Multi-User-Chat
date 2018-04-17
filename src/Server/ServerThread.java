package Server;

import java.net.Socket;

public class ServerThread extends Thread{
	private final Socket cliente;
	
	public ServerThread(Socket cliente) {
		this.cliente = cliente;
	}
	
	public void run() {
		
	}
	
}
