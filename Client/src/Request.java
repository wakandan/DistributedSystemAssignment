import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author akai
 * 
 */

public class Request implements Constants {
	public String	status;
	public String	content;

	public String toRequestString() {
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_STATUS + ":" + status + "\n");
		sb.append(KEY_CONTENT + ":" + content);
		return sb.toString();
	}

	public static Request read(byte[] buffer) {
		Request request = new Request();
		String commandString = new String(buffer);
		StringTokenizer st = new StringTokenizer(commandString, DELIM);
		while (st.hasMoreElements()) {
			String stringPart = st.nextToken().toString();

			StringTokenizer st1 = new StringTokenizer(stringPart, ":");
			String key = st1.nextToken();
			String value = st1.nextToken();
			if (key.equalsIgnoreCase(KEY_STATUS))
				request.status = value;
			else
				request.content = value;
		}
		return request;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[status] " + status + "\n");
		sb.append("[content] " + content + "\n");
		return sb.toString();
	}
}
