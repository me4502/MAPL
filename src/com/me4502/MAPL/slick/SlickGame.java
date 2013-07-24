package com.me4502.MAPL.slick;

import java.awt.Toolkit;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.me4502.MAPL.MAPL;

public class SlickGame extends BasicGame {

	protected SlickGame(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {

		if (!((SlickMAPL)MAPL.inst()).hasInitialized)
			return;
		((SlickMAPL)MAPL.inst()).game.render(arg0, arg1);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {

		System.out.println("Initialization!");
		//GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_WRAP_S,GL11.GL_REPEAT);
		((SlickMAPL)MAPL.inst()).game.init(arg0);
		((SlickMAPL)MAPL.inst()).hasInitialized = true;
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {

		if(((SlickMAPL)MAPL.inst()).Fullscreen != SlickMAPL.gameFrame.isUndecorated()) {
			SlickMAPL.gameFrame.setVisible(false);
			SlickMAPL.gameFrame.dispose();
			if(((SlickMAPL)MAPL.inst()).Fullscreen) {
				Toolkit tk = Toolkit.getDefaultToolkit();
				int xSize = (int) tk.getScreenSize().getWidth();
				int ySize = (int) tk.getScreenSize().getHeight();
				SlickMAPL.gameFrame.setSize(xSize,ySize);
				SlickMAPL.gameFrame.setLocation(0, 0);
			} else {
				SlickMAPL.gameFrame.setSize(((SlickMAPL)MAPL.inst()).width, ((SlickMAPL)MAPL.inst()).height);
				SlickMAPL.gameFrame.setLocationRelativeTo(null);
			}
			SlickMAPL.gameFrame.setUndecorated(((SlickMAPL)MAPL.inst()).Fullscreen);
			SlickMAPL.gameFrame.setVisible(true);
			SlickMAPL.gameFrame.toFront();
			new Thread() {
				@Override
				public void run() {
					synchronized(SlickMAPL.gameFrame) {
						SlickMAPL.gameFrame.setAlwaysOnTop(true);
						SlickMAPL.gameFrame.toFront();
						SlickMAPL.gameFrame.setAlwaysOnTop(false);
					}
				}
			}.start();
		}
		if (!((SlickMAPL)MAPL.inst()).hasInitialized)
			return;
		for(int i = 0; i < ((SlickMAPL)MAPL.inst()).getSpeedScale(); i++) {
			if(((SlickMAPL)MAPL.inst()).mouseX != arg0.getInput().getMouseX() || ((SlickMAPL)MAPL.inst()).mouseY != arg0.getInput().getMouseY()) {
				((SlickMAPL)MAPL.inst()).mouseX = arg0.getInput().getMouseX();
				((SlickMAPL)MAPL.inst()).mouseY = arg0.getInput().getMouseY();
			}
			((SlickMAPL)MAPL.inst()).game.update(arg0, arg1);
		}
	}

	@Override
	public boolean closeRequested() {
		return ((SlickMAPL)MAPL.inst()).game.close();
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		((SlickMAPL)MAPL.inst()).game.mouseClicked(arg0, arg1, arg2, arg3);
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		((SlickMAPL)MAPL.inst()).game.mouseDragged(arg0, arg1, arg2, arg3);
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		((SlickMAPL)MAPL.inst()).game.mouseMoved(arg0, arg1, arg2, arg3);
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		((SlickMAPL)MAPL.inst()).game.mousePressed(arg0, arg1, arg2);
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		((SlickMAPL)MAPL.inst()).game.mouseReleased(arg0, arg1, arg2);
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		((SlickMAPL)MAPL.inst()).game.mouseWheelMoved(arg0);
	}

	@Override
	public void keyPressed(int arg0, char arg1) {
		((SlickMAPL)MAPL.inst()).game.keyPressed(arg0, arg1);
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		((SlickMAPL)MAPL.inst()).game.keyReleased(arg0, arg1);
	}
}