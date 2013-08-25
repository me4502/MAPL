package com.me4502.MAPL.slick;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;

import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.SlickException;

import com.me4502.MAPL.MAPL;
import com.me4502.MAPL.rendering.RenderUtils;
import com.me4502.MAPL.slick.rendering.SlickRenderUtils;
import com.me4502.MAPL.util.SystemUtils;

public class SlickMAPL extends MAPL {

	private File appDir;
	SlickRenderUtils renderer;

	@Override
	public File getApplicationDirectory() {
		if (appDir == null) {
			appDir = getAppDir(getProgram().getProgramName().replace(" ", ""));
		}

		return appDir;
	}

	private File getAppDir(String par0Str) {
		String s = System.getProperty("user.home", ".");
		File file = null;

		if (getOs().equalsIgnoreCase("windows")) {
			String s1 = System.getenv("APPDATA");

			if (s1 != null)
				file = new File(s1, new StringBuilder().append(".").append(par0Str).append('/').toString());
			else
				file = new File(s, new StringBuilder().append('.').append(par0Str).append('/').toString());
		} else if (getOs().equalsIgnoreCase("macosx")) {
			file = new File(s, new StringBuilder().append("Library/Application Support/").append(par0Str).toString());
		} else if (getOs().equalsIgnoreCase("solaris")) {
			file = new File(s, new StringBuilder().append('.').append(par0Str).append('/').toString());
		} else if (getOs().equalsIgnoreCase("linux")) {
			file = new File(s, new StringBuilder().append(par0Str).append('/').toString());
		} else {
			file = new File(s, new StringBuilder().append(par0Str).append('/').toString());
		}

		if (!file.exists() && !file.mkdirs())
			throw new RuntimeException(new StringBuilder().append("The working directory could not be created: ").append(file).toString());
		else
			return file;
	}

	protected String getOs() {
		String s = System.getProperty("os.name").toLowerCase();

		if (s.contains("win"))
			return "windows";
		if (s.contains("mac"))
			return "macosx";
		if (s.contains("solaris"))
			return "solaris";
		if (s.contains("sunos"))
			return "solaris";
		if (s.contains("linux"))
			return "linux";
		if (s.contains("unix"))
			return "linux";
		return "linux";
	}

	@Override
	public RenderUtils getRenderer() {
		if(renderer == null)
			renderer = new SlickRenderUtils();
		return renderer;
	}

	public void setup(MAPLSlickProgram game, String title, int width, int height, boolean force) {

		try {
			System.out.println("Initializing MAPL!");
			this.game = game;
			game.load();

			System.out.println("Initializing Game!");
			final SlickGame sgame = new SlickGame(title);

			System.out.println("Starting Game!");
			System.setProperty("org.lwjgl.librarypath", MAPL.inst().getApplicationDirectory() + "/natives/" + SystemUtils.getOsString() + "");

			gameFrame = new JFrame(title);
			gameFrame.setSize(width, height);
			gameFrame.setLocationRelativeTo(null);

			app = new CanvasGameContainer(sgame);
			if(MAPL.inst().getProgram().getConfiguration() != null) {
				System.out.println("Setting Up Game Engine Configuration Values");
				app.getContainer().setVSync(MAPL.inst().getProgram().getConfiguration().vSync);
				boolean done = false;
				int samples = MAPL.inst().getProgram().getConfiguration().antiAliasing;
				while(done == false && samples > 0) {
					try {
						app.getContainer().setMultiSample(samples);
						done = true;
					} catch(Exception e){
						samples--;
					}
				}
				System.out.println("MSAA: " + samples);
				app.getContainer().setVerbose(MAPL.inst().getProgram().getConfiguration().debug);
				app.getContainer().setShowFPS(MAPL.inst().getProgram().getConfiguration().debug);
				System.out.println("Finishing configuration Engine settings");
			}
			app.getContainer().setTargetFrameRate(targetFrames);
			app.getContainer().setAlwaysRender(true);

			gameFrame.add(app);
			gameFrame.setResizable(true);

			gameFrame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					sgame.closeRequested();
					for(Window win : JFrame.getWindows())
						win.dispose();
					gameFrame.dispose();
					app.getContainer().exit();
					System.exit(0);
				}
			});
			gameFrame.setVisible(true);
			gameFrame.toFront();

			System.out.println("Starting Game!");
			app.start();
		} catch (UnsatisfiedLinkError e) {
			setup(game,title,width,height,true);
		} catch (SlickException e1) {
			e1.printStackTrace();
		}
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public boolean getPaused() {
		return paused;
	}

	public void setSpeedScale(int speed) {
		this.speed = speed;
	}

	public int getSpeedScale() {
		return speed;
	}

	/* Settings */
	public int targetFrames = 120;
	public boolean Fullscreen = false;
	public boolean Resizable = false;

	public String title = "Game Engine";

	public int width = 612;
	public int height = 384;
	public int cenX = width / 2;
	public int cenY = height / 2;

	public int mouseX;
	public int mouseY;

	public MAPLSlickProgram game;

	boolean hasInitialized = false;

	public static JFrame gameFrame;

	boolean paused = false;

	public static CanvasGameContainer app;

	private int speed = 1;
}