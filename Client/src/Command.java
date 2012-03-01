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
	public String	cmdName;
	public byte[]	buffer;
	public Request	request;

	/* indicate whether the command has been served. client will only send out
	 * command when this is NOT set. Otherwise, it will proceed as normal */
	public boolean	isServed	= false;

	public Command() {
		buffer = new byte[BUFFER_SIZE];
	}

	public abstract String toString();

	public abstract void requestData();

	public String wrapRequest(StringBuilder request) {
		StringBuilder sb = new StringBuilder();
		sb.append(INDEX_COMMAND + ":" + Client.indexCommand + DELIM);
		sb.append(KEY_CMD + ":" + cmdName + DELIM);
		sb.append(request);
		sb.append(KEY_CMD_END + ":");
		return sb.toString();
	}

	/* Split a reply into a hashmap, then this will be used by concrete sub
	 * classes, such as CommandReadFile or CommandWriteFile */
	public boolean processReply() {
		request = Request.read(buffer);
		System.out.println("[status] " + request.status);
		isServed = true;
		if (request.status.equalsIgnoreCase(VAL_STATUS_OK))
			return true;
		else
			System.out.println("[reason] " + request.content);
		return false;
	}
}
