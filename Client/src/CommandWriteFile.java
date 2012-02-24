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
	}

	/* (non-Javadoc)
	 * 
	 * @see Command#toString() */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_CMD + ":" + VAL_CMD_WRITEFILE + DELIM);
		sb.append(KEY_FILENAME + ":" + filename + DELIM);
		sb.append(KEY_OFFSET + ":" + byteOffset + DELIM);
		sb.append(KEY_CONTENT + ":" + content + DELIM);
		sb.append(KEY_CMD_END + ":");
		return sb.toString();
	}

	/* (non-Javadoc)
	 * 
	 * @see Command#requestData() */
	@Override
	public void requestData() {

	}

}