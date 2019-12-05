/**
 * CONWAY'S GAME OF LIFE COPYCAT
 * @author Serena He
 * ----------------------------------------------------------------------------------
 * Main class for GameOfLife-Copycat. Begins a GameOfLife game
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private JLabel lifeStageLabel = new JLabel();
    // Editor pane
    private JEditorPane blurbText = new JEditorPane();
    private static final java.net.URL blurbURL = GameOfLife.class.getResource("blurb.html");

    // -----GAME-----
    static final int ROWS = 50;
    static final int COLS = 50;
    LifeCell[][] lifeGrid;
    // Timing
    private int lifeStage;
    private Timer lifeTimer;
    // Status
    private boolean isRunning;

    public GameOfLife() {
        /**
         * Constructor for a new Game of Life
         */
        // Status
        lifeStage = 0;
        isRunning = false;

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
        // Set blurb editor pane
        blurbText.setEditable(false);
        if (blurbURL != null) {
            try {
                blurbText.setPage(blurbURL);
            }
            catch (IOException e) {
                System.err.println("Bad blurb URL: " + blurbURL);
            }
        }
        else {
            System.err.println("Blurb URL cannot be found");
        }

        blurbPanel.add(blurbText);
        blurbFrame.add(blurbPanel);

        // -----TIMER-----
        lifeTimer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNextLifeStage();
                lifeStage++;
                setLifeStageLabel();
            }
        });

        // Start
        startGame();
    }

    // -----GETTER/SETTERS-----

    boolean isRunning() {
        /**
         * Return if the game is running through life stages or not.
         * @return boolean If the game is running or not.
         */
        return isRunning;
    }

    // -----GAME START/STOP-----

    private void startGame() {
        /**
         * Starts the GUI for the game. Called only on initial opening of the game.
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

        // -----MENU PANEL-----
        // Panel
        menuPanel.setSize(MENU_DIMENSION);
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(new Insets(50, 10, 50, 10)));
        menuPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        // -----MENU BUTTONS-----
        // Name
        startStopButton.setName("start");
        clearButton.setName("clear");
        blurbButton.setName("blurb");
        // Text
        startStopButton.setText("Start");
        clearButton.setText("Clear");
        blurbButton.setText("Blurb");
        // Size
        startStopButton.setSize(MENU_WIDTH, 20);
        clearButton.setSize(MENU_WIDTH, 20);
        blurbButton.setSize(MENU_WIDTH, 20);
        // Alignment
        startStopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        blurbButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        blurbButton.setAlignmentY(Component.BOTTOM_ALIGNMENT );
        // Colour
        startStopButton.setBackground(Color.GREEN);

        // -----MENU LABELS-----
        setLifeStageLabel();
        lifeStageLabel.setPreferredSize(new Dimension(MENU_WIDTH, 20));
        lifeStageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // -----COMPOSE MENU-----
        // Add buttons to panel
        menuPanel.add(lifeStageLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(MENU_WIDTH,10 )));
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
         * Set all cells to dead and reset lifeStage.
         * @return Nothing.
         */
        for (int row=0; row < ROWS; row++) {
            for (int col=0; col < COLS; col++) {
                lifeGrid[row][col].setAlive(false);
                lifeGrid[row][col].setCellColour();
            }
        }
        lifeStage = 0;
        setLifeStageLabel();

    }

    void startClicked() {
        /**
         * Define actions after Start/Stop button is clicked.
         * If Start is clicked, start the timer and change the button to Stop.
         * If Stop is clicked, stop the timer and change the button to Start.
         * @return Nothing.
         */
        if (!isRunning) {
            // Clear stages
            lifeStage = 0;
            setLifeStageLabel();
            // Start timer
            lifeTimer.start();
            // Change start button to a stop
            isRunning = true;
            startStopButton.setText("Stop");
            startStopButton.setBackground(Color.RED);
        }
        else {
            // Stop timer
            lifeTimer.stop();
            // Change stop button to a start
            isRunning = false;
            startStopButton.setText("Start");
            startStopButton.setBackground(Color.GREEN);
        }
    }

    void setNextLifeStage() {
        /**
         * For every cell, determine if they will live or die in the next stage of life.
         * After setting if they will be alive, move them to the next stage of life.
         * @return Nothing.
         */
        // Determine willBeAlive status for every cell
        for (int row=0; row < ROWS; row++) {
            for (int col=0; col < COLS; col++) {
                int surroundingLives = 0;
                LifeCell currentCell = lifeGrid[row][col];

                // Check surrounding cells for life
                for (int i=-1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        int newRow = row + i;
                        int newCol = col + j;
                        // Check in range
                        boolean inRange = (newRow < ROWS) && (newCol < COLS) && (newRow > -1) && (newCol > -1) && (newCol != col || newRow != row);
                        if (inRange) {
                            LifeCell surroundingCell = lifeGrid[newRow][newCol];
                            if (surroundingCell.getIsAlive()) {
                                surroundingLives++;
                            }
                        }
                    }
                }

                // Determine life/death
                if (currentCell.getIsAlive()) { // Cell is alive and well :)
                    // Die of solitude or overpopulation
                    if (surroundingLives <= 1 || surroundingLives >= 4) {
                        currentCell.setWillBeAlive(false);
                    }
                    // Continue living
                    else {
                        currentCell.setWillBeAlive(true);
                    }
                }
                else { // Cell is dead :(
                    // Dead cell with 3 neighbours will live
                    if (surroundingLives == 3) {
                        currentCell.setWillBeAlive(true);
                    }
                    // Continue dead
                    else {
                        currentCell.setWillBeAlive(false);
                    }
                }
            }
        }

        // Update cell using willBeAlive status
        for (int row=0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                LifeCell cell = lifeGrid[row][col];
                cell.moveToNextStage();
                cell.setCellColour();
            }
        }
    }

    // -----MOUSE EVENTS-----
    void blurbClicked() {
        /**
         * Open blurb window.
         * @return Nothing.
         */
        blurbFrame.pack();
        blurbFrame.setResizable(false);
        blurbFrame.setLocationRelativeTo(null);
        blurbFrame.setVisible(true);
    }

    void setLifeStageLabel() {
        /**
         * Formats and sets the text for the life stage label using the current lifeStage.
         * @return Nothing.
         */
         lifeStageLabel.setText("Stage: " + lifeStage);
    }

    public static void main(String[] args) {
        GameOfLife newGame = new GameOfLife();
    }
}
