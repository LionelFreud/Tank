package com.yyedu.TankGame03.Tanks;


import com.yyedu.TankGame03.Shot;

import java.util.Vector;

public class Tank {
    private int direction;
    private int x;
    private int y;
    private int speed;
    public Shot shot = null;
    private boolean life = true;
    public String name = null;
    public Vector<Shot> shots = new Vector<Shot>();

    public void setName(String name) {
        this.name = name;
    }

    public Tank(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Tank(int x, int y, int speed, int direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void UP() {
        y -= speed;
    }

    public void DOWN() {
        y += speed;
    }

    public void LEFT() {
        x -= speed;
    }

    public void RIGHT() {
        x += speed;
    }

    public void Bullet() {
        if (name != null && shots.size() == 3) {
            return;
        }
        if (name == null && shots.size() == 1) {
            return;
        }
        switch (getDirection()) {
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;

        }
        shots.add(shot);
        new Thread(shot).start();
    }




}
