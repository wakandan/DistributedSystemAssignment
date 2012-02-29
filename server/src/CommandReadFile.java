import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class CommandReadFile extends Command {
	public CommandReadFile(HashMap<String, String> hashMap,Server server) {
		this.hashMap = hashMap;
		replyMessage = new ReplyMessage();
		this.server = server;
	}

	@Override
	public ReplyMessage execute() {
		// TODO Auto-generated method stub

		File file = new File((String) hashMap.get(KEY_FILENAME));
		if (!file.exists()) {
			replyMessage.error = true;
			replyMessage.content = "file not exist";
			return replyMessage;
		}
		try {
			int offset = Integer.parseInt((String) hashMap.get(KEY_OFFSET));
			int lenght = Integer.parseInt((String) hashMap.get(KEY_LENGTH));
			if (file.length() < offset + lenght) {
				System.out.println("error out of range");
				replyMessage.error = true;
				replyMessage.content = "error out of range";
				return replyMessage;
			}
			readFileAsString(file, offset, lenght, replyMessage);
		} catch (Exception e) {
			System.out.println("error reading file");
			e.printStackTrace();

		}

		return replyMessage;

	}

	private void readFileAsString(File filePath, int offset, int lenght,
			ReplyMessage replyMessage) throws java.io.IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[100];
		byte[] bufferByte = new byte[10000];
		int numRead = 0;
		int index = 0;
		while ((numRead = reader.read(buf)) != -1) {
			for (int i = 0; i < buf.length; i++) {
				bufferByte[index + i] = (byte) buf[i];

			}
			index += buf.length;
		}

		reader.close();
		byte[] returnByte = new byte[lenght];
		System.arraycopy(bufferByte, offset, returnByte, 0, lenght);
		replyMessage.error = false;
		replyMessage.sendByte = returnByte;

	}

	@Override
	public String replyMessage(ReplyMessage replyMessage) {
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

	@Override
	public String replyMessage() {
		// TODO Auto-generated method stub
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

}
