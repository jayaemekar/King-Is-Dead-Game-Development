package com.KingIsDdServer;

import java.io.File;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class Main {
	
	public static Boolean ExitGame = false;
    public static void main(String[] args) throws IOException, InterruptedException {

        String readFile = "/tmp/From";
        String writeFile = "/tmp/To";

        for (int i = 1 ; i <=3 ;i ++) {

                String playerName = "P" + i;
                String readPath = readFile + playerName;
                String writePath = writeFile + playerName;

                 File readFileName = new File(readPath);
                 readFileName.deleteOnExit();

             File writeFileName = new File(writePath);
             writeFileName.deleteOnExit();

             writeFileName.setReadable(true);
             writeFileName.setWritable(true);
             readFileName.setReadable(true);
             readFileName.setWritable(true);
             readFileName.setExecutable(true);
             writeFileName.setExecutable(true);

          //    Runtime.getRuntime().exec(new String[] { "cd /"});

          //Files.newOutputStream(writeFileName.toPath());

          //Files.newOutputStream(readFileName.toPath());
 Runtime.getRuntime().exec(new String[] {"mkfifo","-m", "777",  readFileName.toPath().toString()});
             Runtime.getRuntime().exec(new String[] {"mkfifo","-m","777",writeFileName.toPath().toString()});
  // Runtime.getRuntime().exec(new String[] { "cd /"});

             //Runtime.getRuntime().exec(new String[] { "chmod 775 " + readFileName.toPath().toString() });
             //Runtime.getRuntime().exec(new String[] { "chmod 775 " + writeFileName.toPath().toString() });
         // Files.newOutputStream(readFileName.toPath());

          //Files.newOutputStream(writeFileName.toPath());
        }

        
        		
        		System.out.println("fisrt");
        		GameProcessing.initializeFile(writeFile);
        		
        		System.out.println("second");
        		GameProcessing.distributeFoll(writeFile);
        		System.out.println("third");
        		GameProcessing.distributeReg(writeFile);
      
        
        
       
//        	System.out.println("in");
        	Utility.readAllFile(ExitGame , readFile, writeFile);      
	}

}

