package main;
import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
