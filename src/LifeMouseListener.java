/**
 * GAME OF LIFE MOUSE LISTENER CLASS
 * @author Serena He
 * ----------------------------------------------------------------------------------
 * Implementing the MouseListener class for the Game of Life
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class LifeMouseListener implements MouseListener {

    GameOfLife game;

    public LifeMouseListener(GameOfLife game) {
        this.game = game;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object ob = e.getSource();
        JButton button = (JButton) ob;

        boolean leftPressed = SwingUtilities.isLeftMouseButton(e);
        boolean rightPressed = SwingUtilities.isRightMouseButton(e);

        // Click on LifeCell
        if (button.getName() == null && leftPressed && !game.isRunning()) {
            LifeCell cell = (LifeCell) button;
            cell.cellClicked();
        }
        // Start/Stop
        else if (button.getName() == "start") {
            game.startClicked();
        }
        // Clear
        else if (button.getName() == "clear" && !game.isRunning()) {
            game.clearGrid();
        }
        // Blurb
        else if (button.getName() == "blurb") {
            game.blurbClicked();
        }
        else {
            // Do nothing
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}