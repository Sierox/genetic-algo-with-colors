package com.sierox.genetic.gui;
import javax.swing.*;

public class RGBInputBox {
	private JTextField r;
	private JTextField g;
	private JTextField b;
	
	private Integer red;
	private Integer green;
	private Integer blue;
    
   public RGBInputBox() {
	
	   r = new JTextField(3);
	   g = new JTextField(3);
	   b = new JTextField(3);

	   JPanel myPanel = new JPanel();
	   myPanel.add(new JLabel("Enter RGB values (0-255):"));
	   myPanel.add(Box.createHorizontalStrut(10));
	   myPanel.add(new JLabel("Red:"));
	   myPanel.add(r);
	   myPanel.add(Box.createHorizontalStrut(7));
	   myPanel.add(new JLabel("Green:"));
	   myPanel.add(g);
	   myPanel.add(Box.createHorizontalStrut(7));
	   myPanel.add(new JLabel("Blue:"));
	   myPanel.add(b);

	   int result = JOptionPane.showConfirmDialog(null, myPanel, 
			   	"Enter Red, Green and Blue values.", JOptionPane.OK_CANCEL_OPTION);
	   if (result == JOptionPane.OK_OPTION) {
		   red = Integer.parseInt(r.getText());
		   green = Integer.parseInt(g.getText());
		   blue = Integer.parseInt(b.getText());
      } else if (result == JOptionPane.CANCEL_OPTION){
    	  
      }
   }
   
   public int getRed() {
	   return red;
   }
   
   public int getGreen() {
	   return green;
   }
   
   public int getBlue() {
	   return blue;
   }
   
   public int[] getRGB() {
	   return new int[] {red, green, blue};
   }
}