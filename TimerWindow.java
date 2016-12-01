import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerWindow extends JFrame implements ActionListener {

  JLabel infoLabel;
  JLabel timeText;

  javax.swing.Timer timer;
  int timerSpeed = 1000, currentTime = 3;

  public TimerWindow() {
    super("Timer");
    setSize(150, 200);

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
    Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

    setLocation((int)rect.getMaxX(), 0);

    Container contentPane = this.getContentPane();
    contentPane.setLayout(new BorderLayout());

    infoLabel = new JLabel("Time left: ");
    infoLabel.setHorizontalAlignment(JLabel.CENTER);
    contentPane.add(infoLabel, BorderLayout.NORTH);

    timeText = new JLabel(Integer.toString(currentTime));
    timeText.setHorizontalAlignment(JLabel.CENTER);
    timeText.setFont(new Font(timeText.getFont().getName(), Font.PLAIN, 100));
    contentPane.add(timeText, BorderLayout.CENTER);

    timer = new javax.swing.Timer(timerSpeed, this);
    timer.start();
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();

    if (source == timer) {
      currentTime--;
      if (currentTime < 0) currentTime = 3;
      timeText.setText(Integer.toString(currentTime));
    }
  }
}
