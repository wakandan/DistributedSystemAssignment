import java.util.Scanner;

public class CommandRegister extends Command {
	public String	filename;
	public int		interval;
	public boolean	timeElapsed;
	public Thread	threadRegister;

	public CommandRegister(String fileName, int interval) {
		this.filename = fileName;
		this.interval = interval;
		this.cmdName = VAL_CMD_REGISTER;
	}

	public CommandRegister() {
		this.cmdName = VAL_CMD_REGISTER;
		timeElapsed = false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_FILENAME + ":" + filename + DELIM);
		sb.append(INTERVAL + ":" + interval + DELIM);
		return wrapRequest(sb);
	}

	@Override
	public void requestData() {
		// TODO Auto-generated method stub
		System.out.print("[info] Please enter filename & interval: ");
		Scanner sc = new Scanner(System.in);
		filename = sc.next();
		interval = sc.nextInt();

	}

	@Override
	public boolean processReply() {
		boolean processed = super.processReply();
		System.out.println("[reason] " + request.content);
		if (!processed)
			return false;
		return true;
	}

}
