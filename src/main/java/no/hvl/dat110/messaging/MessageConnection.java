package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;


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

	public void send(Message message) {

		byte[] data;
		
		// encapsulate the data contained in the Message and write to the output stream
		
		data = MessageUtils.encapsulate(message);
		
		try {
			outStream.write(data);
			outStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Message receive() {
		
		Message message = null;
	    byte[] data;
	    
	    
		// read a segment from the input stream and decapsulate data into a Message
		
	    data = new byte[128];
		try {
	        int bytesRead = inStream.read(data);
	        byte[] actualData = Arrays.copyOf(data, bytesRead);   
	        message = MessageUtils.decapsulate(actualData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
		
		
		
		/* readAllBytes() gjorde at testene ikke funket så måtte bruke read istedenfor^^
		 * 
		Message message = null;
		 
	    try {
	        byte[] data = inStream.readAllBytes(); 
	        // Decapsuler meldingen
	        message = MessageUtils.decapsulate(data);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		    return message;
		    
		*/    
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