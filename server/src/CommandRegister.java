import java.io.File;
import java.util.HashMap;

public class CommandRegister extends Command {
	final String homeDirectory = DIRECTORYHOME;
	public static HashMap<String, RegisterClientList> registerFileList = new HashMap<String, RegisterClientList>();

	public CommandRegister(HashMap<String, String> hashMap, Server server) {
		super(hashMap, server);
	}

	@Override
	public ReplyMessage execute() {
		String filePath = homeDirectory+ (String) hashMap.get(KEY_FILENAME);
		File file = new File(filePath);
		
		if (!file.exists()) {
			replyMessage.error = true;
			replyMessage.content = "file not exist";
			return replyMessage;
		}
		
		String intervalString = hashMap.get(INTERVAL);
		int interval = Integer.parseInt(intervalString);
		RegisterClientList clientList = registerFileList.get(filePath);
		
		if (clientList != null) {
			// already have in the list
			clientList.addRegisterClient(server.request, interval);
		} else {
			// haven't add to the list
			clientList = new RegisterClientList();
			clientList.addRegisterClient(server.request, interval);
			registerFileList.put(filePath, clientList);
		}
		
		replyMessage.error = false;
		replyMessage.content = "register successfully";
		return replyMessage;
	}
}
