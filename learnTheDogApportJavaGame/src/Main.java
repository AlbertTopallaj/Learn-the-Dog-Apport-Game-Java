
public class Main {
    public static void main(String[] args) {

    Game game = new Game();

    while(game.runGame() != 0)
        {
            game.runGame();
        }

    System.out.println("game has exited.");
    }
}