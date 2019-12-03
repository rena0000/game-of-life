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
    private final int CELL_WIDTH = GameOfLife.GAME_WIDTH/GameOfLife.COLS;
    private final Dimension CELL_DIMENSION = new Dimension(CELL_WIDTH, CELL_WIDTH);

    // -----ATTRIBUTES-----
    private boolean isAlive;
    private boolean willBeAlive;

    public LifeCell() {
        // Attributes
        isAlive = false;
        willBeAlive = false;
        // GUI
        setSize(CELL_DIMENSION);
        setBackground(Color.GRAY);
    }
}
