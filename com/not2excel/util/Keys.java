package com.not2excel.util;

import com.not2excel.main.Main;
import org.lwjgl.input.Keyboard;

/**
 * Created with IntelliJ IDEA.
 * User: steelers
 * Date: 4/12/13
 * Time: 9:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class Keys
{
	public void Handle()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			Main.player.camera.moveForward(Main.mSpeed * Main.dt);
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_S)) Main.player.camera.moveBackward(Main.mSpeed * Main.dt);

		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			Main.player.camera.moveLeft(Main.mSpeed * Main.dt);
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_D)) Main.player.camera.moveRight(Main.mSpeed * Main.dt);

		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				if (Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT) && Main.mapSize > 0)
				{
					if (Main.mapSize > 32) Main.newSize -= 32;
				}
				else if (Keyboard.isKeyDown(Keyboard.KEY_ADD) && Main.mapSize < 2048) Main.newSize += 32;
				if (Keyboard.isKeyDown(Keyboard.KEY_Q)) Main.r.Wireframe = !Main.r.Wireframe;
				if (Keyboard.isKeyDown(Keyboard.KEY_E)) Main.r.RenderDistance = !Main.r.RenderDistance;
				if (Keyboard.isKeyDown(Keyboard.KEY_F9)) Main.capScr = true;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			Main.player.position.y += Main.mSpeed * Main.dt;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) Main.player.position.y -= Main.mSpeed * Main.dt;//*/
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Main.gameRunning = false;

	}
}
