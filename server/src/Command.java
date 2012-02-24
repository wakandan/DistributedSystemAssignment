import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


public class Command implements Constants{
	HashMap hashMap;
	public Command(){
		hashMap = new HashMap();
	}
	public void setCommand(String commandString){
		StringTokenizer st = new StringTokenizer(commandString);
		System.out.println("number of token "+st.countTokens());
		while(st.hasMoreElements()){
			String stringPart = st.nextToken().toString();
			System.out.println(stringPart);
		
			StringTokenizer st1 = new StringTokenizer(stringPart,":");
			String key = st1.nextToken();
			if(key.equals(KEY_CMD_END))break;
			String value = st1.nextToken();

			hashMap.put(key, value);

		}
		List<String> keys = new ArrayList<String>(hashMap.keySet());
		for(String key: keys) {
		    System.out.println("key = "+key+ " value= " + hashMap.get(key));
		}
	}

}
