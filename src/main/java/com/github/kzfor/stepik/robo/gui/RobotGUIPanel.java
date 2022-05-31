package com.github.kzfor.stepik.robo.gui;

import com.github.kzfor.stepik.robo.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class RobotGUIPanel extends JPanel {
    static final int SQUARE_SIDE = 500;
    static final int GRID_SIZE = 5;
    static final int UNIT_SIZE = SQUARE_SIDE / GRID_SIZE;
    static final int SCREEN_DELAY = 600;
    // Coordinates of robot
    private int robotX;
    private int robotY;
    // Coordinates robot must achieve
    private int pointX;
    private int pointY;

    private boolean canMove;
    private Direction robotDirection;
    private Random random;
    private Image robotImgL;
    private Image robotImgD;
    private Image robotImgR;
    private Image robotImgU;

    public RobotGUIPanel() {
        random = new Random();
        canMove = true;
        setPreferredSize(new Dimension(SQUARE_SIDE, SQUARE_SIDE));
        robotDirection = Direction.values()[random.nextInt(3)];
        runRobot();
    }

    private void runRobot() {
        setUpPointCoordinates();
        setUpRobotCoordinates();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        drawGrid(g);
        g.setColor(Color.BLUE);
        g.fillOval(pointX, pointY, UNIT_SIZE, UNIT_SIZE);
        drawRobot(g);
    }

    private void drawRobot(Graphics g) {
        Image img = loadImage();
        g.drawImage(img, robotX, robotY, UNIT_SIZE, UNIT_SIZE, this);
    }

    private void checkCollisions() {
        if (!canMove) {
            return;
        }
        if (robotX >= SQUARE_SIDE || robotY >= SQUARE_SIDE || robotX < 0 || robotY < 0) {
            canMove = false;
            JOptionPane.showMessageDialog(
                    this,
                    "Robot got lost and no longer can move :(",
                    "Robot got lost",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        if (robotX == pointX && robotY == pointY) {
            canMove = false;
            JOptionPane.showMessageDialog(
                    this,
                    "Congratulations!",
                    "You won!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void makeStep() {
        if (!canMove) {
            return;
        }
        try {
            Thread.sleep(SCREEN_DELAY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        switch (robotDirection) {
            case UP:
                robotY -= UNIT_SIZE;
                break;
            case LEFT:
                robotX -= UNIT_SIZE;
                break;
            case RIGHT:
                robotX += UNIT_SIZE;
                break;
            case DOWN:
                robotY += UNIT_SIZE;
                break;
        }
        repaint();
        checkCollisions();
    }

    private void setUpPointCoordinates() {
        pointX = random.nextInt(SQUARE_SIDE / UNIT_SIZE) * UNIT_SIZE;
        pointY = random.nextInt(SQUARE_SIDE / UNIT_SIZE) * UNIT_SIZE;
    }

    private void setUpRobotCoordinates() {
        robotX = random.nextInt(SQUARE_SIDE / UNIT_SIZE) * UNIT_SIZE;
        robotY = random.nextInt(SQUARE_SIDE / UNIT_SIZE) * UNIT_SIZE;
        if (robotX == pointX || robotY == pointY) {
            setUpRobotCoordinates();
        }
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i <= SQUARE_SIDE; i = i + (SQUARE_SIDE / GRID_SIZE)) {
            g.drawLine(i, 0, i, SQUARE_SIDE);
            g.drawLine(0, i, SQUARE_SIDE, i);
        }
    }

    private Image loadImage() {
        if (robotImgL == null || robotImgD == null) {
            try {
                robotImgL = ImageIO.read(new File(
                                Objects.requireNonNull(getClass()
                                                .getClassLoader()
                                                .getResource("robot-l.png"))
                                        .getFile()
                        )
                );
                robotImgD = ImageIO.read(new File(
                                Objects.requireNonNull(getClass()
                                                .getClassLoader()
                                                .getResource("robot-d.png"))
                                        .getFile()
                        )
                );
                robotImgR = ImageIO.read(new File(
                                Objects.requireNonNull(getClass()
                                                .getClassLoader()
                                                .getResource("robot-r.png"))
                                        .getFile()
                        )
                );
                robotImgU = ImageIO.read(new File(
                                Objects.requireNonNull(getClass()
                                                .getClassLoader()
                                                .getResource("robot-u.png"))
                                        .getFile()
                        )
                );
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        switch (robotDirection) {
            case UP:
                return robotImgU;
            case LEFT:
                return robotImgL;
            case RIGHT:
                return robotImgR;
            case DOWN:
                return robotImgD;
        }
        return null;
    }

    public Direction getRobotDirection() {
        return robotDirection;
    }

    public void setRobotDirection(Direction direction) {
        if (!canMove) {
            return;
        }
        checkCollisions();
        try {
            Thread.sleep(SCREEN_DELAY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        robotDirection = direction;
        repaint();
    }

    public int getRobotX() {
        return robotX;
    }

    public int getRobotY() {
        return robotY;
    }

    public int getPointX() {
        return pointX;
    }

    public int getPointY() {
        return pointY;
    }
}
