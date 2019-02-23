package engine;

import logic.IGameLogic;
import window.Window;

public class GameEngine implements Runnable{
	public static final int TARGET_UPS=30;
	private final Window window;
	private final Timer timer;
	private final Thread gameLoopThread;
	private final IGameLogic gameLogic;
	public GameEngine(String windowTitle,int width,int height,boolean vSync,IGameLogic gameLogic) throws Exception {
		gameLoopThread = new Thread(this,"GAME_LOOP_THREAD");
		window = new Window(windowTitle,width,height,vSync);
		this.gameLogic=gameLogic;
		timer = new Timer();
	}
	@Override
	public void run() {
		try {
			init();
			gameLoop();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			cleanUp();
		}
		
	}
	public void start() {
		if(System.getProperty("os.name").contains("Mac")) {
			gameLoopThread.run();
		}else {
			gameLoopThread.start();
		}
	}
	public void cleanUp() {
		gameLogic.cleanUp();
	}
	protected void gameLoop() {
		float elapsedTime;
		double was = timer.getTime();
		float accumulator = 0f;
		float interval = 1f/TARGET_UPS;
		boolean running = true;
		double is;
		while(running &&!window.windowShouldCole()) {
			is=timer.getTime();
			input();
			if(is-was>=1.0/TARGET_UPS) {
				update(is-was);
				was=is;
			}
			
			
			render();
		}
	}
	protected void init() throws Exception {
		window.init();
		timer.init();
		gameLogic.init();
	}
	protected void input() {
		gameLogic.input(window);
	}
	protected void update(double interval) {
		gameLogic.update(interval);
	}
	protected void render() {
		gameLogic.render(window);
		window.update();
	}
	
}
