package com.company;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

    public static final int SIZE = 20;

    private int x, y;
    private int xDirection, yDirection;
    private int speed = 4;

    public Ball() {
        reset();
    }

    private void reset() {
        x = Game.WIDTH / 2 - SIZE / 2;
        y = Game.HEIGHT / 2 - SIZE / 2;
        xDirection = Game.sign(Math.random() * 2.0 - 1);
        yDirection = Game.sign(Math.random() * 2.0 - 1);
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, SIZE, SIZE);
    }

    public void update(Paddle leftPaddle, Paddle rightPaddle) {
        x += xDirection * speed;
        y += yDirection * speed;

        if (y + SIZE >= Game.HEIGHT || y <= 0)
            changeYDir();

        if (x + SIZE >= Game.WIDTH) {
            leftPaddle.addPoint();
            reset();
        }
        if (x <= 0) {
            rightPaddle.addPoint();
            reset();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void changeXDir() {
        xDirection *= -1;
    }

    public void changeYDir() {
        yDirection *= -1;
    }
}