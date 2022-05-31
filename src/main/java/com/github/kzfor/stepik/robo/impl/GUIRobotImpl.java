package com.github.kzfor.stepik.robo.impl;

import com.github.kzfor.stepik.robo.Direction;
import com.github.kzfor.stepik.robo.abs.GoalPointAccessor;
import com.github.kzfor.stepik.robo.abs.Robot;
import com.github.kzfor.stepik.robo.gui.MainFrame;
import com.github.kzfor.stepik.robo.gui.RobotGUIPanel;

import javax.swing.*;

public class GUIRobotImpl implements Robot, GoalPointAccessor {
    private JFrame frame;
    private RobotGUIPanel robotPanel;

    public GUIRobotImpl() {
        robotPanel = new RobotGUIPanel();
        frame = new MainFrame(robotPanel);
    }

    @Override
    public Direction getDirection() {
        return robotPanel.getRobotDirection();
    }

    @Override
    public int getX() {
        return robotPanel.getRobotX();
    }

    @Override
    public int getY() {
        return robotPanel.getRobotY() * -1;
    }

    @Override
    public void turnLeft() {
        robotPanel.setRobotDirection(rotateRobot(false));
    }

    @Override
    public void turnRight() {
        robotPanel.setRobotDirection(rotateRobot(true));
    }

    @Override
    public void stepForward() {
        robotPanel.makeStep();
    }

    @Override
    public int getGoalX() {
        return robotPanel.getPointX();
    }

    @Override
    public int getGoalY() {
        return robotPanel.getPointY() * -1;
    }

    private Direction rotateRobot(boolean clockwise) {
        int robotDirectionInt = getDirection().ordinal();
        int resultDirectionInt =  clockwise ? robotDirectionInt + 1 : robotDirectionInt - 1;
        if (resultDirectionInt == -1) {
            resultDirectionInt = 3;
        } else if (robotDirectionInt == 4) {
            resultDirectionInt = 0;
        }
        return resultDirectionInt > 3 ? Direction.values()[0] : Direction.values()[resultDirectionInt];
    }
}
