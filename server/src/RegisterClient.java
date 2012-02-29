
public class RegisterClient {
	public String ip;
	public int port;
	public int interval;
	public long startTime;
	public RegisterClient(String ip,int port, int interval){
		this.ip = ip;
		this.interval = interval;
		this.port = port;
		startTime = System.currentTimeMillis() / 1000;
	}
	public boolean isAlive(long time){
		long different = time - startTime;
		if(different < interval) return true;
		else return false;
	}
}
