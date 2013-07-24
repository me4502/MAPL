package com.me4502.MAPL.slick;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.me4502.MAPL.MAPLProgram;

public interface MAPLSlickProgram extends MAPLProgram {

	public void render(GameContainer arg0, Graphics arg1);

	public void load();

	public boolean close();

	public void init(GameContainer arg0);

	public void update(GameContainer arg0, int arg1);

	public void mousePressed(int arg0, int arg1, int arg2);

	public void mouseReleased(int arg0, int arg1, int arg2);

	public void mouseWheelMoved(int arg0);

	public void mouseClicked(int arg0, int arg1, int arg2, int arg3);

	public void mouseDragged(int arg0, int arg1, int arg2, int arg3);

	public void mouseMoved(int arg0, int arg1, int arg2, int arg3);

	public void keyPressed(int arg0, char arg1);

	public void keyReleased(int arg0, char arg1);
}