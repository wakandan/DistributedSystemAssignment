import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.filechooser.FileSystemView;
import java.nio.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Server implements Constants {
	public static void main(String args[]) {
		DatagramSocket aSocket = null;
		Command command = new Command();
		try {

			aSocket = new DatagramSocket(6789);
			byte[] buffer = new byte[1000];
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer,
						buffer.length);

				aSocket.receive(request);
				String receiveCommand = new String(request.getData());
				command.setCommand(receiveCommand);

				ReplyMessage replyMessage = command.execute();
				DatagramPacket reply;
				String sendMessageString = replyString(replyMessage);
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

	private static String replyString(ReplyMessage replyMessage) {
		StringBuilder sb = new StringBuilder();
		if (replyMessage.error) {
			sb.append(Constants.KEY_STATUS + ":" + Constants.VAL_STATUS_ERROR
					+ DELIM);
			sb.append(Constants.KEY_CONTENT + ":" + replyMessage.content);

		} else {
			sb.append(Constants.KEY_STATUS + ":" + Constants.VAL_STATUS_OK
					+ DELIM);
			sb.append(Constants.KEY_CONTENT + ":"
					+ new String(replyMessage.sendByte));
		}
		return sb.toString();
	}

	private static byte[] readFileAsString(File filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[100];
		byte[] returnByte = new byte[1000];
		int numRead = 0;
		int index = 0;
		while ((numRead = reader.read(buf)) != -1) {
			for (int i = 0; i < buf.length; i++) {
				returnByte[index + i] = (byte) buf[i];

			}
			index += buf.length;
		}
		reader.close();

		return returnByte;
	}

}