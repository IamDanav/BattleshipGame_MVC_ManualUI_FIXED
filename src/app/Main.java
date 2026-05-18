package app;
import controller.GameController;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameController().startGame());
    }
}
