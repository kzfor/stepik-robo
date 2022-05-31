package com.github.kzfor.stepik.robo.abs;

import com.github.kzfor.stepik.robo.Direction;

public interface Robot {
    public Direction getDirection();
    public int getX();
    public int getY();
    public void turnLeft();
    public void turnRight();
    public void stepForward();
}
