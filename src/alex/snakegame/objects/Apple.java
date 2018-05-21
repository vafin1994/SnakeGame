//Creating apple, and after Snake eat? apple move to another randome place
package alex.snakegame.objects;

import alex.snakegame.SnakeGame;

public class Apple {
	SnakeGame main;
	
	public int posX, posY;
	
	public Apple(int startX,  int startY) {
		posX = startX;
		posY = startY;
	 }

	@SuppressWarnings("static-access")
	public void SetRandomPosition() {
		posX = (int) (Math.random() * (main.WIDTH - 2) );
		posY = (int) (Math.random() * (main.HEIGHT - 2) );
	}

}

