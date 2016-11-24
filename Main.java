import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    CustomizeWindow gui = new CustomizeWindow(); // creating new Gui object
    gui.setVisible(true); // making it visible
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // setting default close operation

    /*if (args.length > 0 && args[0].equals("-clv")) { // clv = 'command line visualization'
      gui.visualizeBoardOnCommandLine();
    }*/
  }
}
