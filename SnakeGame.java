import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private static final int WIDTH = 1500;
    private static final int HEIGHT = 800;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 100;

    private LinkedList<Point> snake;
    private Point food;
    private char direction = 'R';
    private boolean running = false;

    private SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(this);
        startGame();
    }

    private void startGame() {
        snake = new LinkedList<>();
        snake.add(new Point(0, 0));
        generateFood();
        running = true;
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    private void generateFood() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int y = rand.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        food = new Point(x, y);
    }

    private void move() {
        Point head = snake.getFirst();
        Point newHead = (Point) head.clone();

        switch (direction) {
            case 'U':
                newHead.translate(0, -UNIT_SIZE);
                break;
            case 'D':
                newHead.translate(0, UNIT_SIZE);
                break;
            case 'L':
                newHead.translate(-UNIT_SIZE, 0);
                break;
            case 'R':
                newHead.translate(UNIT_SIZE, 0);
                break;
        }

        if (newHead.equals(food)) {
            snake.addFirst(newHead);
            generateFood();
        } else {
            snake.addFirst(newHead);
            snake.removeLast();
        }
    }

    private boolean checkCollision() {
        Point head = snake.getFirst();

        if (head.getX() < 0 || head.getX() >= WIDTH || head.getY() < 0 || head.getY() >= HEIGHT) {
            return true;
        }

        for (Point bodyPart : snake.subList(1, snake.size())) {
            if (head.equals(bodyPart)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (running) {
            g.setColor(Color.red);
            g.fillRect(food.x, food.y, UNIT_SIZE, UNIT_SIZE);

            for (Point bodyPart : snake) {
                g.setColor(Color.green);
                g.fillRect(bodyPart.x, bodyPart.y, UNIT_SIZE, UNIT_SIZE);
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            if (checkCollision()) {
                running = false;
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (direction != 'R')) {
            direction = 'L';
        } else if ((key == KeyEvent.VK_RIGHT) && (direction != 'L')) {
            direction = 'R';
        } else if ((key == KeyEvent.VK_UP) && (direction != 'D')) {
            direction = 'U';
        } else if ((key == KeyEvent.VK_DOWN) && (direction != 'U')) {
            direction = 'D';
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame snakeGame = new SnakeGame();
        frame.add(snakeGame);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
