package com.KindIsDeadPlayer;

import static java.nio.file.StandardOpenOption.WRITE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Utility {

	
	private String PlayerName;
	private String writefilepath;
	private String readfilepath;
	private static volatile Utility Utility = null;

	Utility() {
		// private constructor
	}
	//singleton object creation
	public static Utility getInstance() {
		if (Utility == null) {
			synchronized (Utility.class) {
				if (Utility == null) {
					Utility = new Utility();
				}
			}
		}
		return Utility;
	}

	public void SetPlayerName(String name) {
		this.PlayerName=name;
	}
	
	public String GetPlayerName() {
		return this.PlayerName;
	}
	
	public  String getFileWritePath() {
		return writefilepath;
	}
	public void setFileWritePath(String fileWritePath) {
		this.writefilepath = fileWritePath;
	}
	
	public static void readFile(String filename, boolean canBreak) throws InterruptedException, IOException {
		canBreak = false;
		String line;
		try {
			LineNumberReader lnr =new LineNumberReader(new FileReader(filename));
			while (!canBreak) {
				line = lnr.readLine();
				if (line == null || line.isEmpty()) {				
					Thread.sleep(3000);
					continue;
				}
				canBreak = parseMessage(line);
				if (canBreak)
					break;
			}
			lnr.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Boolean parseMessage(String line) throws InterruptedException {
		
		if(line.equals("END")) {
			return true;
		}
		else{
			System.out.println("Message from Server --> " + line);
			// TODO Auto-generated method stub
			writeFile( Utility.getInstance().getFileWritePath(), "GoodBye from P1");
			return false;
		}
		
	}
	
	public static void writeFile(String filePath, String message) throws InterruptedException {
		try {
			File file = new File(filePath);
			Path path = Paths.get(filePath);
			OutputStream outputStream = Files.newOutputStream(path, WRITE);
			outputStream.write(message.getBytes());
			outputStream.close();
			Thread.sleep(1000);
			if (file.exists())
	            file.delete();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getReadfilepath() {
		return readfilepath;
	}

	public void setReadfilepath(String readfilepath) {
		this.readfilepath = readfilepath;
	}
	
}
