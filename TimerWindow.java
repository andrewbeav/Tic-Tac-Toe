import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerWindow extends JFrame {

  JLabel infoLabel;
  JLabel timeText;

  public TimerWindow() {
    super("Timer");
    setSize(150, 200);

    Container contentPane = this.getContentPane();
    contentPane.setLayout(new BorderLayout());

    infoLabel = new JLabel("Time left: ");
    infoLabel.setHorizontalAlignment(JLabel.CENTER);
    contentPane.add(infoLabel, BorderLayout.NORTH);

    timeText = new JLabel("3");
    timeText.setHorizontalAlignment(JLabel.CENTER);
    timeText.setFont(new Font(timeText.getFont().getName(), Font.PLAIN, 100));
    contentPane.add(timeText, BorderLayout.CENTER);
  }
}
