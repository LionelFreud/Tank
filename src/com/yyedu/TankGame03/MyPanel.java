package com.yyedu.TankGame03;

import com.yyedu.TankGame03.Tanks.Enemy;
import com.yyedu.TankGame03.Tanks.Tank;
import com.yyedu.TankGame03.Tanks.Telepathy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义坦克
    Telepathy telepathy = null;
    //敌人的数量多，同时需要单独管理，因此需要多线程
    Vector<Enemy> enemy = new Vector();
    int enemySize = 3;
    //定义一个集合存放炸弹
    Vector<Blast> bombs = new Vector();
    Image i1 = null;
    Image i2 = null;
    Image i3 = null;

    public MyPanel() {
        //初始化位置
        telepathy = new Telepathy(100, 800, 5);
        telepathy.setName("Tele");
        Enemy e1 = new Enemy(50, 50, 3, 1);
        new Thread(e1).start();
        Enemy e2 = new Enemy(200, 300, 6, 2);
        new Thread(e2).start();
        Enemy e3 = new Enemy(540, 130, 4, 3);
        new Thread(e3).start();
        enemy.add(e1);
        enemy.add(e2);
        enemy.add(e3);
        for (int i = 0; i < enemy.size(); i++) {
            enemy.get(i).Bullet();
        }

    }
    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD,25);
        g.setFont(font);
        g.drawString("累计击毁坦克",1100,50);
        DrawTank(1100,80,g,0,0);
        g.setColor(Color.BLACK);
        g.drawString("* " + (Recorder.getTanks()),1180,120);
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    //某个按键按下触发
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            telepathy.setDirection(0);
            if (telepathy.getY() >= 10) {
                telepathy.UP();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            telepathy.setDirection(1);
            if (telepathy.getX() <= 930) {
                telepathy.RIGHT();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            telepathy.setDirection(2);
            if (telepathy.getY() <= 930) {
                telepathy.DOWN();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            telepathy.setDirection(3);
            if (telepathy.getX() >= 10) {
                telepathy.LEFT();
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            if(telepathy.isLife()){
                telepathy.Bullet();
            }
        }


        repaint();
    }

    //某个按键释放触发
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 1000);
        showInfo(g);
        //填充矩形，默认黑色

        if (telepathy.isLife()) {
            DrawTank(telepathy.getX(), telepathy.getY(), g, telepathy.getDirection(), telepathy.getType());
        }

        for (int i = 0; i < enemy.size(); i++) {
            Enemy o = enemy.get(i);
            if (o.isLife()) {
                DrawTank(o.getX(), o.getY(), g, o.getDirection(), o.getType());
            }
        }
        for (int i = 0; i < telepathy.shots.size(); i++) {
            Shot shot = telepathy.shots.get(i);
            if (shot != null && shot.live == true) {
                g.fill3DRect(shot.x, shot.y, 3, 3, false);
                for (int j = 0; j < enemy.size(); j++) {
                    Enemy o = enemy.get(j);
                    Hit(shot, o);
                }
            } else {
                telepathy.shots.remove(shot);
                Recorder.add();
            }
        }

        for (int j = 0; j < enemy.size(); j++) {
            Enemy o = enemy.get(j);
                o.Bullet();
            for (int i = 0; i < o.shots.size(); i++) {
                Shot shot = o.shots.get(i);
                if (o.shot != null && o.shot.live != false) {
                    g.fill3DRect(shot.x, shot.y, 3, 3, false);
                    Hit(shot, telepathy);
                }else {
                    o.shots.remove(shot);
                }
            }
        }
        i1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image1.jpg"));
        i2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image2.jpg"));
        i3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image3.jpg"));

        for (int i = 0; i < bombs.size(); i++) {
            Blast blast = bombs.get(i);
            if (blast.getLife() > 4) {
                g.drawImage(i1, blast.x, blast.y, 40, 45, this);
            } else if (blast.getLife() > 2) {
                g.drawImage(i2, blast.x, blast.y, 40, 45, this);
            } else if (blast.getLife() > 0) {
                g.drawImage(i3, blast.x, blast.y, 40, 45, this);
            }
            blast.LifeMinus();
            if (blast.getLife() == 0) {
                bombs.remove(blast);
            }
        }
    }


    //x:坦克横坐标
    //y:坦克纵坐标
    //g:画笔
    //direction:坦克方向
    //type:坦克的类型
    public void DrawTank(int x, int y, Graphics g, int direction, int type) {

        //不同类型的坦克设置不同的颜色
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.YELLOW);
                break;
        }

        //根据坦克方向绘制坦克
        //朝向变动后坦克形状需要发生变化
        //0：上 1：右 2：下 3：左
        switch (direction) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 20, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 20, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                break;
        }


    }

    public void Hit(Shot s, Tank t) {
        switch (s.direct) {
            case 0:
                if (s.y <= (t.getY() + 60) && s.x >= t.getX() && s.x <= (t.getX() + 60) && s.y >= t.getY()) {
                    s.setLive(false);
                    t.setLife(false);
                    if (t.name == null) {
                        enemy.remove(t);
                    }
                    bombs.add(new Blast(t.getX(), t.getY()));
                }
                break;
            case 1:
                if (s.x >= t.getX() && s.y >= t.getY() && s.y <= (t.getY() + 60) && s.x <= (t.getX() + 60)) {
                    s.setLive(false);
                    t.setLife(false);
                    if (t.name == null) {
                        enemy.remove(t);
                    }
                    bombs.add(new Blast(t.getX(), t.getY()));
                }
                break;
            case 2:
                if (s.y >= t.getY() && s.x >= t.getX() && s.x <= (t.getX() + 60) && s.y <= (t.getY() + 60)) {
                    s.setLive(false);
                    t.setLife(false);
                    if (t.name == null) {
                        enemy.remove(t);
                    }
                    bombs.add(new Blast(t.getX(), t.getY()));
                }
                break;
            case 3:
                if (s.x <= (t.getX() + 60) && s.y >= t.getY() && s.y <= (t.getY() + 60) && s.x >= t.getX()) {
                    s.setLive(false);
                    t.setLife(false);
                    if (t.name == null) {
                        enemy.remove(t);
                    }
                    bombs.add(new Blast(t.getX(), t.getY()));
                }
                break;

        }
    }

    @Override
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
