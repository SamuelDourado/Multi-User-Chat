package Server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServerTransmissor {
	private ArrayList<ServerThread> connections = new ArrayList<ServerThread>();
	
	public void addConnection(ServerThread newServerThread) {
		this.connections.add(newServerThread);
	}
	
	public void removeConnection(ServerThread serverThread) {
		this.connections.remove(serverThread);
	}
	
	protected Boolean sendMessenger(String msg, ServerThread remetente) throws IOException {
		for(ServerThread connetion: this.connections) {
			if(connetion != remetente)
				connetion.send(this.getHeader(remetente.getNome()) + msg);
		}
		return true;
	}
	
	protected String getHeader(String nome) {
		SimpleDateFormat ft = new SimpleDateFormat ("hh:mm");
    	String data = ft.format(new Date());
		return nome + " " + data + " : ";
	}

}
