import java.util.HashMap;


public class Client {
	public  HashMap<String,Command> commandList;
	public String ip;
	public int port;
	public Client(String ip, int port){
		commandList =new HashMap<String,Command>();
		this.ip = ip;
		this.port = port;
	}
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");
	    result.append(" Ip: " + ip + NEW_LINE);
	    result.append(" port " + port + NEW_LINE);
	    return result.toString();
	}
	public boolean checkNewCommand(String index){
		Command commandResult =  commandList.get(index);
		if( commandResult != null) return false;
		else return true;
	}
	
	public void addCommand(Command command, String index){
		commandList.put(index, command);
	}
}
