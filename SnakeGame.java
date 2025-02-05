import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class Level {
    private final int delay;
    private final int scoreIncrement;
    private final int lengthIncrement;

    public Level(int delay, int scoreIncrement, int lengthIncrement) {
        this.delay = delay;
        this.scoreIncrement = scoreIncrement;
        this.lengthIncrement = lengthIncrement;
    }

    public int getDelay() {
        return delay; 
    }

    public int updateScore(int currentScore) {
        return currentScore + scoreIncrement;
    }

    public int updateLength(int currentLength) {
        return currentLength + lengthIncrement;
    }
}

class EasyLevel extends Level {
    public EasyLevel() {
        super(100, 1, 1); 
    }
}

class MediumLevel extends Level {
    private int currentDelay;

    public MediumLevel() {
        super(75, 1, 1); 
        this.currentDelay = 75; 
    }

    @Override
    public int getDelay() {
        return currentDelay;
    }

    @Override
    public int updateLength(int currentLength) {
        return currentLength + 1; 
    }

    @Override
    public int updateScore(int currentScore) {
        return currentScore + 1; 
    }

    public void increaseSpeed() {
        
        if (currentDelay > 30) { 
            currentDelay -= 2; 
        }
    }
}

class HardLevel extends Level {
    private int currentDelay;

    public HardLevel() {
        super(50, 1, 2);
        this.currentDelay = 50; 
    }

    @Override
    public int getDelay() {
        return currentDelay;
    }

    @Override
    public int updateLength(int currentLength) {
        return currentLength + 2; 
    }

    @Override
    public int updateScore(int currentScore) {
        return currentScore + 1; 
    }

    public void increaseSpeed() {
        if (currentDelay > 20) {
            currentDelay -= 3; 
        }
    }
}

class SnakeGame extends JFrame {
    private JPanel currentPanel;
    private HomePanel homePanel;
    private GamePanel gamePanel;
    private EndPanel endPanel;

    public SnakeGame() {
        setTitle("Snake Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        homePanel = new HomePanel(this);
        gamePanel = new GamePanel(this);
        endPanel = new EndPanel(this);

        switchToHomePanel();  
        setSize(700, 700); 
    }

    public void switchToHomePanel() {
        switchPanel(homePanel);  
    }

    public void switchToGamePanel() {
        switchPanel(gamePanel);
        gamePanel.startGame();
        gamePanel.requestFocusInWindow(); 
    }

    public void switchToEndPanel(int score) {
        endPanel.setScore(score);
        switchPanel(endPanel); 
    }

    private void switchPanel(JPanel newPanel) {
        if (currentPanel != null) {
            remove(currentPanel);  
        }
        currentPanel = newPanel;  
        add(currentPanel);  
        revalidate();  
        repaint();  
    }

    public void setSnakeColor(Color color) {
        gamePanel.setSnakeColor(color);  
    }

    public void setGameDifficulty(int delay) {
        gamePanel.setGameSpeed(delay);  
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new SnakeGame();
            frame.setVisible(true);  
        });
    }
}

class HomePanel extends JPanel {
    private SnakeGame parentFrame;

    public HomePanel(SnakeGame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(245, 245, 220));
        setPreferredSize(new Dimension(700, 700));  

