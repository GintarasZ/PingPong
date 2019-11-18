package com.company;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;

    private static Thread gameThread;

    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;

    public Game() {
        screenSetup();
        new Window("Pong", this);
        initialise();

        this.addKeyListener(new KeyInput(leftPaddle, rightPaddle));
    }

    private void screenSetup() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    }

    private void initialise() {
        ball = new Ball();
        leftPaddle = new Paddle(true);
        rightPaddle = new Paddle(false);
    }

    @Override
    public void run() {
        this.requestFocus();
        while (true) {
            update();
            draw();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public static void stop() {
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update() {
            ball.update(leftPaddle, rightPaddle);
            leftPaddle.update(ball);
            rightPaddle.update(ball);
    }

    public void draw() {
        BufferStrategy buffer = this.getBufferStrategy();

        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = buffer.getDrawGraphics();
        drawBackground(g);
        ball.draw(g);
        leftPaddle.draw(g);
        rightPaddle.draw(g);

        buffer.show();
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public static int ensureRange(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public static int sign(double s) {
        if (s <= 0)
            return -1;
        return 1;
    }

    public static void main(String[] args) {
        new Game();
    }
}