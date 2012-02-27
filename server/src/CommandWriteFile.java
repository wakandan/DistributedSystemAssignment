import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class CommandWriteFile extends Command {
	public CommandWriteFile(HashMap<String, String> hashMap) {
		this.hashMap = hashMap;
		replyMessage = new ReplyMessage();
	}

	public ReplyMessage execute() {

		File file = new File((String) hashMap.get(KEY_FILENAME));
		if (!file.exists()) {
			replyMessage.error = true;
			replyMessage.content = "file not exist";
			return replyMessage;
		}
		try {
			int offset = Integer.parseInt((String) hashMap.get(KEY_OFFSET));

			String insertText = (String) hashMap.get(KEY_CONTENT);
			System.out.println("hashMap " + insertText);
			if (file.length() < offset) {
				System.out.println("error out of range");
				replyMessage.error = true;
				replyMessage.content = "error out of range";
				return replyMessage;
			}
			writeFileAsString(file, offset, insertText, replyMessage);
		} catch (Exception e) {
			System.out.println("error reading file");
			e.printStackTrace();
		}
		return replyMessage;

	}

	private void writeFileAsString(File filePath, int offset, String text,
			ReplyMessage replyMessage) throws java.io.IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[100];
		char[] bufferByte = new char[1000];
		int numRead = 0;
		int index = 0;
		long fileLength = filePath.length();
		while ((numRead = reader.read(buf)) != -1) {
			for (int i = 0; i < buf.length; i++) {
				bufferByte[index + i] = buf[i];

			}
			index += buf.length;
		}

		reader.close();
		FileWriter fstream = new FileWriter("abc", false);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(bufferByte, 0, offset);
		out.write(text);
		out.write(bufferByte, offset, (int) (fileLength - offset));
		out.close();
		replyMessage.error = false;
		replyMessage.content = "Successfull add";
	}

	public String replyMessage(ReplyMessage replyMessage) {
		StringBuilder sb = new StringBuilder();

		if (replyMessage.error) {
			sb.append(Constants.KEY_STATUS + ":" + Constants.VAL_STATUS_ERROR
					+ DELIM);

		} else {
			sb.append(Constants.KEY_STATUS + ":" + Constants.VAL_STATUS_OK
					+ DELIM);
		}
		sb.append(Constants.KEY_CONTENT + ":" + replyMessage.content);
		return sb.toString();
	}

	@Override
	public String replyMessage() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		if (replyMessage.error) {
			sb.append(Constants.KEY_STATUS + ":" + Constants.VAL_STATUS_ERROR
					+ DELIM);
		} else {
			sb.append(Constants.KEY_STATUS + ":" + Constants.VAL_STATUS_OK
					+ DELIM);
		}
		sb.append(Constants.KEY_CONTENT + ":" + replyMessage.content);
		return sb.toString();
	}

}
