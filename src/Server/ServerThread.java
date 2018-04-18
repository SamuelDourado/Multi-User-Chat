package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread{
	private final Socket cliente;
	private final ServerTransmissor transmissor;
	
	private String nome = null;
	
	public OutputStream out = null;
	public InputStream input = null;
	
	public ServerThread(Socket cliente, ServerTransmissor transmissor) throws IOException {
		this.cliente = cliente;
		this.transmissor = transmissor;
		this.out = this.cliente.getOutputStream();
    	this.input = this.cliente.getInputStream();
	}
	
	public void run() {
            try {
            	this.setNome();
                this.handleClienteSocket();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
    /**
     * Este método manipula a conexão entre o cliente e o servidor
     * @throws IOException
     * @throws InterruptedException 
     */
    private void handleClienteSocket() throws IOException,InterruptedException{
    	// obtendo input e output apartir do socket do cliente 
    	BufferedReader reader = new BufferedReader(new InputStreamReader(this.input));
    	String line;
    	while ( (line = reader.readLine()) != null) {
    		this.transmissor.sendMessenger((line + "\n"),this);
    	}
    	// Fechar connection
        this.sair();
    }
    
    public void setNome() throws IOException {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(this.input));
    	this.out.write("Nome : ".getBytes());
    	try {
			this.nome = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public String getNome() {
    	return this.nome;
    }
    
    public Boolean send(String msg) throws IOException {
    	this.out.write(msg.getBytes());
    	return true;
    }
    
    protected void sair() throws IOException {
    	this.transmissor.removeConnection(this);
    	cliente.close();
    }
	
}

