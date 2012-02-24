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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_CMD + ":" + VAL_CMD_READFILE);
		sb.append(KEY_FILENAME + ":" + filename + "\n");
		sb.append(KEY_OFFSET + ":" + byteOffset + "\n");
		sb.append(KEY_LENGTH + ":" + byteLength + "\n");
		sb.append("\n\n");
		return sb.toString();
	}
}
