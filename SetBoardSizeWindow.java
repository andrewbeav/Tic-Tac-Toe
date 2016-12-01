import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SetBoardSizeWindow extends JFrame implements ActionListener {

  JLabel infoLabel;
  JTextField textField;
  JButton submitButton;

  boolean toResize = false;

  int boardSize;

  public SetBoardSizeWindow() {
    super("Set Board Size");
    setSize(200, 100);

    Container contentPane = this.getContentPane();
    contentPane.setLayout(new FlowLayout());

    contentPane.setBackground(new Color(83, 85, 89));

    infoLabel = new JLabel("Board Size:");
    contentPane.add(infoLabel);

    textField = new JTextField("3");
    contentPane.add(textField);

    submitButton = new JButton("Submit Changes");
    contentPane.add(submitButton);
    submitButton.addActionListener(this);
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();

    if (source == submitButton) { // Just to be sure

      boardSize = Integer.parseInt(textField.getText());

      GameGui gui = new GameGui(boardSize); // creating new Gui object
      gui.setVisible(true); // making it visible
      gui.setResizable(false);
      gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // setting default close operation
    }
  }

}
