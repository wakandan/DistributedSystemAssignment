import java.util.HashMap;
import java.util.Scanner;

/**
 *
 */

/**
 * @author akai
 * 
 */
public class CommandReadFile extends Command implements Constants {
	public String						filename;
	public int							byteOffset;
	public int							byteLength;
	public HashMap<String, CacheEntry>	cache;

	public CommandReadFile(String filename, int byteOffset, int byteLength) {
		super();
		this.filename = filename;
		this.byteOffset = byteOffset;
		this.byteLength = byteLength;
		this.cmdName = VAL_CMD_READFILE;
	}

	/**
	 * 
	 */
	public CommandReadFile() {
		this.cmdName = VAL_CMD_READFILE;
		cache = new HashMap<String, CacheEntry>();
	}

	/* construct the request from current data to send to server */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_FILENAME + ":" + filename + DELIM);
		return wrapRequest(sb);
	}

	/* (non-Javadoc)
	 * 
	 * @see Command#requestData() */
	@Override
	public void requestData() {
		System.out.print("[info] Please enter filename, byte offset and length: ");
		Scanner sc = new Scanner(System.in);
		filename = sc.next();
		byteOffset = sc.nextInt();
		byteLength = sc.nextInt();

		/* if the file is in cache and */
		if (cache.containsKey(filename) && cache.get(filename).isValid()) {
			System.out.println("[info] from cache");
			isServed = true;
			System.out.println(cache.get(filename).data.substring(byteOffset, byteLength));
		}
	}

	@Override
	public boolean processReply() {
		if (!super.processReply())
			return false;
		cache.put(filename, new CacheEntry(request.content, CACHE_FRESHTIME));
		System.out.println(request.content.substring(byteOffset, byteLength));
		return true;
	}
}
