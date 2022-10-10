package com.yyedu.TankGame03.Tanks;

public class Enemy extends Tank implements Runnable{
    int type = 1;

    public Enemy(int x, int y, int speed, int direction) {
        super(x, y, speed, direction);
    }

    public int getType() {
        return type;
    }

    @Override
    public void run() {
        while (isLife()){
            switch (getDirection()){
                case 0:
                    for (int i = 0; i < 8; i++) {
                        if(getY() >= 0){
                            UP();
                        }else {
                            break;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 20; i++) {
                        if (getX() <= 940){
                            RIGHT();
                        }else {
                            break;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 15; i++) {
                        if (getY() <= 940){
                            DOWN();
                        }else {
                            break;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 10; i++) {
                        if (getX() >= 0){
                            LEFT();
                        }else {
                            break;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            setDirection((int)(Math.random() * 4));
        }
        }


    }
