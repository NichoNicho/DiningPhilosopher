public class Fork {

	private boolean isUsed;

	public boolean Lock() {
		if (isUsed)
			return false;
		else {
			isUsed = true;
			return true;
		}

	}

	public void releaseFork() {
		isUsed = false;
	}

}
