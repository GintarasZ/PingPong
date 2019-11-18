package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private boolean leftPaddleUp = false;
    private boolean leftPaddleDown = false;
    private boolean rightPaddleUp = false;
    private boolean rightPaddleDown = false;

    public KeyInput(Paddle paddleOne, Paddle paddleTwo) {
        leftPaddle = paddleOne;
        rightPaddle = paddleTwo;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            rightPaddle.switchDirections(-1);
            rightPaddleUp = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            rightPaddle.switchDirections(1);
            rightPaddleDown = true;
        }
        if (key == KeyEvent.VK_W) {
            leftPaddle.switchDirections(-1);
            leftPaddleUp = true;
        }
        if (key == KeyEvent.VK_S) {
            leftPaddle.switchDirections(1);
            leftPaddleDown = true;
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            rightPaddleUp = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            rightPaddleDown = false;
        }
        if (key == KeyEvent.VK_W) {
            leftPaddleUp = false;
        }
        if (key == KeyEvent.VK_S) {
            leftPaddleDown = false;
        }

        if (!leftPaddleUp && !leftPaddleDown)
            leftPaddle.stop();
        if (!rightPaddleUp && !rightPaddleDown)
            rightPaddle.stop();
    }
}