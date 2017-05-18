import java.awt.Color;
import java.util.Random;

public class Philosopher extends Thread {
	// Fields
	private String Name;
	public Color phcolor;
	private int beenHungry = 0;
	private int beenEating = 0;
	private int beenthinking = 0;

	private Random rand = new Random();

	public Fork Left;
	public Fork Right;

	public boolean lefthold;
	public boolean righthold;
	public boolean isRunning = true;

	public String state = "Thinking";
	private Main main = null;

	// Constructor
	public Philosopher(String name, Main mAin) {
		Name = name;
		main = mAin;
	}

	// it will get the left fork if it is free
	private void getLeft() throws InterruptedException {
		while (!lefthold) {
			lefthold = Left.Lock();
			beenHungry++;
			Thread.sleep(1);
		}
	}

	// it will get the right fork if it is free
	private void getRight() throws InterruptedException {
		while (!righthold) {
			righthold = Right.Lock();
			beenHungry++;
			Thread.sleep(1);
		}
	}

	private void eat() throws InterruptedException {
		main.addtoTable(Name + " is trying to eat");
		int waittime = randWait();
		state = "Hungry";
		getRight();

		main.addtoTable(Name + " Picked up Left Fork");

		getLeft();
		state = "Eating";
		main.addtoTable(Name + " is Eating for: " + waittime);
		beenEating += waittime;
		Thread.sleep(waittime);

		Left.releaseFork();
		Right.releaseFork();
		lefthold = false;
		righthold = false;
	}

	private void think() throws InterruptedException {
		int waittime = randWait();
		main.addtoTable(Name + " is thinking. for: " + waittime
				+ "MiliSeconds.");
		state = "Thinking";
		beenthinking += waittime;
		Thread.sleep(waittime);
	}

	private int randWait() {
		int TimeUnit = 1000;
		return TimeUnit + (rand.nextInt(9) * TimeUnit);
	}

	public void run() {
		while (isRunning) {
			try {
				think();
				eat();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		state = "Sleeping";
		main.addtoTable(Name + ": " + avTime());

	}

	private String avTime() {
		int totalTime = beenHungry + beenEating + beenthinking;
		int Hungry = (int) (100 * ((float) beenHungry / totalTime));
		int Eating = (int) (100 * ((float) beenEating / totalTime));
		int thinking = (int) (100 * ((float) beenthinking / totalTime));
		return "Been: " + Hungry + "% hungry. and " + Eating + "% eating. and "
				+ thinking + "% thinking !";

	}

}
