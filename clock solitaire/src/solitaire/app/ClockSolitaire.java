package solitaire.app;

import java.util.Scanner;

import com.sun.corba.se.impl.orbutil.concurrent.ReentrantMutex;

import solitaire.utils.Print;
import solitaire.utils.Print.LEVEL;

public class ClockSolitaire implements Runnable{
	
	private static Print print=new Print(LEVEL.normal);
	private static int numOfGames=1;
	private static Pile[] piles=new Pile[13];
	private static Deck deck=new Deck();
	private static int stepNumber=0;
	private int score=13;
	private ReentrantMutex mutex;
	public ClockSolitaire(ReentrantMutex mutex){
		deck.shuffle();
		dealThePiles();
		this.mutex=mutex;
	}
	
	private void dealThePiles(){
		initPiles();
		for(int i=0;i<4;i++){
			for(int j=0;j<13;j++){
				piles[j].addCardFaceDown(deck.dealCard());
			}
		}
	}
	
	public void setPrintLevel(LEVEL level){
		this.print.setPrintLevel(level);
	}
	
	public void initPiles(){
		for(int i=0;i<piles.length;i++)
			piles[i]=new Pile();
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<piles.length;i++)
			sb.append(String.format("%2d %-5s %-10s",i+1," - ",piles[i]+"\n"));
		return sb.toString();
		
	}
	
	public void run(){
		Scanner scanner=new Scanner(System.in);
		Pile pileToPlay=piles[12];
		Card cardToPlay;
		if(!print.getPrintLevel().equals(LEVEL.silent)) System.out.println(this);
		while(!isGameFinished()){
		System.out.print("move another card?");
		String ans=scanner.nextLine();
		if(ans.equals("y")){
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cardToPlay=pileToPlay.removeCard();
			int cardNumber=cardToPlay.getValue()%13-1>=0?cardToPlay.getValue()%13-1:12;
			piles[cardNumber].addCardFaceUp(cardToPlay);
			if(piles[cardNumber].isPileAllFacedUp()) this.score--;
			pileToPlay=piles[cardNumber];
			if(print.getPrintLevel().equals(LEVEL.verbose)) System.out.println("The Score is: "+score+"\n\n"+this);
			mutex.release();
		}else{
			
		}
		}
		if(score>0) System.out.println("Game is Over, you Lost!\n");
		else System.out.println("Game is Over, You WON!!\n");
		System.out.println("The Score is: "+score+"\n");
		System.out.println(this);
		scanner.close();
		System.exit(0);
	}
	
	private static boolean isGameFinished() {
		return piles[12].isPileAllFacedUp();
	}
	
	public static void main(String[] args){
		ReentrantMutex mutex=new ReentrantMutex();
		ClockSolitaire clockSolitaire=new ClockSolitaire(mutex);
		if(args.length>=1){
			print.setPrintLevel(LEVEL.valueOf(LEVEL.class, args[0])); 
		}
		
		if(args.length>=2) clockSolitaire.numOfGames=Integer.parseInt(args[1]);
		clockSolitaire.run();
	}

	
}
