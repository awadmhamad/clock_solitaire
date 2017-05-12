package integration.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;

import com.sun.corba.se.impl.orbutil.concurrent.ReentrantMutex;

import solitaire.app.ClockSolitaire;
import solitaire.utils.Print.LEVEL;

public class SolitaireCheckCardsValidaityTest {
	static ReentrantMutex mutex=new ReentrantMutex();
	public static ClockSolitaire clockSolitaire=new ClockSolitaire(mutex);
	public ClockSolitaire getClockSolitaire() {
		return clockSolitaire;
	}
	
	@Test
	public void countCards(){
		clockSolitaire.main(null);
	}
	
	public static void main(String[] args){
		clockSolitaire.setPrintLevel(LEVEL.verbose);
		Thread t1=new Thread(new RunBitch(mutex));
		t1.start();
		
		Thread t2=new Thread(clockSolitaire);
		t2.start();
	}
	
	private static final class RunBitch implements Runnable{
		ReentrantMutex mutex;
		
		public RunBitch(ReentrantMutex mutex) {
			this.mutex=mutex;
		}
		@Override
		public void run() {
			try {
				Robot robot=new Robot();
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
					mutex.acquire();
					robot.keyPress(KeyEvent.VK_Y);
					robot.keyRelease(KeyEvent.VK_Y);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					mutex.release();
				}catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
		
	}
}
