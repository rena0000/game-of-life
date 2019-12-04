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

    LifeCell() {
        /** CONSTRUCTOR
         *  Construct a new LifeCell
         */
        // Attributes
        isAlive = false;
        willBeAlive = false;
        // GUI
        setSize(CELL_DIMENSION);
        setBackground(Color.GRAY);
    }

    public void setAlive() {
        isAlive = true;
        setBackground(Color.YELLOW);
    }

    public void setDead() {
        isAlive = false;
        setBackground(Color.GRAY);
    }

    public void cellClicked() {
        if (isAlive) {
            setDead();
        }
        else {
            setAlive();
        }
    }


}
