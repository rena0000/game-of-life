
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class GameOfLife extends JFrame {
    final int FRAME_WIDTH=500;
    final int FRAME_HEIGHT=400;

    final int DEFAULT_ROWS=10;
    final int DEFAULT_COLS=10;

    private int rows;
    private int cols;
    private int[][] grid;

    public GameOfLife(String title) {
        // Constructor
        // Frame
        super(title);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout());

        // Label
        JLabel testLabel = new JLabel("Hello World");
        this.add(testLabel);

        // Game
        this.rows = DEFAULT_ROWS;
        this.cols = DEFAULT_COLS;
        grid = new int[rows][cols];

    }

    public void printGrid() {
        // Print game grid
        for(int row=0; row < rows; row++) {
            System.out.print("|");
            for(int col=0; col < cols; col++) {
                System.out.print(" 0 |");
            }
            System.out.println();
        }
    }

    public void showGame() {
        this.setVisible(true);
    }

    public static void main(String[] args) {
        final String TITLE = "Test";
        GameOfLife game = new GameOfLife(TITLE);
        game.showGame();
        game.printGrid();
    }
}