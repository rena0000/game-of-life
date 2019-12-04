/**
 * CONWAY'S GAME OF LIFE COPYCAT
 * @author Serena He
 * ----------------------------------------------------------------------------------
 * Main class for GameOfLife-Copycat.
 * Begins a GameOfLife game
 */

import java.awt.*;
import java.lang.management.MemoryNotificationInfo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameOfLife {

    // -----GUI-----
    // Dimensions
    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 700; // 10:9 ratio
    static final int GRID_WIDTH = 650;
    static final int MENU_WIDTH = 100;

    static final Dimension GAME_DIMENSION = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final Dimension GRID_DIMENSION = new Dimension(GRID_WIDTH, GRID_WIDTH);
    static final Dimension MENU_DIMENSION = new Dimension(MENU_WIDTH, GAME_HEIGHT);
    // Frames
    private JFrame gameFrame = new JFrame("Conway's Game of Life - Copycat");
    private JFrame blurbFrame = new JFrame("Blurb");
    // Panels
    private JPanel gridPanel = new JPanel();
    private JPanel menuPanel = new JPanel();
    private JPanel finalPanel = new JPanel();
    private JPanel blurbPanel = new JPanel();
    // Buttons
    private JButton startStopButton = new JButton();
    private JButton clearButton = new JButton();
    private JButton blurbButton = new JButton();
    // Labels
    private JLabel blurbLabel = new JLabel();

    // -----GAME-----
    static final int ROWS = 50;
    static final int COLS = 50;
    LifeCell[][] lifeGrid;

    public GameOfLife() {
        /**
         * Constructor for a new Game of Life
         */

        // -----GRID-----
        lifeGrid = new LifeCell[ROWS][COLS];
        for (int row=0; row < ROWS; row++) {
            for (int col=0; col < COLS; col++) {
                LifeCell cell = new LifeCell();
                lifeGrid[row][col] = cell;
                cell.addMouseListener(new LifeMouseListener(this));
            }
        }
        // -----MOUSE-----
        startStopButton.addMouseListener(new LifeMouseListener(this));
        clearButton.addMouseListener(new LifeMouseListener(this));
        blurbButton.addMouseListener(new LifeMouseListener(this));

        // -----BLURB-----
        blurbFrame.setSize(100, 200);
        blurbPanel.setSize(100, 200);
        blurbLabel.setText("Testing\nTesting");
        blurbPanel.add(blurbLabel);
        blurbFrame.add(blurbPanel);

        // Start
        startGame();
    }

    private void startGame() {
        /**
         * Starts the GUI for the game.
         * @return Nothing.
         */

        // -----GRID-----
        gridPanel.setPreferredSize(GRID_DIMENSION);
        gridPanel.setLayout(new GridLayout(ROWS, COLS, 0, 0));
        gridPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Add life cells to panel
        for (int row=0; row < ROWS; row++) {
            for (int col=0; col < COLS; col++) {
                gridPanel.add(lifeGrid[row][col]);
            }
        }

        // -----MENU-----
        // Panel
        menuPanel.setSize(MENU_DIMENSION);
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(new Insets(50, 10, 50, 10)));
        menuPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        // Button name
        startStopButton.setName("start");
        clearButton.setName("clear");
        blurbButton.setName("blurb");
        // Button text
        startStopButton.setText("Start");
        clearButton.setText("Clear");
        blurbButton.setText("Blurb");
        // Button size
        startStopButton.setSize(MENU_WIDTH, 20);
        clearButton.setSize(MENU_WIDTH, 20);
        blurbButton.setSize(MENU_WIDTH, 20);
        // Button alignment
        startStopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        blurbButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        blurbButton.setAlignmentY(Component.BOTTOM_ALIGNMENT );
        // Add buttons to panel
        menuPanel.add(startStopButton);
        menuPanel.add(Box.createRigidArea(new Dimension(MENU_WIDTH, 10)));
        menuPanel.add(clearButton);
        menuPanel.add(Box.createRigidArea(new Dimension(MENU_WIDTH, 50)));
        menuPanel.add(blurbButton);

        // -----FINAL PANEL-----
        finalPanel.add(gridPanel);
        finalPanel.add(menuPanel);
        finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.X_AXIS));
        // Start frame
        gameFrame.add(finalPanel);
        gameFrame.setLayout(new FlowLayout());
        gameFrame.setSize(GAME_DIMENSION);
        gameFrame.pack();
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    void clearGrid() {
        /**
         * Set all cells to dead
         * @return Nothing.
         */
        for (int row=0; row < ROWS; row++) {
            for (int col=0; col < COLS; col++) {
                lifeGrid[row][col].setDead();
            }
        }
    }

    void blurbClicked() {
        /**
         * Open blurb window
         * @return Nothing.
         */
        blurbFrame.pack();
        blurbFrame.setResizable(false);
        blurbFrame.setLocationRelativeTo(null);
        blurbFrame.setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Game dimension " + GAME_DIMENSION);
        System.out.println("Grid dimension " + GRID_DIMENSION);
        System.out.println("Cell dimension " + LifeCell.CELL_DIMENSION);

        GameOfLife newGame = new GameOfLife();
    }
}
