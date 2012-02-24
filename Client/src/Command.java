import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	/* Split a reply into a hashmap, then this will be used by concrete sub
	 * classes, such as CommandReadFile or CommandWriteFile */
	public boolean processReply() {
		String commandString = buffer.toString();
		StringTokenizer st = new StringTokenizer(commandString, DELIM);
		while (st.hasMoreElements()) {
			String stringPart = st.nextToken().toString();
			System.out.println(stringPart);

			StringTokenizer st1 = new StringTokenizer(stringPart, ":");
			String key = st1.nextToken();
			if (key.equals(KEY_CMD_END))
				break;
			String value = st1.nextToken();
			reply.put(key, value);
		}
		if (!((String) reply.get(KEY_STATUS)).equalsIgnoreCase(VAL_STATUS_OK)) {
			return false;
		}
		return true;
	}
}
