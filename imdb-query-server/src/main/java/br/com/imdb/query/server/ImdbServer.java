package br.com.imdb.query.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import br.com.imdb.query.service.ImdbService;

public class ImdbServer {

	private static int port = 9876;
	
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		// create the socket server object
		ServerSocket server = new ServerSocket(port);
		// create new imdb service
		ImdbService imdb = new ImdbService();
		// keep listens indefinitely until receives 'exit' call or program terminates
		while (true) {
			System.out.println("Waiting for the client request");
			// creating socket and waiting for client connection
			Socket socket = server.accept();
			
			// read from socket to ObjectInputStream object
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			// convert ObjectInputStream object to String
			String message = (String) ois.readObject();
			
			System.out.println("Message Received: "+ message);
			if (message.equalsIgnoreCase("exit"))
				break;
			
			// create ObjectOutputStream object
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

			oos.writeObject(imdb.fetchHtml(message));

			ois.close();
			oos.close();
			socket.close();
			if (message.equalsIgnoreCase("exit"))
				break;
		}
		System.out.println("Shutting down Socket server!!");
		server.close();
	}
}
