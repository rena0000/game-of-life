/**
 * GAME OF LIFE CELL CLASS
 * @author Serena He
 * ----------------------------------------------------------------------------------
 * Extending the JButton class from Java Swing, defines a cell object in the life grid.
 */

import javax.swing.*;
import java.awt.*;

public class LifeCell extends JButton {

    // -----DIMENSIONS-----
    static final int CELL_WIDTH = GameOfLife.GRID_WIDTH/GameOfLife.COLS;
    static final Dimension CELL_DIMENSION = new Dimension(CELL_WIDTH, CELL_WIDTH);

    // -----ATTRIBUTES-----
    private boolean isAlive;
    private boolean willBeAlive;
    // Game
    GameOfLife game;

    LifeCell(GameOfLife game) {
        /** CONSTRUCTOR
         *  Construct a new LifeCell
         */
        // Attributes
        isAlive = false;
        willBeAlive = false;
        this.game = game;
        // GUI
        setSize(CELL_DIMENSION);
        setBackground(Color.GRAY);
    }

    // -----GETTER/SETTERS-----

    public boolean getIsAlive() {
        /**
         * Return if the cell is currently alive or not.
         * @return boolean If the cell is alive.
         */
        return isAlive;
    }

    void setAlive(boolean isAlive) {
        /**
         * Set the current life status of the cell.
         * @param isAlive Set the life status to this.
         * @return Nothing.
         */
        this.isAlive = isAlive;
    }

    void setWillBeAlive(boolean willBeAlive) {
        /**
         * Set the next stage life status of the cell.
         * @param willBeAlive Set the next stage life status to this.
         */
        this.willBeAlive = willBeAlive;
    }

    void setCellColour() {
        /**
         * Set the cell colour based on the current life status.
         * @return Nothing.
         */
        if (isAlive) {
            setBackground(Color.YELLOW);
        }
        else {
            setBackground(Color.GRAY);
        }
    }

    // -----GAME FUNCTION-----

    public void moveToNextStage() {
        /**
         * Move to the next stage by setting the current life status to the next stage life status.
         * Reset the next stage life status to false.
         * @return Nothing.
         */
        isAlive = willBeAlive;
        willBeAlive = false;
    }

    // -----MOUSE ACTION-----

    public void cellClicked() {
        /**
         * Set the cell life status based on user input.
         * @return Nothing.
         */
        // Grid is changing ,reset stages to zero
        if (game.getLifeStage() != 0) {
            game.resetLifeStage();
        }
        // Set cell to dead
        if (isAlive) {
            setAlive(false);
            setCellColour();
            game.decrementPopCount();
        }
        // Set cell to alive
        else {
            setAlive(true);
            setCellColour();
            game.incrementPopCount();
        }
    }


}
