package com.KindIsDeadPlayer;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Boolean GameEnd=false;
		String playerName = args[0];
		System.out.println("This is a client");
		
		String FilepathRead = "/tmp/To" + playerName;
		String Writepath = "/tmp/From" + playerName;
		
		Utility.getInstance().SetPlayerName(playerName);
		Utility.getInstance().setFileWritePath(Writepath);
		Utility.getInstance().setReadfilepath(FilepathRead);
		Utility.readFile(FilepathRead, false);
		

		
	}

}

