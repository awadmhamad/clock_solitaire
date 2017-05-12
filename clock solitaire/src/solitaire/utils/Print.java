package solitaire.utils;

public class Print {
	public enum LEVEL{
		verbose,normal,silent
	}
	
	private LEVEL level;
	
	public Print(LEVEL level){
		this.level=level;
	}
	
	public void setPrintLevel(LEVEL level){
		this.level=level;
	}
	
	public LEVEL getPrintLevel(){
		return this.level;
	}
}
