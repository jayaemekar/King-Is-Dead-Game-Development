package com.KingIsDdServer;

import static java.nio.file.StandardOpenOption.WRITE;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;




public class Utility {

	
//	public static boolean readFile(Boolean exitGame) throws InterruptedException, IOException {
//		boolean canBreakP1 = false;
//		boolean canBreakP2 = false;
//		boolean canBreakP3 = false;
//		String lineP1,lineP2,lineP3;
//		try {
//			
//			FileInputStream fInP1 = new FileInputStream("FromP1");
//			BufferedReader bReadP1 = new BufferedReader(new InputStreamReader(fInP1));
//			FileInputStream fInP2 = new FileInputStream("FromP2");
//			BufferedReader bReadP2 = new BufferedReader(new InputStreamReader(fInP2));
//			FileInputStream fInP3 = new FileInputStream("FromP3");
//			BufferedReader bReadP3 = new BufferedReader(new InputStreamReader(fInP3));
//
//			while (!exitGame) {
//
//				lineP1 = bReadP1.readLine();
//				lineP2 = bReadP2.readLine();
//				if ( (lineP1 == null || lineP1.isEmpty() ) && (lineP2 == null || lineP2.isEmpty())) {				
//					Thread.sleep(3000);
//					continue;
//				}
//				if(lineP1 != null) {
//					canBreakP1 = parseMessage(lineP1, "P1");
//					clearTheFile("FromP1");
//					fInP1.getChannel().position(0);
//					bReadP1 = new BufferedReader(new InputStreamReader(fInP1));
//					if(canBreakP1)
//						Utility.endFile("ToP1", "Game End", "P1");
//				}
//				if(lineP2 != null) {
//					canBreakP2 = parseMessage(lineP2, "P2");
//					clearTheFile("FromP2");
//					fInP1.getChannel().position(0);
//					bReadP2 = new BufferedReader(new InputStreamReader(fInP1));
//					if(canBreakP2)
//						Utility.endFile("ToP2", "Game End", "P2");
//				}
//				
//				if(canBreakP1 && canBreakP2)
//						break;
//				
//				
//			}
//			bReadP1.close();
//			bReadP2.close();
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		return exitGame;
//	}
//	
	public static void readAllFile( Boolean exitGame , String readFile, String writeFile) throws FileNotFoundException, InterruptedException {
		
		Runnable runnable = () -> {
			boolean canBreak = exitGame;
			String line;
			
			try {
				FileInputStream fIn = new FileInputStream(readFile + Thread.currentThread().getName());
				BufferedReader bRead = new BufferedReader(new InputStreamReader(fIn));
				while (!canBreak) {

					line = bRead.readLine();
					if ( line == null || line.isEmpty() ) {				
						Thread.sleep(3000);
						continue;
					}
					if(line != null) {
						canBreak = parseMessage(line, Thread.currentThread().getName());
						clearTheFile(readFile + Thread.currentThread().getName());
						fIn.getChannel().position(0);
						bRead = new BufferedReader(new InputStreamReader(fIn));
						
						if(canBreak)
							Utility.endFile(writeFile, "Game End", Thread.currentThread().getName());
					}

			} 
				
			bRead.close();
			}catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		};
	
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		Thread t3 = new Thread(runnable);
		t1.setName("P1");
		t2.setName("P2");
		t3.setName("P3");
		t1.start();
		t2.start();
		t3.start();		
		t1.join();
		t2.join();
		t3.join();
		
		
	}
	

	public static void clearTheFile(String fileName) throws IOException {
        FileWriter fwOb = new FileWriter(fileName, false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }

	public static Boolean parseMessage(String line, String playerName) {
		
		if(line.equals("END")) {
			return true;
		}
		else{
			System.out.println("Message from " +  playerName + "--> " + line);
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	public static void writeAllFile( String writeFile ,  String message ) throws InterruptedException  {
		
		Runnable runnable = () -> {
			try {
			     String writeFileP = writeFile + Thread.currentThread().getName();
//          File file1 = new File(writeFileP);
//          System.out.println(file1);
//          System.out.println(file1.length());
//          if(file1.length() > 0 ) {
//              System.out.println("I");
//              while (file1.length() > 0) { 

//              }
//          }
//              System.out.println(Thread.currentThread().getName());
                Path path = Paths.get(writeFileP);
                                OutputStream outputStream = Files.newOutputStream(path, WRITE);

//                OutputStream outputStream = Files.newOutputStream(file1.toPath(), WRITE);
                outputStream.write(message.getBytes());
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		};
		
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		Thread t3 = new Thread(runnable);
		t1.setName("P1");
		t2.setName("P2");
		t3.setName("P3");
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
        
	}
	
	public static void writeFile(String filePath, String message) {
		try {
		
			Path path = Paths.get(filePath);
			OutputStream outputStream = Files.newOutputStream(path, WRITE);
			outputStream.write(message.getBytes());
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void endFile(String filePath, String message, String playerName) {
		try {
		
			Path path = Paths.get(filePath);
			OutputStream outputStream = Files.newOutputStream(path, WRITE);
			outputStream.write(message.getBytes());
			outputStream.close();
			System.out.println("Player "+ playerName + " has ended");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
