import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends JFrame implements ActionListener {

  final String PLAYER_1="Player1", PLAYER_2="Player2"; // constants

  JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
  public boolean btn1Pressed, btn2Pressed, btn3Pressed, btn4Pressed, btn5Pressed, btn6Pressed, btn7Pressed, btn8Pressed, btn9Pressed;
  boolean isGamePlaying = true;
  String winner;

  Color defaultBtnColor;

  String turn = "Player1";

  String[][] board = new String[3][3];

  JButton resetBtn;
  JLabel resultsText;

  public Gui() {
    super("Tic Tac Toe");
    setSize(531, 616);

    initBoard(); // initializing the board

    Container contentPane = this.getContentPane();
    contentPane.setLayout(new GridLayout(4,3));

    btn1 = new JButton();
    btn1.setSize(100, 100);
    contentPane.add(btn1);
    btn1.addActionListener(this);

    btn2 = new JButton();
    btn2.setSize(100, 100);
    contentPane.add(btn2);
    btn2.addActionListener(this);

    btn3 = new JButton();
    btn3.setSize(100, 100);
    contentPane.add(btn3);
    btn3.addActionListener(this);

    btn4 = new JButton();
    btn4.setSize(100, 100);
    contentPane.add(btn4);
    btn4.addActionListener(this);

    btn5 = new JButton();
    btn5.setSize(100, 100);
    contentPane.add(btn5);
    btn5.addActionListener(this);

    btn6 = new JButton();
    btn6.setSize(100, 100);
    contentPane.add(btn6);
    btn6.addActionListener(this);

    btn7 = new JButton();
    btn7.setSize(100, 100);
    contentPane.add(btn7);
    btn7.addActionListener(this);

    btn8 = new JButton();
    btn8.setSize(100, 100);
    contentPane.add(btn8);
    btn8.addActionListener(this);

    btn9 = new JButton();
    btn9.setSize(100, 100);
    contentPane.add(btn9);
    btn9.addActionListener(this);

    resetBtn = new JButton("Reset Game");
    contentPane.add(resetBtn);
    resetBtn.addActionListener(this);

    resultsText = new JLabel();
    contentPane.add(resultsText);

    defaultBtnColor = btn1.getBackground();
  }

  public void initBoard() {
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        board[r][c] = "Empty";
      }
    }
  }

  public void putValueInBoard(JButton button, String player) {
    if (button == btn1) {
      board[0][0] = player;
    } else if (button == btn2) {
      board[0][1] = player;
    } else if (button == btn3) {
      board[0][2] = player;
    } else if (button == btn4) {
      board[1][0] = player;
    } else if (button == btn5) {
      board[1][1] = player;
    } else if (button == btn6) {
      board[1][2] = player;
    } else if (button == btn7) {
      board[2][0] = player;
    } else if (button == btn8) {
      board[2][1] = player;
    } else if (button == btn9) {
      board[2][2] = player;
    }
  }

  public JButton getCorrespondingButton(int r, int c) {
    int row = 0;
    JButton button = btn1;
    switch (r) {
      case 0:
        row = 0;
        break;
      case 1:
        row = 1;
        break;
      case 2:
        row = 2;
        break;
    }

    switch (c) {
      case 0:
        if (row == 0) {
          button = btn1;
        } else if (row == 1) {
          button = btn2;
        } else if (row == 2) {
          button = btn3;
        }
        break;
      case 1:
        if (row == 0) {
          button = btn4;
        } else if (row == 1) {
          button = btn5;
        } else if (row == 2) {
          button = btn6;
        }
        break;
      case 2:
        if (row == 0) {
          button = btn7;
        } else if (row == 1) {
          button = btn8;
        } else if (row == 2) {
          button = btn9;
        }
        break;
    }
    return button;
  }

  public void aiMakeMove() {
    boolean shouldBreak = false;

    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        if (board[r][c].equals("Empty")) {
          board[r][c].equals(PLAYER_2);
          getCorrespondingButton(r, c).setIcon(new ImageIcon(getClass().getClassLoader().getResource("o.png")));
          shouldBreak = true;
          break;
        }
        if (shouldBreak) break;
      }
    }
    turn = PLAYER_1;
  }

  public void resetGame() {
    btn1.setIcon(null);
    btn2.setIcon(null);
    btn3.setIcon(null);
    btn4.setIcon(null);
    btn5.setIcon(null);
    btn6.setIcon(null);
    btn7.setIcon(null);
    btn8.setIcon(null);
    btn9.setIcon(null);

    btn1Pressed = false;
    btn2Pressed = false;
    btn3Pressed = false;
    btn4Pressed = false;
    btn5Pressed = false;
    btn6Pressed = false;
    btn7Pressed = false;
    btn8Pressed = false;
    btn9Pressed = false;

    isGamePlaying = true;

    resultsText.setText("");

    initBoard();
  }

  public void actionPerformed(ActionEvent event) {

    Object source = event.getSource();

    if (source == resetBtn) {
      resetGame();
    }

    if (isGamePlaying) {
      if (!btn1Pressed && source == btn1) {
        btn1Pressed = true;
        putValueInBoard(btn1, turn);
        switch(turn) {
          case PLAYER_1:
            btn1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("x.png")));
            turn = PLAYER_2;
            break;
          case PLAYER_2:
            btn1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("o.png")));
            turn = PLAYER_1;
            break;
        }
      } else if (!btn2Pressed && source == btn2) {
        btn2Pressed = true;
        putValueInBoard(btn2, turn);
        switch(turn) {
          case PLAYER_1:
            btn2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("x.png")));
            turn = PLAYER_2;
            break;
          case PLAYER_2:
            btn2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("o.png")));
            turn = PLAYER_1;
            break;
        }
      } else if (!btn3Pressed && source == btn3) {
        btn3Pressed = true;
        putValueInBoard(btn3, turn);
        switch(turn) {
          case PLAYER_1:
            btn3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("x.png")));
            turn = PLAYER_2;
            break;
          case PLAYER_2:
            btn3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("o.png")));
            turn = PLAYER_1;
            break;
        }
      } else if (!btn4Pressed && source == btn4) {
        btn4Pressed = true;
        putValueInBoard(btn4, turn);
        switch(turn) {
          case PLAYER_1:
            btn4.setIcon(new ImageIcon(getClass().getClassLoader().getResource("x.png")));
            turn = PLAYER_2;
            break;
          case PLAYER_2:
            btn4.setIcon(new ImageIcon(getClass().getClassLoader().getResource("o.png")));
            turn = PLAYER_1;
            break;
        }
      } else if (!btn5Pressed && source == btn5) {
        btn5Pressed = true;
        putValueInBoard(btn5, turn);
        switch(turn) {
          case PLAYER_1:
            btn5.setIcon(new ImageIcon(getClass().getClassLoader().getResource("x.png")));
            turn = PLAYER_2;
            break;
          case PLAYER_2:
            btn5.setIcon(new ImageIcon(getClass().getClassLoader().getResource("o.png")));
            turn = PLAYER_1;
            break;
        }
      } else if (!btn6Pressed && source == btn6) {
        btn6Pressed = true;
        putValueInBoard(btn6, turn);
        switch(turn) {
          case PLAYER_1:
            btn6.setIcon(new ImageIcon(getClass().getClassLoader().getResource("x.png")));
            turn = PLAYER_2;
            break;
          case PLAYER_2:
            btn6.setIcon(new ImageIcon(getClass().getClassLoader().getResource("o.png")));
            turn = PLAYER_1;
            break;
        }
      } else if (!btn7Pressed && source == btn7) {
        btn7Pressed = true;
        putValueInBoard(btn7, turn);
        switch(turn) {
          case PLAYER_1:
            btn7.setIcon(new ImageIcon(getClass().getClassLoader().getResource("x.png")));
            turn = PLAYER_2;
            break;
          case PLAYER_2:
            btn7.setIcon(new ImageIcon(getClass().getClassLoader().getResource("o.png")));
            turn = PLAYER_1;
            break;
        }
      } else if (!btn8Pressed && source == btn8) {
        btn8Pressed = true;
        putValueInBoard(btn8, turn);
        switch(turn) {
          case PLAYER_1:
            btn8.setIcon(new ImageIcon(getClass().getClassLoader().getResource("x.png")));
            turn = PLAYER_2;
            break;
          case PLAYER_2:
            btn8.setIcon(new ImageIcon(getClass().getClassLoader().getResource("o.png")));
            turn = PLAYER_1;
            break;
        }
      } else if (!btn9Pressed && source == btn9) {
        btn9Pressed = true;
        putValueInBoard(btn9, turn);
        switch(turn) {
          case PLAYER_1:
            btn9.setIcon(new ImageIcon(getClass().getClassLoader().getResource("x.png")));
            turn = PLAYER_2;
            break;
          case PLAYER_2:
            btn9.setIcon(new ImageIcon(getClass().getClassLoader().getResource("o.png")));
            turn = PLAYER_1;
            break;
        }
      }

      if (source != resetBtn) aiMakeMove();

      for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[r].length; c++) {
          System.out.printf("%-10s", board[r][c]);
        }
        System.out.println("\n");
      }
      System.out.println("------------------------------");

      if (checkForWin() == true) {
        isGamePlaying = false;
        //System.out.println(winner + " has won the game!");
        resultsText.setText(winner + " wins!");
      }
    }
    System.out.println(getWidth() + " " + getHeight());
  }

  public boolean isCellEmpty(int r, int c) {
    if (board[r][c].equals("Empty")) return true;
    else return false;
  }

  public boolean checkForWin() {
    boolean isTrue = false;

    // Check
    if ((!isCellEmpty(0,0))&&((board[0][0].equals(board[0][1]) &&
         board[0][0].equals(board[0][2])) ||
        (board[0][0].equals(board[1][0]) &&
         board[0][0].equals(board[2][0])) ||
        (board[0][0].equals(board[1][1]) &&
         board[0][0].equals(board[2][2])))) {
           isTrue = true;
           System.out.println("check");
           winner = board[0][0];
         }


    else if ((!isCellEmpty(0,1))&&((board[0][1].equals(board[0][0]) &&
              board[0][1].equals(board[0][2])) ||
             (board[0][1].equals(board[1][1]) &&
              board[0][1].equals(board[2][1])))) {
                isTrue = true;
                System.out.println("check");
                winner = board[0][1];
              }

    else if ((!isCellEmpty(0,2))&&((board[0][2].equals(board[0][0]) &&
              board[0][2].equals(board[0][1])) ||
             (board[0][2].equals(board[1][2]) &&
              board[0][2].equals(board[2][2])) ||
             (board[0][2].equals(board[1][1]) &&
              board[0][2].equals(board[2][0])))) {
                isTrue = true;
                winner = board[0][2];
              }

    else if ((!isCellEmpty(1,0))&&((board[1][0].equals(board[0][0]) &&
              board[1][0].equals(board[2][0])) ||
             (board[1][0].equals(board[1][1]) &&
              board[1][0].equals(board[1][2])))) {
                isTrue = true;
                winner = board[1][0];
              }

    else if ((!isCellEmpty(1,1))&&((board[1][1].equals(board[0][1]) &&
              board[1][1].equals(board[2][1])) ||
             (board[1][1].equals(board[1][0]) &&
              board[1][1].equals(board[1][2])) ||
             (board[1][1].equals(board[0][0]) &&
              board[1][1].equals(board[2][2])) ||
             (board[1][1].equals(board[0][2]) &&
              board[1][1].equals(board[2][0])))) {
                isTrue = true;
                winner = board[1][1];
              }

    else if ((!isCellEmpty(1,2))&&((board[1][2].equals(board[1][0]) &&
              board[1][2].equals(board[1][1])) ||
             (board[1][2].equals(board[0][2]) &&
              board[1][2].equals(board[2][2])))) {
                isTrue = true;
                winner = board[1][2];
              }

    else if ((!isCellEmpty(2,0))&&((board[2][0].equals(board[2][1]) &&
              board[2][0].equals(board[2][2])) ||
             (board[2][0].equals(board[1][0]) &&
              board[2][0].equals(board[0][0])) ||
             (board[2][0].equals(board[1][1]) &&
              board[2][0].equals(board[0][2])))) {
                isTrue = true;
                winner = board[2][0];
              }

    else if ((!isCellEmpty(2,1))&&((board[2][1].equals(board[2][0]) &&
              board[2][1].equals(board[2][2])) ||
             (board[2][1].equals(board[1][1]) &&
              board[2][1].equals(board[0][1])))) {
                isTrue = true;
                winner = board[2][1];
              }

    else if ((!isCellEmpty(2,2))&&((board[2][2].equals(board[2][0]) &&
              board[2][2].equals(board[2][1])) ||
             (board[2][2].equals(board[0][2]) &&
              board[2][2].equals(board[0][1])) ||
             (board[2][2].equals(board[1][1]) &&
              board[2][2].equals(board[0][0])))) {
                isTrue = true;
                winner = board[2][2];
              }

    return isTrue;
  }
}
