
/**
 * Name: Muhammad Abdullah Goher
 * Pennkey: mgoher
 * Execution: java SnakeGame
 *
 * Description: Displays the starting screen, current score, high score
 * ,last 5 high scores ,and calls board class.
 **/
// import java.util.ArrayList;

public class SnakeGame {
    public static void main(String[] args) {
        if (args.length != 0) { // otherwise, print usage and exit
            System.out.println("Usage: java SnakeGame [width height]");
            return;
        }
        int width = 600;
        int height = 600;
        int fps = 5;
        boolean gameStarted = false;
        int maxScore = 0;

        PennDraw.setCanvasSize(width, height);
        // Set the scale in terms of width and height;
        // hopefully gives "rounder" numbers for sizes & speeds than
        // using the default scale of 0-1.
        PennDraw.setXscale(0, width);
        PennDraw.setYscale(0, height);

        Board board = new Board(width, height);

        PennDraw.clear(PennDraw.BOOK_LIGHT_BLUE);
        while (!gameStarted) {
            PennDraw.setFontBold();
            PennDraw.setFontSize(45);
            PennDraw.setPenColor(PennDraw.BLACK);
            // the displays the initial starting screen
            PennDraw.text(300, 500, "Snake Game!");
            PennDraw.setFontSize(30);
            PennDraw.setPenColor(PennDraw.GREEN);
            PennDraw.text(300, 350, "Press Space Bar to start");
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.setFontSize(15);
            PennDraw.text(300, 275, "To change" +
                    "to hard mode press X and for normal mode press Y");

            if (PennDraw.hasNextKeyTyped()) {
                char keyPressed = PennDraw.nextKeyTyped();
                if (keyPressed == 32) {
                    fps = 5; // frame rates for normal mode
                    gameStarted = true;
                }
            }
        }

        while (true) // loop till infinity
        {
            while (gameStarted) {
                /*
                 * changes the difficulty of the game according to input by
                 * increasing the frame
                 * rates. Pressing X makes it 2 times faster
                 */
                if (PennDraw.isKeyPressed('X') || PennDraw.isKeyPressed('x')) {
                    fps = 12;
                }
                if (PennDraw.isKeyPressed('y') || PennDraw.isKeyPressed('Y')) {
                    fps = 5;
                }

                PennDraw.enableAnimation(fps);
                PennDraw.clear(PennDraw.BOOK_LIGHT_BLUE);
                PennDraw.setFontSize(25);
                board.update();
                board.draw();
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(150, height - 25, "High Score: " + maxScore);
                PennDraw.advance();

                // checks if game has ended or not
                if (board.gameOver()) {
                    gameStarted = false;

                    // check for new high score after game ends and
                    if (board.getScore() > maxScore) {
                        maxScore = board.getScore();
                    }
                }
            }
            PennDraw.clear(PennDraw.MAGENTA);

            // this loop displays the screen after game has ended
            // and restarts if correct key pressed
            while (!gameStarted) {
                PennDraw.disableAnimation();
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.setFontSize(30);
                PennDraw.text(width / 2, 400, "Game Over!" + "\n" +
                        "Score " + board.getScore());
                PennDraw.setPenColor(PennDraw.YELLOW);
                PennDraw.text(width / 2, 300, "Press space bar to restart");
                if (PennDraw.isKeyPressed(32) || PennDraw.isKeyPressed('z')) {
                    gameStarted = true;
                    fps = 5;
                    board = new Board(width, height);
                }
            }

        }

    }

}
