package UNO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainVentanaUNO extends JFrame {
    public static void main(String[] args) throws IOException {
        new MainVentanaUNO().setVisible(true);
    }
    public MainVentanaUNO() throws IOException {
        super("UNO");
        Container cp = getContentPane();
        cp.add(new MesaPanel());
        pack();
    }
}
