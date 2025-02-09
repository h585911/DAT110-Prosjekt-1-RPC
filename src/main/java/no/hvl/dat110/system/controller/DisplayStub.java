package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {
		
		// TODO - START
		
		// implement marshalling, call and unmarshalling for write RPC method
		
		byte[] msg = RPCUtils.marshallString(message);
		
		byte rpcid = (byte) Common.WRITE_RPCID;
		byte[] result = rpcclient.call(rpcid, msg); //sender data (temperaturen) til serveren (display)
		
		if (result != null) {
			String decoded = RPCUtils.unmarshallString(result); //denne brukes ikke men stod i oppgaven at vi må unmarshalle
		}
		
		// TODO - END
		
	}
}
