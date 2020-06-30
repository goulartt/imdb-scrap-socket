package br.com.imdb.query.server;
import java.io.IOException;

public class ImdbServerMain {

	
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		ImdbSocket socket = new ImdbSocket();
		socket.run();
	}
}
