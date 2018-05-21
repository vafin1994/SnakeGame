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

	//������� �����
	public static final int SCALE = 16;// �������� ����� ������
	public static final int WIDTH = 40;//������
	public static final int HEIGHT = 40;//������
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
		//������� ���
		g.setColor(color(0, 70, 30)); //���� ����
		g.fillRect(0,0, WIDTH * SCALE +30, HEIGHT * SCALE+30); //������ ������� ����
		//������ ������������ �����
		g.setColor(color(0, 70, 30)); //���� �����
		for(int xx = 0; xx <= WIDTH * SCALE; xx+=SCALE) {
			g.drawLine(xx, 0, xx, HEIGHT * SCALE ); //������������ �����
		}
		//������ �������������� �����
		g.setColor(color(15, 70, 30)); //���� �����
		for(int yy = 0; yy <= HEIGHT * SCALE; yy+=SCALE) {
			g.drawLine(0, yy, HEIGHT * SCALE, yy);
		}
		//���� ������
		for(int d = 0; d < s.length; d++) {
			g.setColor(color(0,0,255));
			g.fillRect(s.snakeX[d] * SCALE + 1, s.snakeY[d]*SCALE + 1, SCALE-1, SCALE - 1 );
		}
		//���� ������
		g.setColor(color(255,0,0));
		g.fillRect(a.posX*SCALE+1, a.posY*SCALE+1, SCALE-1, SCALE-1);
	}
	
	
	public Color color(int red, int green, int blue) {
		return new Color(red, green, blue); 
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame(); //������� ���� ��� ����������
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ��� �� �� ����������� ����� � ��������� �����, ��� �� ��������� ���� ����������
		f.setResizable(false); //������ ���� ������ ������
		f.setSize(WIDTH*SCALE +30, HEIGHT*SCALE +50); //������������� ������ ����
		f.setLocationRelativeTo(null);//���� ����� ������������ �� ������
		f.add(new SnakeGame());
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		s.move();
		if((s.snakeX[0] == a.posX) & (s.snakeY[0] == a.posY)) {
			s.length++;//���� ���������� �� ���� ������
			a.SetRandomPosition(); //������ ������������ � ������ ��������� �����
		}
		for(int k = 0; k < s.length; k++) {
			if((s.snakeX[k] == a.posX) & (s.snakeY[k]== a.posY)) a.SetRandomPosition(); // �������� �� ��, ��� �� ������ �� ���������� � ���� ����
		}
		repaint();
	}
	private class Keyboard extends KeyAdapter{
		public void keyPressed(KeyEvent kEvt) {
			int key = kEvt.getKeyCode();
			//�� ������� �������, ���������� direction ������������� ������ ��������,
			// ��� ����, ������ �������� ����������� �� ��������������� �������� ����� �������
			if((key == KeyEvent.VK_RIGHT) & (s.direction != 2)) s.direction = 0;
			if((key == KeyEvent.VK_DOWN) & (s.direction != 3)) s.direction = 1;
			if((key == KeyEvent.VK_LEFT) & (s.direction != 0)) s.direction = 2;
			if((key == KeyEvent.VK_UP) & (s.direction != 1)) s.direction = 3;
		}
	}

}
