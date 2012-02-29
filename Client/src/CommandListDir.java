import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 */

/**
 * @author akai
 * 
 */
public class CommandListDir extends Command implements Constants {
	public String	directory;

	public CommandListDir() {
		this.cmdName = VAL_CMD_GETDIRECTORY;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_FILENAME + ":" + directory + DELIM);
		return wrapRequest(sb);
	}

	/* (non-Javadoc)
	 * 
	 * @see Command#requestData() */
	@Override
	public void requestData() {
		System.out.print("[info] Please enter dir name: ");
		Scanner sc = new Scanner(System.in);
		directory = sc.next();

	}

	@Override
	public boolean processReply() {
		if (!super.processReply())
			return false;
		if (request.status.equalsIgnoreCase(VAL_STATUS_ERROR)) {
			System.out.println("[error] Error listing directory");
			return false;
		}

		StringTokenizer st = new StringTokenizer(request.content, ",");
		while (st.hasMoreElements()) {
			System.out.println(st.nextToken());
		}

		return true;
	}

}
