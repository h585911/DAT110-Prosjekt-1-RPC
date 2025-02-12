package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessagingServer {

	// server-side socket for accepting incoming TCP connections
	private ServerSocket welcomeSocket;

	public MessagingServer(int port) {

		try {

			this.welcomeSocket = new ServerSocket(port);

		} catch (IOException ex) {

			System.out.println("Messaging server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// accept an incoming connection from a client
	public MessageConnection accept() throws IOException {

		MessageConnection connection = null;
	
	    // Sjekk at serveren kjører
	    if (welcomeSocket == null) {
	        throw new IOException("ServerSocket er ikke initialisert.");
	    }

		// accept TCP connection on welcome socket and create messaging connection to be returned
		Socket clientSocket = welcomeSocket.accept();
		connection = new MessageConnection(clientSocket);
		
		
		return connection;
	}
	

	public void stop() {

		if (welcomeSocket != null) {

			try {
				welcomeSocket.close();
			} catch (IOException ex) {

				System.out.println("Messaging server: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}
