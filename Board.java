import java.util.*;

public class Board {
  final int BOARD_SIZE = 3;
  private int[][] board;
  private int winner;

  private int player1Score = 0;
  private int player2Score = 0;

  boolean toPrintOnCommandLine = false;

  private void initBoard() {
    board = new int[BOARD_SIZE][BOARD_SIZE];
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        board[r][c] = 0;
      }
    }
  }

  public int getPlayer1Score() {
    return player1Score;
  }

  public int getPlayer2Score() {
    return player2Score;
  }

  public Board() {
    initBoard();
  }

  public void reset() {
    initBoard();
  }

  public int getWinner() {
    return winner;
  }

  public void assignOwner(int r, int c, int player) {
    board[r][c] = player;
    if (toPrintOnCommandLine) printBoard();
  }

  public boolean checkIfOwned(int r, int c) {
    if (board[r][c] == 0) return false;
    else return true;
  }

  public void updateScoreForPlayer(int player) {
    switch (player) {
      case 1:
        player1Score++;
        break;
      case 2:
        player2Score++;
        break;
    }
  }

  public boolean checkForWin() {

    // Checking horizontal matches
    for (int r = 0; r < board.length; r++) {
      int valueToCheck = board[r][0];
      if (valueToCheck != 0) {
        for (int c = 1; c < board.length; c++) {
          if (board[r][c] != valueToCheck) {
            break;
          } else if (c == 2) {
            winner = valueToCheck;
            updateScoreForPlayer(winner);
            return true;
          }
        }
      }
    }

    // Checking vertically
    for (int c = 0; c < board.length; c++) {
      int valueToCheck = board[0][c];
      if (valueToCheck != 0) {
        for (int r = 1; r < board.length; r++) {
          if (board[r][c] != valueToCheck) break;
          else if (r == 2) {
            winner = valueToCheck;
            updateScoreForPlayer(winner);
            return true;
          }
        }
      }
    }

    // Check diagonal (left to right)
    if (board[0][0] != 0) {
      for (int i = 1; i < board.length; i++) {
        if (board[i][i] != board[0][0]) break;
        else if (i == 2) {
          winner = board[0][0];
          updateScoreForPlayer(winner);
          return true;
        }
      }
    }

    // Checking diagonal (right to left)
    if (board[0][2] != 0) {
      for (int r = 0; r < board.length; r++) {
        int c = (board.length-1) - r;
        if (board[r][c] != board[0][2]) break;
        else if (r == 2) {
          winner = board[0][2];
          updateScoreForPlayer(winner);
          return true;
        }
      }
    }

    return false;
  }

  public void visualizeBoardOnCommandLine() {
    toPrintOnCommandLine = true;
  }

  public void printBoard() {
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        System.out.printf("%5d", board[r][c]);
      }
      System.out.println();
    }
    System.out.println();
  }
}
