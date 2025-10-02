
public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        while (game.isGameRunning() != false) {
            game.runGame();
        }

        System.out.println("game has exited.");
    }
}