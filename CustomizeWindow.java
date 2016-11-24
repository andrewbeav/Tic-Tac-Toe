import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomizeWindow extends JFrame implements ActionListener {

  JLabel selectImageLabel;

  JRadioButton androidVsAppleButton;
  JRadioButton xoButton;
  ButtonGroup buttonGroup;

  JButton submitButton;

  private ImageIcon player1Icon, player2Icon;

  public CustomizeWindow() {
    super("Tic Tac Toe");
    setSize(200, 200);

    Container contentPane = this.getContentPane();
    contentPane.setLayout(new FlowLayout());
    contentPane.setBackground(new Color(83, 85, 89));

    selectImageLabel = new JLabel("Select Images");
    contentPane.add(selectImageLabel);

    androidVsAppleButton = new JRadioButton("Android vs. Apple", false);
    xoButton = new JRadioButton("X vs. O", false);
    androidVsAppleButton.setBackground(new Color(83, 85, 89));
    xoButton.setBackground(new Color(83, 85, 89));
    contentPane.add(androidVsAppleButton);
    contentPane.add(xoButton);
    androidVsAppleButton.addActionListener(this);
    xoButton.addActionListener(this);

    buttonGroup = new ButtonGroup();
    buttonGroup.add(androidVsAppleButton);
    buttonGroup.add(xoButton);

    submitButton = new JButton("Start Playing Game");
    contentPane.add(submitButton);
    submitButton.setBackground(new Color(100, 110, 127));
    submitButton.addActionListener(this);
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();

    if (source == androidVsAppleButton) {
      player1Icon = new ImageIcon(getClass().getClassLoader().getResource("android.png"));
      player2Icon = new ImageIcon(getClass().getClassLoader().getResource("apple.png"));
    } else if (source == xoButton) {
      player1Icon = new ImageIcon(getClass().getClassLoader().getResource("x.png"));
      player2Icon = new ImageIcon(getClass().getClassLoader().getResource("o.png"));
    } else if (source == submitButton) {
      GameGui gameGui = new GameGui(player1Icon, player2Icon);
      gameGui.setVisible(true);
      gameGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      this.setVisible(false);
    }
  }
}
