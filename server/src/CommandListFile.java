import java.io.File;
import java.util.HashMap;


public class CommandListFile extends Command {
	 File[] listOfFiles;
	public CommandListFile(HashMap<String, String> hashMap,Server server) {
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
			
			 
			  listOfFiles = file.listFiles(); 
			  replyMessage.error = false;
//			readFileAsString(file, offset, lenght, replyMessage);
		} catch (Exception e) {
			System.out.println("error reading file");
			e.printStackTrace();

		}

		return replyMessage;
	}

	@Override
	public String replyMessage(ReplyMessage message) {
		// TODO Auto-generated method stub
		return null;
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
			if(listOfFiles.length>0){
				sb.append(Constants.KEY_CONTENT + ":");
				for(int i=0;i<listOfFiles.length;i++){
					sb.append(listOfFiles[i].getName()+",");
				}
				sb.deleteCharAt(sb.length()-1);
			}
		}
		return sb.toString();
	}

}
