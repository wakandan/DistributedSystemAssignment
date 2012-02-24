import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
		System.out.println("content "+commandString);
		StringTokenizer st = new StringTokenizer(commandString,"\0");
		System.out.println("number of token "+st.countTokens() );
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
	public ReplyMessage execute(){
		String commandName =(String) hashMap.get(KEY_CMD);
		if(commandName.equals(VAL_CMD_READFILE)){
			return readFile();
		}else if(commandName.equals(VAL_CMD_WRITEFILE)){
			return writeFile();
		}
	
		
		else{
			System.out.println("error dont have this command");
			
			return null;
		}
		
	}
	private ReplyMessage readFile() {
		ReplyMessage replyMessage = new ReplyMessage();
		File file = new File((String)hashMap.get(KEY_FILENAME));
		if(file.exists())System.out.println("file exist");
		else{
			
			System.out.println("file not exist");
			replyMessage.error = true;
			replyMessage.content = "file not exist";
			return replyMessage;
		}
		try{
			int offset = Integer.parseInt((String)hashMap.get(KEY_OFFSET));
			int lenght = Integer.parseInt((String)hashMap.get(KEY_LENGTH));
			if(file.length()< offset+lenght){
				System.out.println("error out of range");
				replyMessage.error = true;
				replyMessage.content = "error out of range";
				return replyMessage;
			}
			readFileAsString(file,offset,lenght,replyMessage);
		}
		catch(Exception e){
			System.out.println("error reading file");
			e.printStackTrace();
		
		}
//		replyMessage.error = true;
//		replyMessage.content = "error reading file";
		return replyMessage;
	}
	private  void readFileAsString(File filePath,int offset, int lenght, ReplyMessage replyMessage)
		    throws java.io.IOException{
		        BufferedReader reader = new BufferedReader(
		                new FileReader(filePath));
		        char[] buf = new char[100];
		        byte[] bufferByte = new byte[1000];
		        int numRead=0;
		        int index =0;
		        while((numRead=reader.read(buf)) != -1){
		            for(int i=0;i< buf.length;i++){
		            	bufferByte[index+i]= (byte)buf[i];
		     
		            }
		            index+= buf.length;
		        }
		        
		        reader.close();
		        byte[] returnByte = new byte[lenght];
		        System.arraycopy(bufferByte, offset, returnByte, 0, lenght);
		        replyMessage.error = false;
		        replyMessage.sendByte = returnByte;

		    }
	public ReplyMessage writeFile(){
		ReplyMessage replyMessage = new ReplyMessage();
		File file = new File((String)hashMap.get(KEY_FILENAME));
		if(file.exists())System.out.println("file exist");
		else{
			
			System.out.println("file not exist");
			replyMessage.error = true;
			replyMessage.content = "file not exist";
			return replyMessage;
		}
		try{
			int offset = Integer.parseInt((String)hashMap.get(KEY_OFFSET));
			String insertText = (String) hashMap.get(KEY_CONTENT);
			if(file.length()< offset){
				System.out.println("error out of range");
				replyMessage.error = true;
				replyMessage.content = "error out of range";
				return replyMessage;
			}
			writeFileAsString(file, offset, insertText);
		}
		catch(Exception e){
			System.out.println("error reading file");
			e.printStackTrace();
		
		}
//		replyMessage.error = true;
//		replyMessage.content = "error reading file";
		return replyMessage;

	}
	private   void writeFileAsString(File filePath,int offset, String text)
		    throws java.io.IOException{
		        BufferedReader reader = new BufferedReader(
		                new FileReader(filePath));
		        char[] buf = new char[100];
		        char[] bufferByte = new char[1000];
		        int numRead=0;
		        int index =0;
		        while((numRead=reader.read(buf)) != -1){
		            for(int i=0;i< buf.length;i++){
		            	bufferByte[index+i]= buf[i];
		     
		            }
		            index+= buf.length;
		        }
		        
		        reader.close();
//		        ArrayList<Byte> returnByte = new ArrayList<Byte>();
//		        System.arraycopy(bufferByte, 0, returnByte, 0, offset);
//		        returnByte.addAll(text.getBytes());
		        FileWriter fstream = new FileWriter("abc",false);
		        BufferedWriter out = new BufferedWriter(fstream);
		        out.write(bufferByte, 0, offset);
		        out.write(text);
		        out.write(bufferByte,offset,bufferByte.length-offset);
		        out.close();
		    }
}
