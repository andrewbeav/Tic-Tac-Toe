import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame implements ActionListener {

  ArrayList<JButton> gridButtons = new ArrayList<>(9);
  JButton resetButton;
  JLabel winnerLabel;

  Color originalColor;

  final ImageIcon xIcon = new ImageIcon(getClass().getClassLoader().getResource("x.png"));
  final ImageIcon oIcon = new ImageIcon(getClass().getClassLoader().getResource("o.png"));

  Board gameBoard = new Board();
  int currentPlayer = 1;
  int initialPlayer = 1;
  boolean isGamePlaying = true;

  public void makeButtonGrid() {
    for (int i = 0; i < 9; i++) {
      gridButtons.add(new JButton());
    }
  }

  public Gui() {
    super("Tic Tac Toe");
    setSize(531, 616);

    Container contentPane = this.getContentPane();
    contentPane.setLayout(new BorderLayout());

    JPanel bottomArea = new JPanel();
    bottomArea.setLayout(new FlowLayout());
    contentPane.add(bottomArea, BorderLayout.SOUTH);

    JPanel topArea = new JPanel();
    topArea.setLayout(new FlowLayout());
    contentPane.add(topArea, BorderLayout.NORTH);

    topArea.setBackground(new Color(83, 85, 89));
    bottomArea.setBackground(new Color(83, 85, 89));

    resetButton = new JButton("Reset Game");
    topArea.add(resetButton);
    resetButton.setBackground(new Color(100, 110, 127));
    resetButton.addActionListener(this);

    originalColor = resetButton.getBackground();

    winnerLabel = new JLabel();
    winnerLabel.setText("It's player " + currentPlayer + "'s turn!");
    winnerLabel.setHorizontalAlignment(JLabel.CENTER);
    winnerLabel.setFont(new Font(winnerLabel.getFont().getName(), Font.PLAIN, 25));
    bottomArea.add(winnerLabel, BorderLayout.SOUTH);

    JPanel buttonGrid = new JPanel();
    buttonGrid.setLayout(new GridLayout(3, 3));
    contentPane.add(buttonGrid, BorderLayout.CENTER);

    makeButtonGrid();
    for (JButton button : gridButtons) {
      buttonGrid.add(button);
      button.setBackground(new Color(100, 110, 127));
      button.addActionListener(this);
    }
  }

  public void resetGui() {
    for (JButton button : gridButtons) {
      //button.setBackground(originalColor);
      button.setIcon(null);
    }

    isGamePlaying = true;
    if (initialPlayer == 2) {
      initialPlayer = 1;
      currentPlayer = initialPlayer;
    }
    else if (initialPlayer == 1) {
      initialPlayer = 2;
      currentPlayer = initialPlayer;
    }

    winnerLabel.setText("It's player " + currentPlayer + "'s turn!");
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();
    JButton button = (JButton) source;

    if (source == resetButton) {
      resetGui();
      gameBoard.reset();
    }
    else if (isGamePlaying && !gameBoard.checkIfOwned(findRowOfButton(button), findColumnOfButton(button))) {
      if (currentPlayer == 1) {
        button.setIcon(xIcon);
        //button.setBackground(Color.RED);
        gameBoard.assignOwner(findRowOfButton(button), findColumnOfButton(button), currentPlayer);
        currentPlayer = 2;
      } else if (currentPlayer == 2) {
        button.setIcon(oIcon);
        //button.setBackground(Color.BLUE);
        gameBoard.assignOwner(findRowOfButton(button), findColumnOfButton(button), currentPlayer);
        currentPlayer = 1;
      }

      if (gameBoard.checkForWin()) {
        winnerLabel.setText("Player " + gameBoard.getWinner() + " won the Game!");
        isGamePlaying = false;
      } else winnerLabel.setText("It's player " + currentPlayer + "'s turn!");
    }
  }

  public int findRowOfButton(JButton button) {
    int row = 0;
    int btnNum = findIndexOfButton(button);

    if (btnNum < 3) {
      row = 0;
    } else if (btnNum < 6) {
      row = 1;
    } else if (btnNum < 9) {
      row = 2;
    }

    return row;
  }

  public int findColumnOfButton(JButton button) {
    int column = 0;
    int btnNum = findIndexOfButton(button);

    if (btnNum % 3 == 0) {
      column = 0;
    } else if (btnNum % 3 == 1) {
      column = 1;
    } else if (btnNum % 3 == 2) {
      column = 2;
    }

    return column;
  }

  public int findIndexOfButton(JButton button) {
    int btnNum = 0;

    for (int i = 0; i < gridButtons.size(); i++) {
       if (gridButtons.get(i) == button) {
          btnNum = i;
          break;
       }
    }

    return btnNum;
  }

  public void visualizeBoardOnCommandLine() {
    gameBoard.visualizeBoardOnCommandLine();
  }
}
