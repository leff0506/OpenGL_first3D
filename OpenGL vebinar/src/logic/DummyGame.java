package logic;
import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.glViewport;

import render.Renderer;
import window.Window;

public class DummyGame implements IGameLogic{
	private int dir=0;
	private float color = 0.0f;
	private final Renderer renderer;
	public DummyGame() {
		renderer=new Renderer();
	}
	@Override
	public void init() throws Exception {
		renderer.init();
		
	}

	@Override
	public void input(Window window) {
		if(window.isKeyPressed(GLFW_KEY_W)) {
			dir=1;
		}else if(window.isKeyPressed(GLFW_KEY_S)) {
			dir=-1;
		}else {
			dir=0;
		}
		
	}

	@Override
	public void update(double interval) {
		color+=dir*interval;
		if(color>1) {
			color=1.f;
		}else if(color<0) {
			color=0.f;
		}
		System.out.println(color);
	}

	@Override
	public void render(Window window) {
		glViewport(0, 0, window.getWidth(), window.getHeight());
		window.setClearColor(color, color, color, 0.4f);
		renderer.clear();
	}

}