        JLabel titleLabel = new JLabel("Snake Game");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 60));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Serif", Font.BOLD, 30));
        startButton.setFocusable(false);
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        startButton.addActionListener(e -> {
            parentFrame.switchToGamePanel(); 
        });

        JButton changeColorButton = new JButton("Change Snake Color");
        changeColorButton.setFont(new Font("Serif", Font.BOLD, 20));
        changeColorButton.setFocusable(false);
        changeColorButton.setAlignmentX(CENTER_ALIGNMENT);
        changeColorButton.addActionListener(e -> showColorSelectionDialog(parentFrame));

        JButton chooseLevelButton = new JButton("Choose Level");
        chooseLevelButton.setFont(new Font("Serif", Font.BOLD, 20));
        chooseLevelButton.setFocusable(false);
        chooseLevelButton.setAlignmentX(CENTER_ALIGNMENT);
        chooseLevelButton.addActionListener(e -> showLevelSelectionDialog(parentFrame));

        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createVerticalStrut(30));
        add(changeColorButton);
        add(Box.createVerticalStrut(30));
        add(chooseLevelButton); // Added button
        add(Box.createVerticalStrut(30));
        add(startButton);
        add(Box.createVerticalGlue());
    }

    private void showColorSelectionDialog(SnakeGame parentFrame) {
        String[] colorNames = {"Red", "Orange", "Yellow", "Blue", "Purple", "Gray"};
        Color[] colors = {
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.BLUE,
            new Color(128, 0, 128),
            Color.GRAY
        };

        JComboBox<String> colorDropdown = new JComboBox<>(colorNames);
        int option = JOptionPane.showConfirmDialog(
            this,
            colorDropdown,
            "Select Snake Body Color",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            int selectedIndex = colorDropdown.getSelectedIndex();
            parentFrame.setSnakeColor(colors[selectedIndex]); 
        }
    }

    private void showLevelSelectionDialog(SnakeGame parentFrame) {
        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new BoxLayout(levelPanel, BoxLayout.Y_AXIS));
        levelPanel.setBackground(new Color(245, 245, 220));

        EasyLevel easyLevel = new EasyLevel();
        MediumLevel mediumLevel = new MediumLevel();
        HardLevel hardLevel = new HardLevel();

        JButton easyButton = new JButton("Easy");
        easyButton.setFont(new Font("Serif", Font.BOLD, 20));
        easyButton.addActionListener(e -> {
            parentFrame.setGameDifficulty(easyLevel.getDelay()); 
        });

        JButton mediumButton = new JButton("Medium");
        mediumButton.setFont(new Font("Serif", Font.BOLD, 20));
        mediumButton.addActionListener(e -> {
            parentFrame.setGameDifficulty(mediumLevel.getDelay()); 
        });

        JButton hardButton = new JButton("Hard");
        hardButton.setFont(new Font("Serif", Font.BOLD, 20));
        hardButton.addActionListener(e -> {
            parentFrame.setGameDifficulty(hardLevel.getDelay()); 
        });

        levelPanel.add(easyButton);
        levelPanel.add(mediumButton);
        levelPanel.add(hardButton);

        int option = JOptionPane.showConfirmDialog(this, levelPanel, "Select Difficulty", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            parentFrame.switchToGamePanel();
        }
    }
}

class EndPanel extends JPanel {
    private JLabel scoreLabel;

    public EndPanel(SnakeGame parentFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(245, 245, 220));
        setPreferredSize(new Dimension(700, 700));

        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 60));
        gameOverLabel.setAlignmentX(CENTER_ALIGNMENT);

        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 40));
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Serif", Font.BOLD, 30));
        playAgainButton.setFocusable(false);
        playAgainButton.setAlignmentX(CENTER_ALIGNMENT);
        playAgainButton.addActionListener(e -> parentFrame.switchToGamePanel());

        JButton homeButton = new JButton("Home");
        homeButton.setFont(new Font("Serif", Font.BOLD, 30));
        homeButton.setFocusable(false);
        homeButton.setAlignmentX(CENTER_ALIGNMENT);
        homeButton.addActionListener(e -> parentFrame.switchToHomePanel());

        add(Box.createVerticalGlue());
        add(gameOverLabel);
        add(Box.createVerticalStrut(20));
        add(scoreLabel);
        add(Box.createVerticalStrut(30));
        add(playAgainButton);
        add(Box.createVerticalStrut(20));
        add(homeButton);
        add(Box.createVerticalGlue());
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}

class GamePanel extends JPanel implements ActionListener {
    private final int GAME_SIZE = 675;
    private final int UNIT_SIZE = 25;
    private int DELAY = 100;

    private SnakeGame parentFrame;
    private Snake snake;
    private Food food;
    private GameController controller;
    private Timer timer;

    private JLabel scoreLabel; 

    public GamePanel(SnakeGame parentFrame) {
        this.parentFrame = parentFrame;
        setPreferredSize(new Dimension(GAME_SIZE, GAME_SIZE + 50)); 
        setBackground(new Color(245, 245, 220));
        setFocusable(true);
        setLayout(new BorderLayout()); 

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(scoreLabel, BorderLayout.NORTH);

        snake = new Snake(GAME_SIZE / UNIT_SIZE, Color.GREEN);
        food = new Food(GAME_SIZE, UNIT_SIZE);
        controller = new GameController(snake, food, new EasyLevel()); 

        addKeyListener(new KeyHandler(snake));
    }

    public void startGame() {
        if (!snake.isRunning()) {
            snake = new Snake(GAME_SIZE / UNIT_SIZE, snake.getSnakeColor());
            food.placeNewFood(false);
            controller = new GameController(snake, food, new EasyLevel());

            addKeyListener(new KeyHandler(snake));

            timer = new Timer(DELAY, this);
            timer.start();
            snake.setRunning(true);
            requestFocusInWindow();
            repaint();
        }
    }

    public void setSnakeColor(Color color) {
        snake.setSnakeColor(color);
        repaint();
    }

