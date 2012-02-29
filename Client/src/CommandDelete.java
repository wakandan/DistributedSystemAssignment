import java.util.Scanner;

public class CommandDelete extends Command {
	public String	filePath;

	public CommandDelete() {
		this.cmdName = VAL_CMD_DELETE;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(KEY_FILENAME + ":" + filePath + DELIM);
		return wrapRequest(sb);
	}

	@Override
	public void requestData() {
		// TODO Auto-generated method stub
		System.out.print("[info] Please enter file name: ");
		Scanner sc = new Scanner(System.in);
		filePath = sc.next();
	}

	@Override
	public boolean processReply() {
		if (!super.processReply())
			return false;
		return true;
	}
}
