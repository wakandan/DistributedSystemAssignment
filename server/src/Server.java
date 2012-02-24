import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class Server{
	public static void main(String args[]){
		DatagramSocket aSocket =null;
		Command command =new Command();
		try{
			
			aSocket = new DatagramSocket(6789);
			byte [] buffer =new byte[1000];
			while(true){
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				
				aSocket.receive(request);
				String receiveCommand = new String(request.getData());
				command.setCommand(receiveCommand);
//				StringTokenizer st = new StringTokenizer(receiveCommand);
//				while(st.hasMoreElements()){
//					String stringPart = st.nextToken().toString();
//					StringTokenizer st1 = new StringTokenizer(stringPart,":");
//					
//					System.out.println("part" +stringPart);
//				}
				File file = new File("Text");
				if(file.exists())System.out.println("file exist");
				else System.out.println("file not exist");
				byte[] sendByte = readFileAsString(file);
//				String text = new String(request.getData());
//				System.out.println("server "+text);
				DatagramPacket reply = new DatagramPacket(sendByte,sendByte.length,request.getAddress(),request.getPort());
				aSocket.send(reply);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally { 
			if(aSocket!=null)aSocket.close();
		}
	}
	private static byte[] readFileAsString(File filePath)
		    throws java.io.IOException{
		        BufferedReader reader = new BufferedReader(
		                new FileReader(filePath));
		        char[] buf = new char[100];
		        byte[] returnByte = new byte[1000];
		        int numRead=0;
		        int index =0;
		        while((numRead=reader.read(buf)) != -1){
		            for(int i=0;i< buf.length;i++){
		            	returnByte[index+i]= (byte)buf[i];
		     
		            }
		            index+= buf.length;
		        }
		        reader.close();

		        return returnByte;
		    }

}