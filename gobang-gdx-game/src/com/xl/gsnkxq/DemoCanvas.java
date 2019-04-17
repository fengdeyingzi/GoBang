package com.xl.gsnkxq;
//import javax.microedition.lcdui.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008 romeo</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DemoCanvas
     implements Runnable {

  SpriteX spx;
  Thread thread;
  boolean play;
  int action = 0;
  long time;

  public DemoCanvas() {
   // setFullScreenMode(true);
    spx = new SpriteX("/a.sprite", "/a.png");
    spx.setVisible(true);
    thread = new Thread(this);
    thread.start();
  }

  public void run() {
    play = true;
    time = System.currentTimeMillis();
    while (play) {
     // repaint();
     // serviceRepaints();
    }
  }
/*
  public void paint(Graphics g) {
    g.setColor(~0);
    g.fillRect(0, 0, getWidth(), getHeight());

    spx.setPosition(getWidth() / 2, getHeight() / 2);
    spx.setAction(action);
    spx.update();

    spx.paint(g);
    if (System.currentTimeMillis() - time >= 5000) {
      action = (action + 1) % spx.getActionCount();
      time = System.currentTimeMillis();
    }
  }
  */
}