    public void setGameSpeed(int delay) {
        DELAY = delay;
        if (timer != null) {
            timer.setDelay(DELAY);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (snake.isRunning()) {
            snake.move();
            controller.checkFoodCollision();
            controller.checkCollisions(snake, GAME_SIZE, GAME_SIZE);

            scoreLabel.setText("Score: " + controller.getScore());

            if (controller.getLevel() instanceof MediumLevel) {
                int newDelay = ((MediumLevel) controller.getLevel()).getDelay();
                if (timer.getDelay() != newDelay) {
                    timer.setDelay(newDelay);
                }
            }
        } 
        else {
            timer.stop();
            parentFrame.switchToEndPanel(controller.getScore());
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (snake.isRunning()) {
            food.draw(g, UNIT_SIZE);
            snake.draw(g, UNIT_SIZE);
        }
    }

    public void setGameLevel(Level level) {
        DELAY = level.getDelay();
        if (timer != null) {
            timer.setDelay(DELAY);
        }
        controller = new GameController(snake, food, level);
    }
}

class Snake {
    private final int[] x;
    private final int[] y;
    private int bodyParts = 4;
    private char direction = 'R';
    private boolean running = false;
    private Color snakeColor; 

    public Snake(int maxUnits, Color initialColor) {
        x = new int[maxUnits];
        y = new int[maxUnits];
        this.snakeColor = initialColor;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U' -> y[0] -= 25;
            case 'D' -> y[0] += 25;
            case 'L' -> x[0] -= 25;
            case 'R' -> x[0] += 25;
        }
    }

    public void grow() {
        bodyParts++;
    }

    public void draw(Graphics g, int unitSize) {
        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(snakeColor);
            }
            g.fillRect(x[i], y[i], unitSize, unitSize);
        }
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public Color getSnakeColor() {
        return snakeColor;
    }

    public void setSnakeColor(Color snakeColor) {
        this.snakeColor = snakeColor;
    }

    public void stop() {
        this.running = false;
    }

    public void setBodyParts(int bodyParts) {
        this.bodyParts = bodyParts;
    }
}

class Food {
    private int x;
    private int y;
    private final int gameSize; 
    private final int unitSize;
    private final Random random;
    private boolean isSuperFood;

    public Food(int gameSize, int unitSize) {
        this.gameSize = gameSize; 
        this.unitSize = unitSize;
        random = new Random();
    }

    public void placeNewFood(boolean isSuperFood) {
        x = (random.nextInt(gameSize / unitSize)) * unitSize;
        y = (random.nextInt(gameSize / unitSize)) * unitSize;
        this.isSuperFood = isSuperFood;
    }

    public void draw(Graphics g, int unitSize) {
        if (isSuperFood) {
            g.setColor(new Color(139, 0, 0));
            g.fillOval(x, y, unitSize + 3, unitSize + 3);
        } else {
            g.setColor(Color.red);
            g.fillOval(x, y, unitSize, unitSize);
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isSuperFood() { return isSuperFood; }
}

class GameController {
    private Snake snake;
    private Food food;
    private int score = 0;
    private Level level; 

    public GameController(Snake snake, Food food, Level level) {
        this.snake = snake;
        this.food = food;
        this.level = level;
    }

    public void checkFoodCollision() {
        if (snake.getX()[0] == food.getX() && snake.getY()[0] == food.getY()) {
            if (food.isSuperFood()) {
                score += 3; 
            } else {
                score = level.updateScore(score); 
            }

            int newLength = level.updateLength(snake.getBodyParts()); 
            snake.setBodyParts(newLength);

            if (level instanceof MediumLevel) {
                ((MediumLevel) level).increaseSpeed();  
            }
            else if (level instanceof HardLevel) {
                ((HardLevel) level).increaseSpeed();  
            }

            food.placeNewFood(score % 8 == 0); 
        }
    }

    public void checkCollisions(Snake snake, int gameWidth, int gameHeight) {
        for (int i = snake.getBodyParts(); i > 0; i--) {
            if (snake.getX()[0] == snake.getX()[i] && snake.getY()[0] == snake.getY()[i]) {
                snake.stop();
            }
        }

        if (snake.getX()[0] < 0 || snake.getX()[0] >= gameWidth || 
            snake.getY()[0] < 0 || snake.getY()[0] >= gameHeight) {
            snake.stop();
        }
    }

    public int getScore() {
        return score;
    }

    public Level getLevel() {
        return level; 
    }

    public void drawScore(Graphics g, int gameSize) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 25));
        String scoreText = "Score: " + score;
        g.drawString(scoreText, 10, gameSize - 10);
    }
}

class KeyHandler extends KeyAdapter {
    private Snake snake;

    public KeyHandler(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                if (snake.getDirection() != 'R') snake.setDirection('L');
            }
            case KeyEvent.VK_RIGHT -> {
                if (snake.getDirection() != 'L') snake.setDirection('R');
            }
            case KeyEvent.VK_UP -> {
                if (snake.getDirection() != 'D') snake.setDirection('U');
            }
            case KeyEvent.VK_DOWN -> {
                if (snake.getDirection() != 'U') snake.setDirection('D');
            }
        }
    }
}
