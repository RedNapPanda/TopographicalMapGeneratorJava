package com.not2excel.main;

import com.not2excel.util.Keys;
import com.not2excel.util.Generator;
import com.not2excel.view.Player;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: steelers
 * Date: 4/12/13
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main
{
	public static Player player = new Player(0, 0, 0);

	static        float dx = 0f;
	static        float dy = 0.0f;
	public static float dt = 0.025f;

	static        float mSens  = 0.15f;
	public static float mSpeed = 25.0f;

	public static Renderer r;
	static        Keys     keys;
	public static Generator gen;

	static long fpsTime;
	static int tfps = 0, fps = 0;

	public static boolean gameRunning = true;
	public static boolean capScr      = false;
	static boolean buildMap;
	public static int targetWidth = 1280, targetHeight = 720;
	public static double mapSize = 256, newSize = 256;
	static ByteBuffer    pixBuf;
	static BufferedImage out;
	public static boolean black = true;

	private static void init() throws Exception
	{
		r = new Renderer();
		keys = new Keys();
		Keyboard.create();
		Mouse.setGrabbed(true);
		gen = new Generator();
	}

	private static void initDisplay(boolean fullscreen)
	{
		try
		{
			DisplayMode chosenMode = new DisplayMode(targetWidth, targetHeight);
			Display.setDisplayMode(chosenMode);
			Display.setFullscreen(fullscreen);
			Display.setTitle("Terrain Generator");
			Display.setSwapInterval(0);
			Display.setVSyncEnabled(false);
			Display.create();

		}
		catch (LWJGLException e)
		{
			Sys.alert("Error", "Unable to create display.");
			System.exit(0);
		}

	}

	private static boolean initGL()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(90.0f, (float) targetWidth / (float) targetHeight, 0.1f, 500.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		return true;
	}

	private static ActionListener logic = new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			dx = Mouse.getDX();
			dy = Mouse.getDY();
			player.camera.yaw(dx * mSens);
			player.camera.pitch(-dy * mSens);
			keys.Handle();

		}

	};

	private static void gameLoop() throws Exception
	{
		player.camera.position.x = -(float) mapSize / 2;
		player.camera.position.y = 20;
		player.camera.position.z = -(float) mapSize / 2;
		pixBuf = BufferUtils.createByteBuffer(targetHeight * targetWidth * 3);
		out = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
		new File(System.getProperty("user.home") + "/Terrain Generator/").mkdirs();
		int R, G, B, i = 0;
		Timer t = new Timer(16, logic);
		t.start();
		fpsTime = System.currentTimeMillis();
		while (gameRunning)
		{
			if (mapSize != newSize) //initialize the map generator
				return;
			r.Render();
			Display.update();
			GL11.glLoadIdentity();
			player.camera.lookAndMove();
			if (Display.isCloseRequested()) gameRunning = false;
			if (capScr)
			{
				capScr = false;
				pixBuf.position(0);
				GL11.glReadBuffer(GL11.GL_BACK);
				GL11.glReadPixels(0, 0, targetWidth, targetHeight, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, pixBuf);
				for (int x = 0;
						x < targetWidth;
						x++)
					for (int y = 0;
							y < targetHeight;
							y++)
					{
						R = pixBuf.get((x + targetWidth * y) * 3) & 0xFF;
						G = pixBuf.get((x + targetWidth * y) * 3 + 1) & 0xFF;
						B = pixBuf.get((x + targetWidth * y) * 3 + 2) & 0xFF;
						out.setRGB(x, targetHeight - (y + 1), 0xFF << 24 | R << 16 | G << 8 | B);
					}
				try
				{
					ImageIO.write(out, "png", new File(System.getProperty("user.home") + "/Voltage/sc" + i++ + ".png"));
				}
				catch (Exception e)
				{
				}
			}
			//Display.sync(60);
			tfps++;
			if (System.currentTimeMillis() - fpsTime >= 1000)
			{
				fps = tfps;
				tfps = 0;
				fpsTime += 1000;
			}
		}
		t.stop();
		Display.destroy();
		System.exit(0);
	}

	public static void main(String[] args) throws Exception
	{
		initDisplay(false);
		initGL();
		init();
		gameLoop();
	}
}
