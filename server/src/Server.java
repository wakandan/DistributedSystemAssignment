import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


import javax.swing.filechooser.FileSystemView;
import java.nio.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Server implements Constants {
	public static int port = 6789;

	
	public static void main(String args[]) {
		DatagramSocket aSocket = null;
		Command command ;
		try {


			aSocket = new DatagramSocket(port);
			byte[] buffer = new byte[1000];
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer,
						buffer.length);

				aSocket.receive(request);
				String receiveCommand = new String(request.getData());
				command = Command.setCommand(receiveCommand);

				ReplyMessage replyMessage = command.execute();
				DatagramPacket reply;
				String sendMessageString = command.replyMessage(replyMessage);
				System.out.println("reply content " + sendMessageString);
				reply = new DatagramPacket(sendMessageString.getBytes(),
						sendMessageString.getBytes().length,
						request.getAddress(), request.getPort());

				aSocket.send(reply);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
	}



	
}
