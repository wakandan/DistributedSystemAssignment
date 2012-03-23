import java.util.Scanner;

public class CommandDelete extends Command {

	public CommandDelete() {
		this.cmdName = VAL_CMD_DELETE;
	}

	@Override
	public String toString() {
//		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
//		sb.append();
		return wrapRequest(sb);
	}


	@Override
	public boolean processReply() {
		boolean result;
		if (!super.processReply()) 
			result = false;
		else
			result = true;
		System.out.println("[Content] "+request.content);
		return result;
	}
}
