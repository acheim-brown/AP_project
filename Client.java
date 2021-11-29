package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	
	private static final String Server_ip = "127.0.0.1";
	private static final int Server_Port = 9090;

	public static void main(String[] args) throws IOException{
		Socket socket = new Socket(Server_ip, Server_Port);
		
		BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String serverResponse = input.readLine();
	//	JOptionPane.showMessageDialog(null, "connected");
		
		socket.close();
		System.exit(0);
	}

}
