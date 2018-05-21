package alex.snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import alex.snakegame.objects.Apple;
import alex.snakegame.objects.Snake;

public class SnakeGame extends JPanel implements ActionListener {

	//Создаем сетку
	public static final int SCALE = 16;// Величина одной клетки
	public static final int WIDTH = 40;//Ширина
	public static final int HEIGHT = 40;//Высота
	public static final int SPEED = 5;
	
	Apple a = new Apple((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
	Snake s = new Snake(10,10, 9, 10);
	Timer t = new Timer(1000/SPEED, this);
	
	public SnakeGame() {
		t.start();
		addKeyListener(new Keyboard());
		setFocusable(true);
	}
	
	public void paint(Graphics g) {
		//Деламем фон
		g.setColor(color(0, 70, 30)); //цвет фона
		g.fillRect(0,0, WIDTH * SCALE +30, HEIGHT * SCALE+30); //Размер заливки фона
		//Делаем вертикальные линии
		g.setColor(color(0, 70, 30)); //цвет линий
		for(int xx = 0; xx <= WIDTH * SCALE; xx+=SCALE) {
			g.drawLine(xx, 0, xx, HEIGHT * SCALE ); //расположение линий
		}
		//Делаем горизонтальные линии
		g.setColor(color(15, 70, 30)); //цвет линий
		for(int yy = 0; yy <= HEIGHT * SCALE; yy+=SCALE) {
			g.drawLine(0, yy, HEIGHT * SCALE, yy);
		}
		//Цвет змейки
		for(int d = 0; d < s.length; d++) {
			g.setColor(color(0,0,255));
			g.fillRect(s.snakeX[d] * SCALE + 1, s.snakeY[d]*SCALE + 1, SCALE-1, SCALE - 1 );
		}
		//Цвет яблока
		g.setColor(color(255,0,0));
		g.fillRect(a.posX*SCALE+1, a.posY*SCALE+1, SCALE-1, SCALE-1);
	}
	
	
	public Color color(int red, int green, int blue) {
		return new Color(red, green, blue); 
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame(); //создаем окно для приложения
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Что бы не приходилось лезть в диспетчер задач, что бы выключать наше приложение
		f.setResizable(false); //размер окна нельзя менять
		f.setSize(WIDTH*SCALE +30, HEIGHT*SCALE +50); //Устанавливаем размер окна
		f.setLocationRelativeTo(null);//окно будет рассполагать по центру
		f.add(new SnakeGame());
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		s.move();
		if((s.snakeX[0] == a.posX) & (s.snakeY[0] == a.posY)) {
			s.length++;//змея удлиняется на одну клетку
			a.SetRandomPosition(); //яблоко перемещается в другое случайное место
		}
		for(int k = 0; k < s.length; k++) {
			if((s.snakeX[k] == a.posX) & (s.snakeY[k]== a.posY)) a.SetRandomPosition(); // проверка на то, что бы яблоко не появлялось в теле змеи
		}
		repaint();
	}
	private class Keyboard extends KeyAdapter{
		public void keyPressed(KeyEvent kEvt) {
			int key = kEvt.getKeyCode();
			//По нажатию клавиши, переменной direction присваевается нужное значение,
			// при этом, нельзя изменить направление на противоположное нажатием одной клавиши
			if((key == KeyEvent.VK_RIGHT) & (s.direction != 2)) s.direction = 0;
			if((key == KeyEvent.VK_DOWN) & (s.direction != 3)) s.direction = 1;
			if((key == KeyEvent.VK_LEFT) & (s.direction != 0)) s.direction = 2;
			if((key == KeyEvent.VK_UP) & (s.direction != 1)) s.direction = 3;
		}
	}

}
