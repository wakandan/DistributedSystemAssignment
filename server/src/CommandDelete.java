import java.io.File;
import java.util.HashMap;


public class CommandDelete extends Command {
	public CommandDelete(HashMap<String, String>hashMap,Server server){
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
			if(file.delete()){
				replyMessage.error = false;
				replyMessage.content = "Delete "+hashMap.get(KEY_FILENAME)+" successfully";
			}else{
				replyMessage.error = true;
				replyMessage.content = "Delete "+hashMap.get(KEY_FILENAME)+" fail";
			}
			
		} catch (Exception e) {
			System.out.println("error reading file");
			e.printStackTrace();

		}

		return replyMessage;
	}

	@Override
	public String replyMessage(ReplyMessage message) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		if (replyMessage.error) {
			sb.append(Constants.KEY_STATUS + ":" + Constants.VAL_STATUS_ERROR
					+ DELIM);
		} else {
			sb.append(Constants.KEY_STATUS + ":" + Constants.VAL_STATUS_OK
					+ DELIM);
		}
		sb.append(Constants.KEY_CONTENT + ":" + message.content);
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
