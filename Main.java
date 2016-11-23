import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    Gui gui = new Gui();
    gui.setVisible(true);
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    if (args.length > 0 && args[0].equals("-clv")) {
      gui.visualizeBoardOnCommandLine();
    }
  }
}
