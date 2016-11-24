import java.util.*;

public class Board {
  final int BOARD_SIZE = 3; // Size of the board, probably shouldn't change this
  private int[][] board; // actual board
  private int winner; // The winner variable

  // initializing the scores
  private int player1Score = 0;
  private int player2Score = 0;

  boolean toPrintOnCommandLine = false; // initializing boolean variable to print grid on command line

  private void initBoard() { // This method initializes the board and sets all values to 0
    board = new int[BOARD_SIZE][BOARD_SIZE];
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        board[r][c] = 0;
      }
    }
  }

  public int getPlayer1Score() { // Getter method for player 1 score
    return player1Score;
  }

  public int getPlayer2Score() { // Getter method for player 2 score
    return player2Score;
  }

  public Board() { // Constructor
    initBoard();
  }

  public void reset() { // Reset method
    initBoard();
  }

  public int getWinner() { // Getter method for winner
    return winner;
  }

  public void assignOwner(int r, int c, int player) { // This method assigns a player to a cell
    board[r][c] = player;
    if (toPrintOnCommandLine) printBoard();
  }

  public boolean checkIfOwned(int r, int c) { // This method checks if a cell is occupied
    if (board[r][c] == 0) return false;
    else return true;
  }

  public void updateScoreForPlayer(int player) { // This method updates the score
    switch (player) {
      case 1:
        player1Score++;
        break;
      case 2:
        player2Score++;
        break;
    }
  }

  public boolean checkForWin() { // This method checks if there is a winner

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
    toPrintOnCommandLine = true; // set value to true if method is called
  }

  public void printBoard() { // This method prints the board on the command line
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        System.out.printf("%5d", board[r][c]);
      }
      System.out.println();
    }
    System.out.println();
  }
}
