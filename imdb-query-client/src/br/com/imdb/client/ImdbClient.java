package br.com.imdb.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.Scanner;

public class ImdbClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
    	Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Search movie title:");
			String title = scanner.nextLine();

			 //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Searching on IMDB for title: "+title);
            oos.writeObject(buildPackage(title));
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            
            Optional<Object> message = Optional.ofNullable(ois.readObject());
            if (message.isPresent()) {
            	System.out.println(message.get().toString());            	
            } else {
            	System.err.println("Not found");
            }
            //close resources
            ois.close();
            oos.close();
			
			if (title.equals("quit")) {
				scanner.close();
				break;
			}
            Thread.sleep(100);
			
		}
        
    }

	private static String buildPackage(String title) {
		return title.trim().replaceAll("\\s", "").length()+":"+title;
	}
}
