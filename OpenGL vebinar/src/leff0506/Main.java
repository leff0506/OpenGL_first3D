package leff0506;

import engine.GameEngine;
import logic.DummyGame;
import logic.IGameLogic;

public class Main {

	public static void main(String[] args) {
		try {
			boolean vSync = true;
			IGameLogic gameLogic = new DummyGame(); 
			GameEngine eng = new GameEngine("Game_test",1000,600,vSync,gameLogic);
			eng.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}