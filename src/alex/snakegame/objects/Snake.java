package alex.snakegame.objects;

import alex.snakegame.SnakeGame;

//создаем саму змейку
public class Snake {
	SnakeGame main;
	public int direction = 0; //Переменная задает направление движения змейки	
	public int length = 3; //Начальная длина змейки;
	public int snakeX[] = new int[main.WIDTH * main.HEIGHT];//Максимальная величина змейки
	public int snakeY[] = new int[main.WIDTH * main.HEIGHT];
	
	//Создаем конструктор змейки
	public Snake(int x0, int y0, int x1, int y1) {
		snakeX[0] = x0;
		snakeY[0] = y0;
		snakeX[1] = x1;
		snakeY[1] = y1;
	}
	//Создаем метод имитирующий движение (закрашивает элемент фона)
	public void move() {
		//Если змейка зайдет за край, то появится с другой стороны
		if(snakeX[0] > main.WIDTH) snakeX[0] = 0;
		if(snakeY[0] > main.HEIGHT) snakeY[0] = 0;
		if(snakeX[0] < 0) snakeX[0] = main.WIDTH;
		if(snakeY[0] < 0) snakeY[0] = main.HEIGHT;
		
		
		for(int d = length; d > 0; d--) {
			snakeX[d] = snakeX[d-1];
			snakeY[d] = snakeY[d-1];
		}
		//При движении змейки закрышиваются элементы, в зависимости
		//от направления движения
		if(direction == 0) snakeX[0]++;
		if(direction == 1) snakeY[0]++;
		if(direction == 2) snakeX[0]--;
		if(direction == 3) snakeY[0]--;
		
		for(int d = length-1; d > 0; d--) {
			if((snakeX[0] == snakeX[d])& (snakeY[0] == snakeY[d])) length = d-1;
		}	
			if(length < 3) length =3;
	}	
}
