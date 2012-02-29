import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 */

/**
 * @author akai
 * 
 */
public abstract class Command implements Constants {
	public String					cmdName;
	public HashMap<String, String>	reply;
	public byte[]					buffer;

	public Command() {
		reply = new HashMap<String, String>();
		buffer = new byte[BUFFER_SIZE];
	}

	public abstract String toString();

	public abstract void requestData();

	public String wrapRequest(StringBuilder request) {
		StringBuilder sb = new StringBuilder();
		sb.append(INDEX_COMMAND + ":"+Client.indexCommand+DELIM);
		sb.append(KEY_CMD + ":" + cmdName + DELIM);
		sb.append(request);
		sb.append(KEY_CMD_END + ":");
		return sb.toString();
	}

	/* Split a reply into a hashmap, then this will be used by concrete sub
	 * classes, such as CommandReadFile or CommandWriteFile */
	public boolean processReply() {
		String commandString = new String(buffer);
		StringTokenizer st = new StringTokenizer(commandString, DELIM);
		while (st.hasMoreElements()) {
			String stringPart = st.nextToken().toString();

			StringTokenizer st1 = new StringTokenizer(stringPart, ":");
			String key = st1.nextToken();
			if (key.equals(KEY_CMD_END))
				break;
			String value = st1.nextToken();
			reply.put(key, value);
		}
		if (!((String) reply.get(KEY_STATUS)).equalsIgnoreCase(VAL_STATUS_OK)) {
			System.out.println("[error] Command failed");
			System.out.println("[status] " + reply.get(KEY_STATUS));
			System.out.println("[reason] " + reply.get(KEY_CONTENT));
			return false;
		}else {			
			System.out.println("[status] " + reply.get(KEY_STATUS));
			System.out.println("[reason] " + reply.get(KEY_CONTENT));
		}
		return true;
	}
}
