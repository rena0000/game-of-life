/**
 * CONWAY'S GAME OF LIFE COPYCAT
 * @author Serena He
 * ----------------------------------------------------------------------------------
 * Main class for GameOfLife-Copycat.
 * Begins a GameOfLife game
 */

import java.awt.*;
import javax.swing.*;

public class GameOfLife {

    // -----GUI-----
    // Dimensions
    static final Dimension SCREEN_DIM = Toolkit.getDefaultToolkit().getScreenSize();
    static final int GAME_WIDTH = (int)(SCREEN_DIM.getWidth()/2.5);
    static final int GAME_HEIGHT = (int)(GAME_WIDTH*0.8); // 5:4 ratio
    static final Dimension GAME_DIMENSION = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final Dimension GRID_DIMENSION = new Dimension(GAME_HEIGHT, GAME_HEIGHT);
    static final int MENU_WIDTH = (int)(GAME_WIDTH*0.2);
    static final Dimension MENU_DIMENSION = new Dimension(MENU_WIDTH, GAME_HEIGHT);
    // Frame
    private JFrame gameFrame = new JFrame("Conway's Game of Life - Copycat");
    // Panels
    private JPanel gridPanel = new JPanel();
    private JPanel menuPanel = new JPanel();

    // -----GAME-----
    static final int ROWS = 30;
    static final int COLS = 30;
    LifeCell[][] lifeGrid;

    public GameOfLife() {
        /**
         * Constructor for a new Game of Life
         */
        // Add life cells to grid
        lifeGrid = new LifeCell[ROWS][COLS];
        for (int row=0; row < ROWS; row++) {
            for (int col=0; col < COLS; col++) {
                LifeCell cell = new LifeCell();
                lifeGrid[row][col] = cell;
            }
        }

        startGame();
    }

    private void startGame() {
        /**
         * Starts the GUI for the game.
         * @return Nothing.
         */

        // Grid
        gridPanel.setPreferredSize(GRID_DIMENSION);
        gridPanel.setLayout(new GridLayout(ROWS, COLS, 0, 0));
        // Add life cells to panel
        for (int row=0; row < ROWS; row++) {
            for (int col=0; col < COLS; col++) {
                gridPanel.add(lifeGrid[row][col]);
            }
        }

        // Menu
        menuPanel.setPreferredSize(MENU_DIMENSION);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Add components
        gameFrame.add(gridPanel);
        gameFrame.add(menuPanel);

        // Start frame
        gameFrame.setSize(GAME_DIMENSION);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
    }

    public static void main(String[] args) {
        GameOfLife newGame = new GameOfLife();
    }
}
