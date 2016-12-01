import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerWindow extends JFrame implements ActionListener {

  JLabel infoLabel;
  JLabel timeText;

  //javax.swing.Timer timer;
  int currentTime = 3;

  public TimerWindow() {
    super("Timer");
    setSize(150, 200);

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
    Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

    setLocation((int)rect.getMaxX(), 0);

    Container contentPane = this.getContentPane();
    contentPane.setLayout(new BorderLayout());

    contentPane.setBackground(new Color(83, 85, 89));

    infoLabel = new JLabel("Time left: ");
    infoLabel.setHorizontalAlignment(JLabel.CENTER);
    contentPane.add(infoLabel, BorderLayout.NORTH);

    timeText = new JLabel(Integer.toString(currentTime));
    timeText.setHorizontalAlignment(JLabel.CENTER);
    timeText.setFont(new Font(timeText.getFont().getName(), Font.PLAIN, 100));
    contentPane.add(timeText, BorderLayout.CENTER);

    //timer = new javax.swing.Timer(timerSpeed, this);
    //timer.start();
  }

  public void reset() {
    currentTime = 3;
    timeText.setText(Integer.toString(currentTime));
  }

  public boolean update() {
    boolean toSwitch = false;

    timeText.setText(Integer.toString(currentTime));
    currentTime--;
    if (currentTime == 0) {
      currentTime = 3;
      toSwitch = true;
    }

    return toSwitch;
  }

  public int getCurrentTime() {
    return currentTime;
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();

    /*if (source == timer) {
      timeText.setText(Integer.toString(currentTime));
      currentTime--;
      if (currentTime == 0) currentTime = 3;
    }*/
  }
}
