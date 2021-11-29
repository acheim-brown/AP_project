package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{

	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	public ClientHandler(Socket clientsocket) throws IOException{
		this.client = clientsocket;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		MainPage frame = new MainPage();
		frame.setVisible(true);
	}
	@Override
	public void run() {
		
		
		// TODO Auto-generated method stub
		
	}

}
