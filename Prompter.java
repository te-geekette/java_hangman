import java.io.Console;

public class Prompter {
  private Game mGame;

  public void play(){
    while(mGame.getRemainingMisses() > 0 && !mGame.isSolved()){
      displayProgress();
      promptForGuess();
    }
    if(mGame.isSolved()){
      System.out.printf("Congratulations! You won with %d tries remaining. \n",
                        mGame.getRemainingMisses());
    } else {
      System.out.printf("Oh no! You lost! The correct answer was: %s\n",
                       mGame.getAnswer());
    }
  }

  public Prompter(Game game){
    mGame = game;
  }

  public boolean promptForGuess(){
    Console console = System.console();
    boolean isHit = false;
    boolean isValidGuess = false;

    while(! isValidGuess){
      String guessAsString = console.readLine("Enter a letter: ");
      try {
        isHit = mGame.applyGuess(guessAsString);
        isValidGuess = true;
      } catch (IllegalArgumentException iae){
        console.printf("%s Please try again.\n", iae.getMessage());
      }
    }
    return isHit;
  }

  public void displayProgress(){
    System.out.printf("You have %d tries left to solve: %s\n",
                      mGame.getRemainingMisses(),
                      mGame.getCurrentProgress());
  }
}
