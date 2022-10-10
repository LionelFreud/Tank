package com.yyedu.TankGame03;

public class Shot implements Runnable{
    int x;
    int y;
    //方向
    int direct;
    //速度
    int speed = 5;
    boolean live = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setLive(boolean live){
        this.live = live;
    }

    @Override
    public void run() {
        while (live){
            if (!(x > 0 && x <1000 && y > 0 && y < 1000)){
                live = false;
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           switch (direct){
               case 0:
                   y -= speed;
                   break;
               case  1:
                   x += speed;
                   break;
               case 2:
                   y += speed;
                   break;
               case 3:
                   x -= speed;
                   break;
           }
        }
    }
}
