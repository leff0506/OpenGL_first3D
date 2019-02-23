package logic;

import window.Window;

public interface IGameLogic {
	void init()throws Exception;
	void input(Window window);
	void update(double interval);
	void render(Window window);
}
