import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Command implements Constants {
	public HashMap<String, String> hashMap;
	public ReplyMessage replyMessage;
	public Server server;
	public static Command setCommand(String commandString,Server server) {
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
			return new CommandReadFile(hashMapStatic,server);
		} else if (commandName.equals(VAL_CMD_WRITEFILE)) {
			return new CommandWriteFile(hashMapStatic,server);
		} else if (commandName.equals(VAL_CMD_REGISTER)){
			return new CommandRegister(hashMapStatic,server);
		} else if (commandName.equals(VAL_CMD_GETDIRECTORY)) {
			return new CommandListFile(hashMapStatic,server);
		}else
			return null;
	}

	public abstract ReplyMessage execute();

	public abstract String replyMessage(ReplyMessage message);

	public abstract String replyMessage();

}
