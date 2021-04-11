package ch2;

import java.util.Random;

public class protocol1 {
	public static void main(String args[]) {
		int n=0;
		protocol1 p = new protocol1();
		while(true) {
			System.out.println(n+" "+p.protocolWorking("scissors"));
			n++;
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				e.getMessage();
			}
			
		}
		
		
	}
	public String protocolWorking(String clientSide) {
		String serverSide=null;
		String result=null;
		String answer = null;
		Random random = new Random();
		int serverChoice = random.nextInt(3);
		switch(serverChoice) {
		case 0:
			serverSide = "Stone";
			if(clientSide=="Stone") {
				result = "TwoDraw";
				
			}
			else if(clientSide =="scissors"){
				result="ServerWin";
			}
			else
				result="ClientWin";
			break;
		case 1:
			serverSide = "Scissors";
			if(clientSide=="Stone") {
				result = "ClientWin";
				
			}
			
			else if(clientSide =="scissors"){
				result="TwoDraw";
			}
			else
				result="ServerWin";
			break;
		case 2:
			serverSide = "Paper";
			if(clientSide=="Stone") {
				result = "ServerWin";
				
			}
			else if(clientSide =="scissors"){
				result="ClientWin";
			}
			else
				result="TwoDraw";
			break;
			
		
		}
		answer = serverSide +"   #   "+result;
		return answer;
	}

}
