import java.io.File;
import java.util.HashMap;

public class CommandDelete extends Command {
	public CommandDelete(HashMap<String, String> hashMap, Server server) {
		super(hashMap, server);
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
			if (file.delete()) {
				replyMessage.error = false;
				replyMessage.content = "Delete " + hashMap.get(KEY_FILENAME)
						+ " successfully";
			} else {
				replyMessage.error = true;
				replyMessage.content = "Delete " + hashMap.get(KEY_FILENAME)
						+ " fail";
			}

		} catch (Exception e) {
			System.out.println("error reading file");
			e.printStackTrace();

		}

		return replyMessage;
	}

}
