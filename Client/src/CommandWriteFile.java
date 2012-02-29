import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 */

/**
 * @author akai
 * 
 */
public class CommandWriteFile extends Command implements Constants {
	public String	filename;
	public int		byteOffset;
	public String	content;

	public CommandWriteFile(String filename, int byteOffset) {
		super();
		this.filename = filename;
		this.byteOffset = byteOffset;
		this.content = null;
		this.cmdName = VAL_CMD_WRITEFILE;
	}

	public CommandWriteFile() {
		this.cmdName = VAL_CMD_WRITEFILE;
	}

	/* (non-Javadoc)
	 * 
	 * @see Command#toString() */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_FILENAME + ":" + filename + DELIM);
		sb.append(KEY_OFFSET + ":" + byteOffset + DELIM);
		sb.append(KEY_CONTENT + ":" + content + DELIM);
		return wrapRequest(sb);
	}

	/* (non-Javadoc)
	 * 
	 * @see Command#requestData() */
	@Override
	public void requestData() {
		System.out.print("[info] Please enter filename, byte offset and content: ");
		Scanner sc = new Scanner(System.in);
		filename = sc.next();
		byteOffset = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		try {
			while ((str = br.readLine()) != null) {
				sb.append(str + "\n");
			}
			sb.deleteCharAt(sb.length()-1);
		} catch (IOException e) {
			System.out.println("[error] Error reading input");
		} finally {
			content = sb.toString();
		}
	}

	public boolean processReply() {
		return super.processReply();
	}

}
