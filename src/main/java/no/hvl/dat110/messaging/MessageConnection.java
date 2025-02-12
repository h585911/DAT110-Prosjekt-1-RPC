package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	/* Konstruktøren lager socket(start- og endepunkt. Så lager vi en inn- og utstream hvor vi kan sende 
	 * pakker mellom nodene og ruterene. TCP/IP protokollen er en toveis kommunikasjon derfor må vi ha to
	 * streamer. InputStream (DataInputStream) – For å motta data fra den andre enden. 
	 * OutputStream (DataOutputStream) – For å sende data til den andre enden.
	 */

	public void send(Message message) throws IOException {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        byte[] encoded = MessageUtils.encapsulate(message); // Pakk inn meldingen
        if (encoded == null) {
            throw new IOException("Encapsulation failed");
        }
        outStream.write(encoded);  // Send den ferdige pakken
        outStream.flush(); // Sørg for at alt blir sendt umiddelbart
    }


	public Message receive() throws IOException {
        byte[] received = new byte[MessageUtils.SEGMENTSIZE];  // Buffer for mottatt data
        inStream.readFully(received);  // Les hele meldingen
        if (received == null || received.length == 0) {
            throw new IOException("Received an empty message");
        }
        return MessageUtils.decapsulate(received); // Pakker ut og returnerer en Message
    }

	// close the connection by closing streams and the underlying socket	
	public void close() {
		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}

		


