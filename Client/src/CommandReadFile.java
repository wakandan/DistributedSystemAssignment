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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_CMD + ":" + VAL_CMD_READFILE);
		sb.append(KEY_FILENAME + ":" + filename + "\n");
		sb.append(KEY_OFFSET + ":" + byteOffset + "\n");
		sb.append(KEY_LENGTH + ":" + byteLength + "\n");
		sb.append(KEY_CMD_END + ":");
		return sb.toString();
	}
}
