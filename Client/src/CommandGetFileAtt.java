import java.util.Scanner;

/**
 *
 */

/**
 * @author akai
 * 
 */
public class CommandGetFileAtt extends Command implements Constants {
	String	filename;
	
	public CommandGetFileAtt() {
		this.cmdName = VAL_CMD_GETATT;
	}

	/* (non-Javadoc)
	 * 
	 * @see Command#toString() */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return wrapRequest(sb.append(KEY_FILENAME + ":" + filename));
	}

	/* (non-Javadoc)
	 * 
	 * @see Command#requestData() */
	@Override
	public void requestData() {
		System.out.print("[info] Enter file name: ");
		filename = (new Scanner(System.in)).next();
	}
}
