package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Paddle {

    private int x, y;
    private int direction = 0;
    private int speed = 10;
    private int width = 20, height = 85;
    private int score = 0;
    private boolean left;

    public Paddle(boolean left) {
        this.left = left;

        if (left)
            x = 0;
        else
            x = Game.WIDTH - width;

        y = Game.HEIGHT / 2 - height / 2;
    }

    public void addPoint() {
        score++;
        if (score >= 5)
            Game.stop();
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);

        int scoreOnScreen, space = 25;
        String scoreText = Integer.toString(score);

        if (left) {
            scoreOnScreen = Game.WIDTH / 2 - space;
        } else {
            scoreOnScreen = Game.WIDTH / 2 + space;
        }

        g.setFont(new Font("Roboto", Font.BOLD, 50));
        g.drawString(scoreText, scoreOnScreen, 50);
    }

    public void update(Ball b) {
        y = Game.ensureRange(y + direction, 0, Game.HEIGHT - height);

        int ballX = b.getX();
        int ballY = b.getY();

        if (left) {
            if (ballX <= x + width && ballY + Ball.SIZE >= y && ballY <= y + height)
                b.changeXDir();
        } else {
            if (ballX + Ball.SIZE >= x && ballY + Ball.SIZE >= y && ballY <= y + height)
                b.changeXDir();
        }
    }

    public void switchDirections(int chosenDirection) { //-1 up 1 down
        direction = speed * chosenDirection;
    }

    public void stop() {
        direction = 0;
    }
}