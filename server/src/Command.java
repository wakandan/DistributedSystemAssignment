import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Command implements Constants {
	public HashMap<String, String> hashMap;
	public ReplyMessage replyMessage;
	public Server server;

	public Command(HashMap<String, String> hashMap, Server server) {
		this.hashMap = hashMap;
		this.replyMessage = new ReplyMessage();
		this.server = server;
	}

	/*
	 * generate the corresponding command based on the data that client sent
	 */
	public static Command setCommand(String commandString, Server server) {
		System.out.println("content " + commandString);
		StringTokenizer st = new StringTokenizer(commandString, DELIM);
		HashMap<String, String> hashMapStatic = new HashMap<String, String>();
		
		while (st.hasMoreElements()) {
			String stringPart = st.nextToken().toString();
			StringTokenizer st1 = new StringTokenizer(stringPart, ":");
			String key = st1.nextToken();
			if (key.equals(KEY_CMD_END))
				break;
			String value = st1.nextToken();
			hashMapStatic.put(key, value);
		}
		
		List<String> keys = new ArrayList<String>(hashMapStatic.keySet());
		for (String key : keys) {
			System.out.println("key = " + key + " value= "
					+ hashMapStatic.get(key));
		}
		
		String commandName = (String) hashMapStatic.get(KEY_CMD);
		if (commandName.equals(VAL_CMD_READFILE)) {
			return new CommandReadFile(hashMapStatic, server);
		} else if (commandName.equals(VAL_CMD_WRITEFILE)) {
			return new CommandWriteFile(hashMapStatic, server);
		} else if (commandName.equals(VAL_CMD_REGISTER)) {
			return new CommandRegister(hashMapStatic, server);
		} else if (commandName.equals(VAL_CMD_GETDIRECTORY)) {
			return new CommandListFile(hashMapStatic, server);
		} else if (commandName.equals(VAL_CMD_DELETE)) {
			return new CommandDelete(hashMapStatic, server);
		} else
			return null;
	}

	/*
	 * execute the commmand
	 */
	public abstract ReplyMessage execute();

	/*
	 * generate the reply message to send to client
	 */
	public String replyMessage(ReplyMessage message) {
		StringBuilder sb = new StringBuilder();
		if (message.error) {
			sb.append(Constants.KEY_STATUS + ":" + Constants.VAL_STATUS_ERROR
					+ DELIM);
		} else {
			sb.append(Constants.KEY_STATUS + ":" + Constants.VAL_STATUS_OK
					+ DELIM);
		}
		sb.append(Constants.KEY_CONTENT + ":" + message.content);
		return sb.toString();
	}

	public String replyMessage() {
		return replyMessage(replyMessage);
	}

}
