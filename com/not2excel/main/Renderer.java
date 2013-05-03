package com.not2excel.main;

import com.not2excel.util.GraphicsHelper;
import com.not2excel.util.MathHelper;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: steelers
 * Date: 4/12/13
 * Time: 9:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Renderer
{
	FloatBuffer aLight, aPos, fog;
	byte   renderDistance = 25;
	double heightSmooth   = 8;
	public boolean Wireframe      = true;
	public boolean RenderDistance = false;
	int x, z;

	public Renderer()
	{
		GL11.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
	}

	public void Render() throws Exception
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		GL11.glPushMatrix();
		GraphicsHelper.make3D();
		for (x = 0;
				x < Main.mapSize;
				x++)
		{
			GL11.glBegin(GL11.GL_LINE_STRIP);
			for (z = 0;
					z < Main.mapSize;
					z++)
			{
				if (MathHelper
						.calculateDistance(Main.player.camera.position, -x, Main.gen.getMapHeight(x, z), -z) >
						renderDistance && RenderDistance)
				{
					continue;
				}
				GL11.glColor3d(Main.gen.getMapColor(x, z), 0, -Main.gen.getMapColor(x, z));
				GL11.glVertex3d(x, Main.gen.getMapHeight(x, z), z);
			}
			GL11.glEnd();
		}

		if (!Wireframe) GL11.glBegin(GL11.GL_QUADS);
		for (z = 0;
				z < Main.mapSize;
				z++)
		{
			if (Wireframe) GL11.glBegin(GL11.GL_LINE_STRIP);
			for (x = 0;
					x < Main.mapSize;
					x++)
			{
				if (MathHelper
						.calculateDistance(Main.player.camera.position, -x, Main.gen.getMapHeight(x, z), -z) >
						renderDistance && RenderDistance)
				{
					continue;
				}
				if (!Wireframe)
				{
					GL11.glColor3d(Main.gen.getMapColor(x, z), 0, -Main.gen.getMapColor(x, z));
					GL11.glVertex3d(x, Main.gen.getMapHeight(x, z), z);
					GL11.glVertex3d(x, Main.gen.getMapHeight(x, z + 1), z + 1);
					GL11.glVertex3d(x + 1, Main.gen.getMapHeight(x + 1, z + 1), z + 1);
					GL11.glVertex3d(x + 1, Main.gen.getMapHeight(x + 1, z), z);
				}
				else
				{
					GL11.glColor3d(Main.gen.getMapColor(x, z), 0, -Main.gen.getMapColor(x, z));
					GL11.glVertex3d(x, Main.gen.getMapHeight(x, z), z);//*/
				}
			}
			if (Wireframe) GL11.glEnd();
		}
		if (!Wireframe) GL11.glEnd();
		GL11.glPopMatrix();
		GraphicsHelper.make2D();
		GL11.glColor3d(0, 0, 0);
		GraphicsHelper.drawReticule();

	}

}
