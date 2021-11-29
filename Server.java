package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static String cust_id;
	private static final int PORT = 9090;

	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(PORT);
		
		System.out.println("[SERVER] Waiting for Client Connection");
		Socket client = listener.accept();
		System.out.println("[SERVER] Client Connected");
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		//try {
			
			MainPage frame = new MainPage();
			frame.setVisible(true);
			
			System.out.println("[SERVER] Connection Closed");
			client.close();
			listener.close();
			
			
			
			
		//}catch(Exception e)
      	
		
	}
}
