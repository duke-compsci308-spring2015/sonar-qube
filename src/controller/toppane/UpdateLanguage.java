package controller.toppane;

import model.commands.ReceiveFromFront;

public class UpdateLanguage {
	private ReceiveFromFront receive;
	public UpdateLanguage(ReceiveFromFront rs) {
		receive = rs;
	}
	
	public void changeLanguage(String s) {
		receive.receiveLanguage(s);
	}
}
