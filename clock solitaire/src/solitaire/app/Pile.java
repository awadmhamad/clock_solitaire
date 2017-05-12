package solitaire.app;

import solitaire.app.Card.SUIT;

public class Pile {
	
	public enum FACE{
		FACE_DOWN,FACE_UP;
	}
	
	private Card[] pileCards=new Card[5];
	private int numOfCards=0;
	private FACE[] cardFaces=new FACE[5];
	private int numberOfFaceDown=0;
	public Pile(){}
	
	public void addCardFaceDown(Card card){
		pileCards[numOfCards]=card;
		cardFaces[numOfCards]=FACE.FACE_DOWN;
		numOfCards++;
		numberOfFaceDown++;
	}
	
	public Card removeCard(){
		numberOfFaceDown--;
		return pileCards[--numOfCards];
	}
	
	public int getNumberOfFaceDown(){
		return numberOfFaceDown;
	}
	
	public void addCardFaceUp(Card card){
		shiftCards();
		pileCards[0]=card;
		cardFaces[0]=FACE.FACE_UP;
		numOfCards++;
	}
	
	public int getNumberOfFaceUp(){
		return numOfCards-numberOfFaceDown;
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<numOfCards;i++){
			sb.append(String.format("%-10s",pileCards[i].toString()+" "+cardFaces[i]+"\t"));
		}
		return sb.toString();
	}
	
	public boolean isPileAllFacedUp(){
		return getNumberOfFaceUp()==4;
	}
	
	public void shiftCards(){
		for(int i=pileCards.length-1;i>0;i--){
			pileCards[i]=pileCards[i-1];
			cardFaces[i]=cardFaces[i-1];
		}
	}
	
}
