
/**
 * Name: Muhammad Abdullah Goher
 * Pennkey: mgoher
 * Execution: java SnakeGame
 *
 * Description: Draws the main grid. Handles drawing of food, 
 * snake movement, collision with wall, handles when game ends. 
 **/

import java.util.ArrayList;

public class Board {
    private int numRows, numCols;
    private double width, height;
    private double cellWidth, cellHeight;
    private Snake snakeHead;
    private int score;
    private int xCord;
    private int yCord;
    private double radius;
    private int snakeXCord, snakeYCord;
    private ArrayList<Snake> snakeList;
    private int xVel, yVel;

    public Board(double width, double height) {
        this.numRows = 20;
        this.numCols = 20;
        this.width = width;
        this.height = height;
        cellHeight = 12.5;
        cellWidth = 12.5;
        snakeXCord = numCols / 2;
        snakeYCord = numRows / 2;
        snakeHead = new Snake(snakeXCord, snakeYCord, cellWidth, cellHeight);
        PennDraw.setPenColor(PennDraw.WHITE);
        score = 0;
        xCord = (int) (Math.random() * numCols);
        yCord = (int) (Math.random() * numRows);
        radius = 12.5;
        snakeList = new ArrayList<Snake>();
        snakeList.add(snakeHead);
        yVel = -1;
        xVel = 0;
    }

    /*
     * Description: Draws the board of dimensions 250 by 250 and displaying the
     * score on top of the canvas.
     */
    public void draw() {
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setPenRadius(0.010);
        PennDraw.rectangle(300, 300, 250, 250);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(width - 150, height - 25, "Current Score: " + score);
        drawFood();
    }

    public void update() {
        // checks if game has ended or not
        if (!gameOver()) {
            if (PennDraw.hasNextKeyTyped()) {
                changeDirection();
            }
            PennDraw.setPenColor(PennDraw.GREEN);
            // detecting collision between snake head and food
            if (snakeList.get(0).getXPos() == xCord &&
                    snakeList.get(0).getYPos() == yCord) {
                xCord = (int) (Math.random() * numCols);
                yCord = (int) (Math.random() * numRows);
                drawFood();
                score++;
                ateFood();
            }
            /*
             * a loop which is used for snake movement. It gives
             * the position of the head to
             * the one body segment and the position of
             * first body segment to that of
             * previous and so on. Through the snake body
             * follows the snake head and it
             * moves in a zig zag as per requirement
             */
            int preHeadX = snakeList.get(0).getXPos();
            int preHeadY = snakeList.get(0).getYPos();
            for (int i = 1; i < snakeList.size(); i++) {
                PennDraw.setPenColor(PennDraw.RED);
                int holderX = snakeList.get(i).getXPos();
                int holderY = snakeList.get(i).getYPos();
                snakeList.get(i).setXPos(preHeadX);
                snakeList.get(i).setYPos(preHeadY);
                snakeList.get(i).draw();
                preHeadX = holderX;
                preHeadY = holderY;
            }

            // sets the velocity of the head according to the key pressed
            snakeList.get(0).update(xVel, yVel);
        } else {
            PennDraw.clear();
            gameOver();
        }

    }

    /**
     * Inputs: none
     * Outputs: none
     * Description: Draws the food using randomly generated
     * xCords and yCords.
     * Checks if the xCord and yCord dont
     * overlap with the snake body. Re-generates
     * the cords if that is true
     */

    public void drawFood() {
        for (int i = 1; i < snakeList.size(); i++) {
            if (xCord == snakeList.get(i).getXPos() &&
                    yCord == snakeList.get(i).getYPos()) {
                // generating the xCord according to size of grid
                xCord = (int) (Math.random() * numCols);
                // generating the yCord according to size of grid
                yCord = (int) (Math.random() * numRows);
            }
        }
        double centerX = (xCord * cellWidth * 2) + 62.5;
        double centerY = (yCord * cellWidth * 2) + 62.5;
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledPie(centerX, centerY, radius, 0, 300);
    }

    /**
     * Inputs: none
     * Outputs: none
     * Description: Called when the snake head collides with the food. Moves the
     * snake one step forward according to the current velocity and then adds a body
     * segment at the end of the snake body
     */
    public void ateFood() {
        Snake lastSnake = snakeList.get(snakeList.size() - 1);
        Snake newTail = new Snake(lastSnake.getXPos(),
                lastSnake.getYPos(), cellWidth, cellHeight);

        int preHeadX = snakeList.get(0).getXPos();
        int preHeadY = snakeList.get(0).getYPos();

        snakeList.get(0).update(xVel, yVel);

        for (int i = 1; i < snakeList.size(); i++) {
            PennDraw.setPenColor(PennDraw.RED); // body drawn as red
            int holderX = snakeList.get(i).getXPos();
            int holderY = snakeList.get(i).getYPos();
            snakeList.get(i).setXPos(preHeadX);
            snakeList.get(i).setYPos(preHeadY);
            snakeList.get(i).draw();
            preHeadX = holderX;
            preHeadY = holderY;
        }
        snakeList.add(snakeList.size() - 1, newTail);
    }

    /**
     * Inputs: x position
     * Outputs: boolean indicating if game ended
     * Description: Detects if snake has collided
     * with the wall or calls the
     * snakeCollision function to check if snakes collides with
     * itsels and return true if one of the conditions is met
     * and the game is ended
     */
    public boolean gameOver() {
        if (snakeList.get(0).getXPos() > numCols - 1 ||
                snakeList.get(0).getYPos() > numRows - 1 ||
                snakeList.get(0).getXPos() < 0 ||
                snakeList.get(0).getYPos() < 0) {
            return true;
        } else if (snakeCollision()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Inputs: none
     * Outputs: none
     * Description: Sets the xVelocity and yVelocity of the snake according to the
     * key pressed
     */
    public void changeDirection() {
        if (PennDraw.hasNextKeyTyped()) {
            char input = PennDraw.nextKeyTyped();
            if (input == 'A' || input == 'a') {
                if (xVel == 0) {
                    xVel = -1;
                    yVel = 0;
                }
            }

            if (input == 'D' || input == 'd') {
                if (xVel == 0) {
                    xVel = 1;
                    yVel = 0;
                }
            }

            if (input == 'W' || input == 'w') {
                if (yVel == 0) {
                    xVel = 0;
                    yVel = 1;

                }
            }

            if (input == 'S' || input == 's') {
                if (yVel == 0) {
                    xVel = 0;
                    yVel = -1;
                }
            }
        }

    }

    /**
     * Inputs: none
     * Outputs: integer which is the score
     * Description: Returns the score of the game ongoing
     */
    public int getScore() {
        return score;
    }

    /**
     * Inputs: none
     * Outputs: boolean indicating snake collision
     * Description: Detects snake collision between the head of the snake and body.
     */

    public boolean snakeCollision() {
        for (int i = 1; i < snakeList.size(); i++) {
            if (snakeList.get(0).getXPos() == snakeList.get(i).getXPos() &&
                    snakeList.get(0).getYPos() == snakeList.get(i).getYPos()) {
                return true;
            }
        }
        return false;
    }

}
