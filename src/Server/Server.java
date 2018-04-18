package Server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server extends Thread {
	
	private final int porta;
	
	
	public Server(int porta) {
		this.porta = porta;
	}
	
	public void run() {
        String myIp = null;
        ServerTransmissor transmissor = new ServerTransmissor();
		
		try {
			// Criando socket do servido que é responsavel pro aceitar conexões
			ServerSocket serverSocket = new ServerSocket(this.porta);
            System.out.println("Servidor iniciado: " +  "Porta : " + porta);
			while(true) {
                System.out.println("Servidor Aguardando Conexão.");
				Socket clienteSocket = serverSocket.accept();
                System.out.println("Nova conexão " + clienteSocket);
                // A class ServerThread ira manipular a conexão com o servidor
				ServerThread handler = new ServerThread(clienteSocket, transmissor);
				if(handler != null)
					transmissor.addConnection(handler);
                handler.start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
