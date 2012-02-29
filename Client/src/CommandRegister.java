import java.util.Scanner;


public class CommandRegister extends Command {
	public String	filename;
	public int 		interval;
	public CommandRegister(String fileName, int interval){
		this.filename = fileName;
		this.interval = interval;
		this.cmdName  = VAL_CMD_REGISTER;
	}
	public CommandRegister(){
		this.cmdName = VAL_CMD_REGISTER;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_FILENAME + ":" + filename + DELIM);
		sb.append( INTERVAL+ ":" + interval + DELIM);
//		sb.append(KEY_LENGTH + ":" + byteLength + DELIM);
		return wrapRequest(sb);
	}

	@Override
	public void requestData() {
		// TODO Auto-generated method stub
		System.out.print("[info] Please enter filename, byte offset and length: ");
		Scanner sc = new Scanner(System.in);
		filename = sc.next();
		interval = sc.nextInt();


	}
	@Override
	public boolean processReply() {
		if (!super.processReply())
			return false;

//		System.out.println();
		System.out.println(reply.get(KEY_CONTENT));

		return true;
	}

}
