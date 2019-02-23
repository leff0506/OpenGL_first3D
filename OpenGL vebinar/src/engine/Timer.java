package engine;

public class Timer {
	private double lastLoopTime;
	public void init() {
		lastLoopTime = getTime();
	}
	public double getTime() {
		return System.nanoTime()/1000_000_000.0;
	}
	public float getElapsedTime() {
		double time = getTime();
		float elapsed = (float)(time - lastLoopTime);
		lastLoopTime= time;
		return elapsed;
	}
	public double getLastLoopTime() {
		return lastLoopTime;
	}
}
