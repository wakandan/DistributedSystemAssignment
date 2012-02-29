import java.io.File;
import java.util.HashMap;


public class CommandRegister extends Command {
	public static HashMap<String, RegisterClientList> registerFileList = new HashMap<String, RegisterClientList>();
	public CommandRegister(HashMap<String, String> hashMap,Server server){
		this.hashMap = hashMap;
		replyMessage = new ReplyMessage();
		this.server = server;
	}
	@Override
	public ReplyMessage execute() {
		// TODO Auto-generated method stub
		String filePath = (String) hashMap.get(KEY_FILENAME);
		File file = new File(filePath);
		if (!file.exists()) {
			replyMessage.error = true;
			replyMessage.content = "file not exist";
			return replyMessage;
		}
		String intervalString = hashMap.get(INTERVAL);
		int interval = Integer.parseInt(intervalString);
		RegisterClientList clientList = registerFileList.get(filePath);
		if(clientList !=null){
			//already have in the list
			
			clientList.addRegisterClient(server.request,interval);
		}else{
			//haven't add to the list
			clientList = new RegisterClientList();
			clientList.addRegisterClient(server.request,interval);
			registerFileList.put(filePath,clientList);
		}
		replyMessage.error = false;
		replyMessage.content = "register successfully";
		return replyMessage;
	}

	@Override
	public String replyMessage(ReplyMessage replyMessage) {
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
