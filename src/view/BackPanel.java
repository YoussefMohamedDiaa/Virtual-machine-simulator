package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackPanel extends JPanel {
	
	private Image background;
	
	public BackPanel(Image background) {
		this.background = background;
	}
	
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
