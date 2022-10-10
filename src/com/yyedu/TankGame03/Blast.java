package com.yyedu.TankGame03;

public class Blast {
    int x;
    int y;
    int life = 6;
    boolean isLive = true;

    public Blast(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLife() {
        return life;
    }

    public void LifeMinus(){
        if (life > 0){
            life --;
        }else {
            isLive = false;
        }
    }
}
