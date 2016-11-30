import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameGui extends JFrame implements ActionListener {

  ArrayList<JButton> gridButtons = new ArrayList<>(9); // List of buttons
  JButton resetButton; // button to reset game
  JLabel winnerLabel, player1ScoreLabel, player2ScoreLabel, timerLabel; // Labels

  JMenuBar menuBar;
  JMenu settingsMenu, imagesMenu, modeMenu;
  JMenuItem androidVsAppleMenuItem, xVsOMenuItem, ticTacGoModeItem, regularModeItem;

  JMenu timerSettingsMenu;
  JMenuItem slowItem, mediumItem, fastItem;

  javax.swing.Timer timer; // Timer

  int initialTime = 3;
  int currentTime = initialTime;

  boolean isTimedMode = false;

  // x and o images
  ImageIcon player1Icon, player2Icon;

  final ImageIcon androidIcon = new ImageIcon(getClass().getClassLoader().getResource("android.png"));
  final ImageIcon appleIcon = new ImageIcon(getClass().getClassLoader().getResource("apple.png"));

  final ImageIcon xIcon = new ImageIcon(getClass().getClassLoader().getResource("x.png"));
  final ImageIcon oIcon = new ImageIcon(getClass().getClassLoader().getResource("o.png"));

  Board gameBoard = new Board(); // Creating new board object
  int currentPlayer = 1; // setting current player
  int initialPlayer = 1; // setting the initialPlayer (beginning of game)
  boolean isGamePlaying = true; // setting boolean to see if the game is playing

  public void makeButtonGrid() { // Creating buttons and adding them to the list
    for (int i = 0; i < 9; i++) {
      gridButtons.add(new JButton());
    }
  }

  public GameGui() { // Constructor
    super("Tic Tac Toe"); // Setting title
    setSize(531, 616); // Setting size

    player1Icon = androidIcon;
    player2Icon = appleIcon;

    setIconImage(androidIcon.getImage()); // setting icon for entire program

    menuBar = new JMenuBar();
    menuBar.setBackground(new Color(83, 85, 89));

    settingsMenu = new JMenu("Settings");
    menuBar.add(settingsMenu);

    imagesMenu = new JMenu("Change Images");
    settingsMenu.add(imagesMenu);

    androidVsAppleMenuItem = new JMenuItem("Android Vs. Apple");
    xVsOMenuItem = new JMenuItem("X Vs. O");
    imagesMenu.add(androidVsAppleMenuItem);
    imagesMenu.add(xVsOMenuItem);

    modeMenu = new JMenu("Change Mode");
    settingsMenu.add(modeMenu);

    regularModeItem = new JMenuItem("Regular Tic Tac Toe");
    modeMenu.add(regularModeItem);

    ticTacGoModeItem = new JMenuItem("Tic Tac GO!");
    modeMenu.add(ticTacGoModeItem);

    androidVsAppleMenuItem.addActionListener(this);
    xVsOMenuItem.addActionListener(this);
    regularModeItem.addActionListener(this);
    ticTacGoModeItem.addActionListener(this);

    setJMenuBar(menuBar);

    Container contentPane = this.getContentPane(); // making content pane for the entire frame
    contentPane.setLayout(new BorderLayout()); // setting it to border layout

    JPanel bottomArea = new JPanel(); // making the panel for the bottom
    bottomArea.setLayout(new FlowLayout()); // setting it to flow layout
    contentPane.add(bottomArea, BorderLayout.SOUTH); // adding the bottomArea to the bottom

    JPanel topArea = new JPanel(); // making panel for the top
    topArea.setLayout(new FlowLayout()); // setting it to flow layout
    contentPane.add(topArea, BorderLayout.NORTH); // adding the topArea to the top

    topArea.setBackground(new Color(83, 85, 89)); // setting the color of the background
    bottomArea.setBackground(new Color(83, 85, 89)); // Same thing as above

    player1ScoreLabel = new JLabel("Player 1: 0"); // creating the label for scores
    topArea.add(player1ScoreLabel); // adding it to the topArea

    resetButton = new JButton("Reset Game"); // creating the resetButton
    topArea.add(resetButton); // adding it to the topArea
    resetButton.setBackground(new Color(100, 110, 127)); // setting the background of the reset button
    resetButton.addActionListener(this); // adding the event handler

    player2ScoreLabel = new JLabel("Player 2: 0"); // making label for scores
    topArea.add(player2ScoreLabel); // adding it to the topArea

  	timerLabel = new JLabel(); // making label for timer
  	topArea.add(timerLabel);

    winnerLabel = new JLabel(); // making new label for the winner
    winnerLabel.setText("It's player " + currentPlayer + "'s turn!"); // setting the text
    winnerLabel.setFont(new Font(winnerLabel.getFont().getName(), Font.PLAIN, 25)); // making it bigger font
    bottomArea.add(winnerLabel);// adding it to the bottomArea

    JPanel buttonGrid = new JPanel(); // making new panel for holding the grid of buttons
    buttonGrid.setLayout(new GridLayout(3, 3)); // making a grid layout for the buttons
    contentPane.add(buttonGrid, BorderLayout.CENTER); // adding it to the content pane in the center

  	timer = new javax.swing.Timer(1000, this);
  	timer.start();

    makeButtonGrid(); // making the grid of buttons
    for (JButton button : gridButtons) { // looping through the list of buttons
      buttonGrid.add(button); // adding the button to the grid of buttons
      button.setBackground(new Color(100, 110, 127)); // setting the background
      button.addActionListener(this); // adding the event handler
    }
  }

  public void resetGui() { // method to reset the gui
    for (JButton button : gridButtons) { // looping through the button
      button.setIcon(null);// setting the icon to null
    }

    isGamePlaying = true; // start playing the game
    if (initialPlayer == 2) {
      initialPlayer = 1;
      currentPlayer = initialPlayer;
    }
    else if (initialPlayer == 1) {
      initialPlayer = 2;
      currentPlayer = initialPlayer;
    }

    winnerLabel.setText("It's player " + currentPlayer + "'s turn!"); // resetting the text of the winnerLabel

    currentTime = initialTime;
    timer.start();
  }

  public void changeCurrentIcons() {
    for (JButton button : gridButtons) {
      if (button.getIcon() == xIcon || button.getIcon() == androidIcon) {
        button.setIcon(player1Icon);
      } else if (button.getIcon() == oIcon || button.getIcon() == appleIcon) {
        button.setIcon(player2Icon);
      } else button.setIcon(null);
    }
  }

  public void startTimedMode() {
    isTimedMode = true;
    timerSettingsMenu = new JMenu("Timer speed");
    menuBar.add(timerSettingsMenu);

    slowItem = new JMenuItem("Slow");
    mediumItem = new JMenuItem("Medium");
    fastItem = new JMenuItem("Fast");

    timerSettingsMenu.add(slowItem);
    timerSettingsMenu.add(mediumItem);
    timerSettingsMenu.add(fastItem);

    slowItem.addActionListener(this);
    mediumItem.addActionListener(this);
    fastItem.addActionListener(this);
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource(); // getting the source

    if (source == resetButton) {
        // reset everything
        resetGui();
        gameBoard.reset();
    } else if (source == androidVsAppleMenuItem) {
        player1Icon = androidIcon;
        player2Icon = appleIcon;
        changeCurrentIcons();
    } else if (source == xVsOMenuItem) {
        player1Icon = xIcon;
        player2Icon = oIcon;
        changeCurrentIcons();
    } else if (source == regularModeItem) {
        isTimedMode = false;
    } else if (source == ticTacGoModeItem) {
        startTimedMode();
    } else if (source == slowItem) {
        timer.setDelay(1000);
    } else if (source == mediumItem) {
        timer.setDelay(800);
    } else if (source == fastItem) {
        timer.setDelay(500);
    } else if (source == timer && isTimedMode) { // event handler for timer
    		timerLabel.setText("        Time: " + Integer.toString(currentTime));
    		currentTime--;
		if (currentTime == 0) {
			currentTime = initialTime;
			switchCurrentPlayer();
		}

	} else if (source != timer) {
      JButton button = (JButton) source; // casting it to a game button
      if (isGamePlaying && !gameBoard.checkIfOwned(findRowOfButton(button), findColumnOfButton(button))) {
        currentTime = initialTime;
        if (isTimedMode) timerLabel.setText("        Time: " + Integer.toString(currentTime));
        if (currentPlayer == 1) {
          button.setIcon(player1Icon); // setting the image to 'x'
          gameBoard.assignOwner(findRowOfButton(button), findColumnOfButton(button), currentPlayer); // assigning the owner
          currentPlayer = 2; // swapping the player
        } else if (currentPlayer == 2) {
          button.setIcon(player2Icon); // setting the image to 'o'
          gameBoard.assignOwner(findRowOfButton(button), findColumnOfButton(button), currentPlayer); // assigning the owner
          currentPlayer = 1; // swapping the player
        }

        if (gameBoard.checkForWin()) { // Checking if there is a winner
          winnerLabel.setText("Player " + gameBoard.getWinner() + " Won the Round!"); // setting the text

          player1ScoreLabel.setText("Player 1: " + gameBoard.getPlayer1Score()); // setting the text for the score
          player2ScoreLabel.setText("Player 2: " + gameBoard.getPlayer2Score()); // setting the text for the score

          isGamePlaying = false; // Make the game not playable
          timer.stop();
        } else winnerLabel.setText("It's player " + currentPlayer + "'s turn!"); // setting the text for the current players turn
      }
    }
  }


  //
  // These are 'helper' methods. Used for things above.
  //

  public void switchCurrentPlayer() {
  	if (currentPlayer == 1) {
  		currentPlayer = 2;
  	}
  	else currentPlayer = 1;

  	winnerLabel.setText("It's player " + currentPlayer + "'s turn!");
  }

  public int findRowOfButton(JButton button) { // This method finds the row for the button
    int row = 0; // initializing the row to 0
    int btnNum = findIndexOfButton(button); // getting the index of the button

    // Checking each case to see what row it's in
    if (btnNum < 3) {
      row = 0;
    } else if (btnNum < 6) {
      row = 1;
    } else if (btnNum < 9) {
      row = 2;
    }

    return row; // returning that row
  }

  public int findColumnOfButton(JButton button) { // This method finds the column of a button
    int column = 0; // initializing the column to 0
    int btnNum = findIndexOfButton(button); // finding the index of the button

    // Checking each case to see what column it is in.
    if (btnNum % 3 == 0) {
      column = 0;
    } else if (btnNum % 3 == 1) {
      column = 1;
    } else if (btnNum % 3 == 2) {
      column = 2;
    }

    return column; // returning the column
  }

  public int findIndexOfButton(JButton button) { // finding the index of a specified button in the list
    int btnNum = 0; // initializing the index variable

    // linear search through the list
    for (int i = 0; i < gridButtons.size(); i++) {
       if (gridButtons.get(i) == button) {
          btnNum = i;
          break;
       }
    }

    return btnNum; // return the index
  }

  // method to show the grid on the command line
  public void visualizeBoardOnCommandLine() {
    gameBoard.visualizeBoardOnCommandLine();
  }
}
