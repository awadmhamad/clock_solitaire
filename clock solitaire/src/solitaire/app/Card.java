package solitaire.app;

public class Card {
	
	public enum SUIT{
		S,H,D,C;
	}
	
	protected static final int A=14;
	protected static final int J=11;
	protected static final int Q=12;
	protected static final int K=13;
	
	private int value;
	private SUIT suit;
	
	public Card(int value,SUIT suit){
		this.value=value;
		this.suit=suit;
		System.out.println("Picking a new Card!");
	}
	
	public int getValue(){
		return value;
	}
	
	public SUIT getSuit(){
		return suit;
	}
	
	public String toString(){
		if(value>=11&&value<=14){
			switch(value){
				case 11: return "J"+suit.toString();
				case 12: return "Q"+suit.toString();
				case 13: return "K"+suit.toString();
				case 14: return "A"+suit.toString();
			}
		}
		return value+suit.toString();
	}
}
