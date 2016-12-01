import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    GameGui gui = new GameGui(3); // creating new Gui object
    gui.setVisible(true); // making it visible
    gui.setResizable(false);
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // setting default close operation

    if (args.length > 0 && args[0].equals("-clv")) { // clv = 'command line visualization'
      gui.visualizeBoardOnCommandLine();
    }
  }
}
