/**
 *
 */

/**
 * @author akai
 * 
 */
public interface Constants {
	public static final int		BUFFER_SIZE			= 10000;
	public static final String	KEY_FILENAME		= "Filename";
	public static final String	KEY_OFFSET			= "Offset";
	public static final String	KEY_LENGTH			= "Length";
	public static final String	KEY_CMD				= "Command";
	public static final String	KEY_CONTENT			= "Content";
	public static final String	VAL_CMD_READFILE	= "Readfile";
	public static final String	VAL_CMD_WRITEFILE	= "Writefile";
	public static final String	VAL_CMD_GETATT		= "GetfileAtt";
	public static final String	KEY_CMD_END			= "EndCommand";
	public static final String	KEY_STATUS			= "Status";
	public static final String	VAL_STATUS_OK		= "OK";
	public static final String	VAL_STATUS_FAILED	= "FAILED";
	public static final String	VAL_STATUS_ERROR	= "ERROR";
	public static final String	DELIM				= "\0";

	public static final int		OPT_READFILE		= 1;
	public static final int		OPT_WRITEFILE		= 2;
	public static final int		OPT_EXIT			= 0;
}
