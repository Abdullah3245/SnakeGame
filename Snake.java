/**
 * Name: Muhammad Abdullah Goher
 * Pennkey: mgoher
 * Execution: java SnakeGame
 *
 * Description: Draws the snake and updates the xPos and yPos of the snake.
 **/
public class Snake {
    private double cellWidth, cellHeight;
    private int xPos, yPos;
    private int xVel, yVel;

    public Snake(int headRow, int headCol, double cellWidth, double cellHeight) {
        this.xPos = headRow;
        this.yPos = headCol;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

    /**
     * Inputs: xVel and Vel
     * Outputs: none
     * Description: Draws the segment of the snake according to x and y position
     */
    public void draw() {
        PennDraw.filledRectangle((xPos * cellWidth * 2) +
                62.5, (yPos * cellHeight * 2) + 62.5,
                cellWidth, cellHeight);
    }

    /**
     * Inputs: xVel and Vel
     * Outputs: none
     * Description: updates the x and y position of the snake
     */
    public void update(int xVelocity, int yVelocity) {
        xVel = xVelocity;
        yVel = yVelocity;
        xPos += xVel;
        yPos += yVel;
        PennDraw.setPenColor(PennDraw.GREEN);
        draw();
    }

    /**
     * Inputs: none
     * Outputs: xPos
     * Description: returns the x Position
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * Inputs: none
     * Outputs: yPos
     * Description: returns the y Position
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Inputs: x position
     * Outputs: none
     * Description: Updates the x position of the snake according to input value
     */
    public void setXPos(int x) {
        this.xPos = x;
    }

    /**
     * Inputs: y position
     * Outputs: none
     * Description: Updates the y position of the snake according to input value
     */
    public void setYPos(int y) {
        this.yPos = y;
    }

}
