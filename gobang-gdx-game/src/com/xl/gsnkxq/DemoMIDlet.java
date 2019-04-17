package com.xl.gsnkxq;
//import javax.microedition.midlet.*;
//import javax.microedition.lcdui.*;

/**
 * <p>Title: </p> 
 * 
 * <p>Description: </p> 
 * 
 * <p>Copyright: Copyright (c) 2008</p> 
 * 
 * <p>Company: </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class DemoMIDlet  {
  static DemoMIDlet instance;
  DemoCanvas displayable = new DemoCanvas();
  public DemoMIDlet() {
    instance = this;
  }

  public void startApp() {
   // Display.getDisplay(this).setCurrent(displayable);
  }

  public void pauseApp() {
  }

  public void destroyApp(boolean unconditional) {
  }

  public static void quitApp() {
    instance.destroyApp(true);
   // instance.notifyDestroyed();
    instance = null;
  }

}
