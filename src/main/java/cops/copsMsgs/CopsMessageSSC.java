/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package copsMsgs;

import auxClasses.PacketAssembler;
import cops.*;
import copsObjs.CopsHandleObj;
import copsObjs.CopsCommonHeader;
import java.net.InetAddress;

/**
 *
 * @author badaro
 */
public class CopsMessageSSC {

	private boolean[] decisions;
	private CopsCommonHeader header;
	private CopsHandleObj handle;

	public CopsMessageSSC(short clientType, String ClientHandle, boolean[] decisions)
	{
		this.header = new CopsCommonHeader((byte) 10, clientType);
		this.handle = new CopsHandleObj(ClientHandle);
		this.decisions = decisions;
	}

	public byte[] Packet() {
		byte[] pacote = null;
		PacketAssembler assembler = new PacketAssembler();

		if (decisions[0] == true) {
			pacote = this.handle.Packet();
		}

		if (pacote == null) {
			header.setLength(header.Packet().length);
		}
		else {
			header.setLength(pacote.length + header.Packet().length);
		}
		pacote = assembler.Packet(header.Packet(), pacote);

		return pacote;
	}
}