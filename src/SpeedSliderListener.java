/**
 * GAME OF LIFE SLIDER LISTENER CLASS
 * @author Serena He
 * ----------------------------------------------------------------------------------
 * Implementing the ChangeListener class to read the speed slider
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SpeedSliderListener implements ChangeListener {

    GameOfLife game;

    public SpeedSliderListener(GameOfLife game) {
        this.game = game;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider)e.getSource();

        int newValue = slider.getValue();
        game.setLifeTimerDelay(newValue);
    }
}
