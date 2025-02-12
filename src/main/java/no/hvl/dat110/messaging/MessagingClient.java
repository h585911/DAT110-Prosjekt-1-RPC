package no.hvl.dat110.messaging;


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import no.hvl.dat110.TODO;

public class MessagingClient {

	// name/IP address of the messaging server
	private String server;

	// server port on which the messaging server is listening
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	// setup of a messaging connection to a messaging server
	public MessageConnection connect () throws UnknownHostException, IOException {

		// client-side socket for underlying TCP connection to messaging server
		Socket clientSocket;
		MessageConnection connection = null;
		
		// connect to messaging server using a TCP socket
		clientSocket = new Socket(server, port);
		
		// create and return a corresponding messaging connection
		connection = new MessageConnection(clientSocket);
		
		if (!clientSocket.isConnected() || connection == null) {
			throw new UnsupportedOperationException("Failed to establish connection to server.");
		}
		
		
		return connection;
	}
}
