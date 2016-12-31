import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageErrorWindow extends JFrame implements ActionListener {

  JLabel errorLabel;
  JButton backButton;

  public ImageErrorWindow() {
    super("Error!");
    setSize(310, 100);

    Container contentPane = this.getContentPane();
    contentPane.setLayout(new FlowLayout());

    errorLabel = new JLabel("The File You Selected is Not a Proper Image");

    backButton = new JButton("Go Back");

    contentPane.add(errorLabel);
    contentPane.add(backButton);
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();
  }
}
