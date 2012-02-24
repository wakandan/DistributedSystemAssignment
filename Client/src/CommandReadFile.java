import java.util.Scanner;

/**
 *
 */

/**
 * @author akai
 * 
 */
public class CommandReadFile extends Command implements Constants {
	public String	filename;
	public int		byteOffset;
	public int		byteLength;

	public CommandReadFile(String filename, int byteOffset, int byteLength) {
		super();
		this.filename = filename;
		this.byteOffset = byteOffset;
		this.byteLength = byteLength;
	}

	/**
	 * 
	 */
	public CommandReadFile() {}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_CMD + ":" + VAL_CMD_READFILE);
		sb.append(KEY_FILENAME + ":" + filename + DELIM);
		sb.append(KEY_OFFSET + ":" + byteOffset + DELIM);
		sb.append(KEY_LENGTH + ":" + byteLength + DELIM);
		sb.append(KEY_CMD_END + ":");
		return sb.toString();
	}

	/* (non-Javadoc)
	 * 
	 * @see Command#requestData() */
	@Override
	public void requestData() {
		System.out.println("Please enter filename, byte offset and length: ");
		Scanner sc = new Scanner(System.in);
		filename = sc.next();
		byteOffset = sc.nextInt();
		byteLength = sc.nextInt();
	}

	@Override
	public boolean processReply() {
		if (!super.processReply())
			return false;
		System.out.println("Data read successful. Requested content: ");
		System.out.println(reply.get(KEY_CONTENT));
		return true;
	}
}
