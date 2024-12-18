package org.haigh.view;

import javax.swing.*;

public abstract class AFrame extends JFrame implements BaseUI{
   public AFrame(){
       this.init();
       this.setOnClick();
   }
}
