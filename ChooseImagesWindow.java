import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ChooseImagesWindow extends JFrame implements ActionListener {

  // Icon Stuff
  private ImageIcon player1Icon, player2Icon;
  private String player1IconPath, player2IconPath;

  private JButton player1ChooseButton, player2ChooseButton, submitButton;
  private JTextArea player1PathField, player2PathField;
  private JFileChooser player1IconFileChooser, player2IconFileChooser;

  public ChooseImagesWindow() {
    super("Choose Images");
    setSize(450, 110);

    Container contentPane = this.getContentPane();
    contentPane.setBackground(new Color(83, 85, 89));
    contentPane.setLayout(new BorderLayout());

    JPanel topArea = new JPanel();
    topArea.setLayout(new GridLayout(2, 2));

    topArea.setBackground(new Color(83, 85, 89));

    player1ChooseButton = new JButton("Choose Image For Player 1:");
    player2ChooseButton = new JButton("Choose Image For Player 2:");

    player1ChooseButton.setBackground(new Color(100, 110, 127));
    player2ChooseButton.setBackground(new Color(100, 110, 127));

    player1ChooseButton.addActionListener(this);
    player2ChooseButton.addActionListener(this);

    player1PathField = new JTextArea("Player 1 Path");
    player1PathField.setEditable(false);
    player1PathField.setBackground(new Color(100, 110, 127));

    player2PathField = new JTextArea("Player 2 Path");
    player2PathField.setEditable(false);
    player2PathField.setBackground(new Color(100, 110, 127));

    topArea.add(player1ChooseButton);
    topArea.add(player1PathField);
    topArea.add(player2ChooseButton);
    topArea.add(player2PathField);

    contentPane.add(topArea, BorderLayout.NORTH);

    JPanel bottomArea = new JPanel();
    bottomArea.setLayout(new FlowLayout());

    bottomArea.setBackground(new Color(83, 85, 89));

    submitButton = new JButton("Submit");
    submitButton.setBackground(new Color(100, 110, 127));
    submitButton.addActionListener(this);

    bottomArea.add(submitButton);

    contentPane.add(bottomArea, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();

    if (source == player1ChooseButton) {
      JButton open = new JButton();
      player1IconFileChooser = new JFileChooser();
      player1IconFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      player1IconFileChooser.setDialogTitle("Select Icon For Player 1");
      if (player1IconFileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION);
      player1IconPath = player1IconFileChooser.getSelectedFile().getAbsolutePath();

      player1PathField.setText(player1IconPath);

    } else if (source == player2ChooseButton) {
      JButton open = new JButton();
      player2IconFileChooser = new JFileChooser();
      player2IconFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      player2IconFileChooser.setDialogTitle("Select Icon For Player 2");
      if (player2IconFileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION);
      player2IconPath = player2IconFileChooser.getSelectedFile().getAbsolutePath();

      player2PathField.setText(player2IconPath);
    } else if (source == submitButton) {
      player1IconPath = player1PathField.getText();
      player2IconPath = player2PathField.getText();

      if ((player1IconPath.contains(".png") || player1IconPath.contains(".jpg")) &&
          (player2IconPath.contains(".png") || player2IconPath.contains(".jpg"))) {
            player1Icon = new ImageIcon(player1IconPath);
            player2Icon = new ImageIcon(player2IconPath);

            this.setVisible(false);

            GameGui gui = new GameGui(3);
            gui.setVisible(true);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gui.setImages(player1Icon, player2Icon);
      }
      else {
        ImageErrorWindow errorWindow = new ImageErrorWindow();
        errorWindow.setVisible(true);
        errorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
    }
  }

  public ImageIcon getPlayer1Icon() {
    return this.player1Icon;
  }

  public ImageIcon getPlayer2Icon() {
    return this.player2Icon;
  }

}
