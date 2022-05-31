package com.github.kzfor.stepik.robo;

import com.github.kzfor.stepik.robo.impl.GUIRobotImpl;

import static com.github.kzfor.stepik.robo.YourCodeHere.moveRobot;

public class App {
    public static void main(String[] args) {
        GUIRobotImpl guiRobot = new GUIRobotImpl();

        int toX = guiRobot.getGoalX();
        int toY = guiRobot.getGoalY();
        moveRobot(guiRobot, toX, toY);
    }
}
