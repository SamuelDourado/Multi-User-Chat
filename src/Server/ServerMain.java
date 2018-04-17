package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMain {
	public static void main(String[] args) {
		int porta = 3566;
		try {
			// Criando socket do servido que é responsavel pro aceitar conections
			ServerSocket serverSocket = new ServerSocket(porta);
			while(true) {
				Socket clienteSocket = serverSocket.accept();
				OutputStream out = clienteSocket.getOutputStream();
				out.write("Hello World \n".getBytes());
				clienteSocket.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
