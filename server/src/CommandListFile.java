import java.io.File;
import java.util.HashMap;

public class CommandListFile extends Command {
	final String homeDirectory = "/Users/yewsoonong/Downloads/dropbox/Dropbox/";
	File[] listOfFiles;

	public CommandListFile(HashMap<String, String> hashMap, Server server) {
		super(hashMap, server);
	}

	@Override
	public ReplyMessage execute() {
		// TODO Auto-generated method stub
		String fileName = (hashMap.get(KEY_FILENAME) == null) ? homeDirectory
				: homeDirectory + hashMap.get(KEY_FILENAME);

		File file = new File(fileName);
		if (!file.exists()) {
			replyMessage.error = true;
			replyMessage.content = "file not exist";
			return replyMessage;
		}
		try {
			System.out.println("in the list file");

			listOfFiles = file.listFiles();
			replyMessage.error = false;
			StringBuilder sb = new StringBuilder();
			if (listOfFiles.length > 0) {

				for (int i = 0; i < listOfFiles.length; i++) {
					sb.append(listOfFiles[i].getName() + ",");
				}
				sb.deleteCharAt(sb.length() - 1);

			}
			replyMessage.content = sb.toString();

		} catch (Exception e) {
			System.out.println("error reading file");
			e.printStackTrace();

		}

		return replyMessage;
	}
}
