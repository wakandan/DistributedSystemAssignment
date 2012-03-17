import java.util.Calendar;

/**
 *
 */

/**
 * @author akai
 * 
 */
public class CacheEntry {
	public long		createdTime;
	public String	data;
	public long		refreshTime;

	public CacheEntry(String data, long refreshTime) {
		this.createdTime = Calendar.getInstance().getTimeInMillis();
		this.data = data;
		this.refreshTime = refreshTime;
	}

	public boolean isValid() {
		long now = Calendar.getInstance().getTimeInMillis();
		return (now - createdTime < refreshTime);
	}
}
