package solitaire.app;

import java.util.Random;

import solitaire.app.Card.SUIT;

public class Deck {
	
	private static Card[] cards=new Card[52];
	private static int cardsLeft=52;
	public Deck(){
		int counter=0;
		for(int value=2;value<=14;value++){
			for(SUIT suit:SUIT.values()){
				cards[counter]=new Card(value,suit);
				counter++;
			}
		}
	}
	
	public void shuffle(){
		int index;
		Card temp;
		Random random=new Random();
		for(int i=cards.length-1 ; i>0 ; i--){
			index=random.nextInt(i+1);
			temp=cards[index];
			cards[index]=cards[i];
			cards[i]=temp;
		}
	}
	
	public Card dealCard(){
		return cards[--cardsLeft];
	}
	
	public int cardsLeft(){
		return cardsLeft;
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(int i=0; i< cardsLeft ;i++){
			sb.append(cards[i]+"\t");
		}
		return sb.toString();
	}
	
}
