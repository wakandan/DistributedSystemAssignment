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
	public CommandListDir(){
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
		System.out.print("[info] Please enter : ");
		Scanner sc = new Scanner(System.in);
		directory = sc.next();

	}
	@Override
	public boolean processReply() {
		if (!super.processReply())
			return false;

		System.out.println("Directory contents ");
		String content = reply.get(KEY_CONTENT);
		System.out.println(reply.get(KEY_CONTENT));
		StringTokenizer st = new StringTokenizer(content,",");
		while(st.hasMoreElements()){
			System.out.println(st.nextToken());
		}

		return true;
	}

}
