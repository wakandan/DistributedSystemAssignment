import java.io.File;
import java.util.HashMap;

public class CommandListFile extends Command {

	final String homeDirectory = DIRECTORYHOME;
	File[] listOfFiles;

	public CommandListFile(HashMap<String, String> hashMap, Server server) {
		super(hashMap, server);
	}

	@Override
	public ReplyMessage execute() {
		String fileName = (hashMap.get(KEY_FILENAME) == null) ? homeDirectory
				: homeDirectory + hashMap.get(KEY_FILENAME);

		File file = new File(fileName);
		if (!file.exists()) {
			replyMessage.error = true;
			replyMessage.content = "file not exist";
			return replyMessage;
		}
		try {
			listOfFiles = file.listFiles();

			replyMessage.error = false;
			StringBuilder sb = new StringBuilder();
			if (listOfFiles.length > 0) {

				for (int i = 0; i < listOfFiles.length; i++) {
					sb.append(listOfFiles[i].getName() + ",");
				}
				sb.deleteCharAt(sb.length() - 1);

			}else{
				sb.append("<none>");
			}
			replyMessage.content = sb.toString();

		} catch (Exception e) {
			System.out.println("error reading file");
			e.printStackTrace();
		}

		return replyMessage;
	}
}
