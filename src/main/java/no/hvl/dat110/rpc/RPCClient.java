package no.hvl.dat110.rpc;

import java.io.IOException;
import java.net.UnknownHostException;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() throws UnknownHostException, IOException {
		
		// TODO - START
		// connect using the RPC client
		connection = msgclient.connect();
		
		if (connection == null) {
			throw new UnsupportedOperationException("Kunne ikke koble til serveren");
		}
		// TODO - END
	}
	
	public void disconnect() {
		
		// TODO - START
		if (connection == null) {
			throw new UnsupportedOperationException("Feil ved lukking av tilkobling");
		}
	
		// disconnect by closing the underlying messaging connection
		connection.close();
		connection = null;
		// TODO - END
	}

	/*
	 Make a remote call om the method on the RPC server by sending an RPC request message and receive an RPC reply message

	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called
	 */

	public byte[] call(byte rpcid, byte[] param) {
		
		byte[] returnval = null;
		
		// TODO - START

		/*

		The rpcid and param must be encapsulated according to the RPC message format

		The return value from the RPC call must be decapsulated according to the RPC message format

		*/
				
		if (true)
			throw new UnsupportedOperationException(TODO.method());
		
		// TODO - END
		return returnval;
		
	}
	
	/*
		1️⃣ Implementer RPCClient (for tilkobling og kall).
		2️⃣ Fullfør RPCUtils (for serialisering og deserialisering).
		3️⃣ Fullfør RPCServer (for mottak og behandling av RPC-kall).
		4️⃣ Fullfør RPCServerStopImpl (for å stoppe serveren).
		5️⃣ Fullfør RPCClientStopStub (for å kalle stop() fra klienten).
		6️⃣ Test systemet ved å koble en klient til serveren og sende et RPC-kall.
		
	 */

}
