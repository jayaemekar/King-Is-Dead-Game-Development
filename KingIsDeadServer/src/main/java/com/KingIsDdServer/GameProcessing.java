package com.KingIsDdServer;

import java.util.ArrayList;

import java.util.Random;

public class GameProcessing {

	
	public static void initializeFile(String filePath) throws InterruptedException {

		Random random = new Random();
		
		StringBuilder message = new StringBuilder(Constant.MESSAAGE_02).append(":");
		message.append(Constant.MORAY_CHAR).append(Constant.COMMA)
				.append(Constant.FOUR).append(Constant.COMMA).append(Constant.ZERO).append(Constant.COMMA).append(Constant.ZERO)
				.append(Constant.COMMA).append(Constant.GWYNEDD_CHAR).append(Constant.COMMA)
				.append(Constant.ZERO).append(Constant.COMMA).append(Constant.FOUR).append(Constant.COMMA).append(Constant.ZERO)
				.append(Constant.COMMA).append(Constant.ESSEX_CHAR).append(Constant.COMMA)
				.append(Constant.ZERO).append(Constant.COMMA).append(Constant.ZERO).append(Constant.COMMA).append(Constant.FOUR);
		
		ArrayList<String> loca = new ArrayList<String>();
		loca.add(Constant.STRATHCLYDE_CHAR);
		loca.add(Constant.LANCASTER_CHAR);
		loca.add(Constant.NORTHUMBRIA_CHAR);
		loca.add(Constant.WARWICK_CHAR);
		loca.add(Constant.DEVON_CHAR);
		
		for ( int i = 0 ; i < 5 ; i ++) {
			
			int n1 = random.nextInt(4);
			int n2 = random.nextInt(4 - n1);
			int n3 = 4 - n1 - n2;	
			message.append(Constant.COMMA).append(loca.get(i)).append(Constant.COMMA).append(n1).append(Constant.COMMA).append(n2).append(Constant.COMMA).append(n3);
		}
		Utility.writeAllFile(filePath, message.toString());

}

//03 message
public static void distributeFoll(String filePath) throws InterruptedException {
	Random random = new Random();

	StringBuilder message = new StringBuilder(Constant.MESSAAGE_03).append(":");
	for(int i = 1 ; i <= 3;  i ++ ) {
		
			int n1 = random.nextInt(3);
			int n2 = random.nextInt(3 - n1);
			int n3 = 3 - n1 - n2;		
			String playerName = "P" + i;
			if(i < 3)
				message.append(playerName).append(Constant.COMMA).append(n1).append(Constant.COMMA).append(n2).append(Constant.COMMA).append(n3).append(Constant.COMMA);
			else
				message.append(playerName).append(Constant.COMMA).append(n1).append(Constant.COMMA).append(n2).append(Constant.COMMA).append(n3);
	}
	Utility.writeAllFile(filePath, message.toString());
	
}
//04 message
public static void distributeReg(String filePath) throws InterruptedException {
	Random random = new Random();

	StringBuilder message = new StringBuilder(Constant.MESSAAGE_04).append(":");
	
	ArrayList<String> loca = new ArrayList<String>();
	loca.add(Constant.MORAY_CHAR);
	loca.add(Constant.GWYNEDD_CHAR);
	loca.add(Constant.ESSEX_CHAR);
	loca.add(Constant.STRATHCLYDE_CHAR);
	loca.add(Constant.LANCASTER_CHAR);
	loca.add(Constant.NORTHUMBRIA_CHAR);
	loca.add(Constant.WARWICK_CHAR);
	loca.add(Constant.DEVON_CHAR);
	
	for(int i = 7 ; i >= 0;  i -- ) {
		
			if(i > 0) {
				int n1 = random.nextInt(i);
				message.append(loca.get(n1)).append(Constant.COMMA);
				loca.remove(n1);
			}
			else
				message.append(loca.get(0));
			
	}
	Utility.writeAllFile(filePath, message.toString());
	
}

}
