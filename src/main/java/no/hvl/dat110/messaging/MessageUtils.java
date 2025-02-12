package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		byte[] segment = null;
		byte[] data;

		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer

		segment = new byte[SEGMENTSIZE];
		data = message.getData();

		if (data == null || data.length > 127) {
			throw new IllegalArgumentException("Melding må være mellom 1 og 127 bytes.");
			// the first byte is to save the header
		}

		segment[0] = (byte) data.length;
		System.arraycopy(data, 0, segment, 1, data.length);

		return segment;

	}

	public static Message decapsulate(byte[] segment) {
		
		Message message = null;
		
		// decapsulate segment and put received payload data into a message
		
		if (segment.length > SEGMENTSIZE) {
			throw new IllegalArgumentException("Segmentet må ikke være over 128 bytes");
		}	
		
		int lengde = (int) segment[0];
		byte[] data = new byte[lengde];
		System.arraycopy(segment, 1, data, 0, lengde);
		message = new Message(data);
		
		return message;
		
	}
	
}


