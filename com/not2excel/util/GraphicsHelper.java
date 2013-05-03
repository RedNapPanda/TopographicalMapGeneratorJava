package com.not2excel.util;

import com.not2excel.main.Main;
import org.lwjgl.opengl.GL11;

/**
 * Created with IntelliJ IDEA.
 * User: steelers
 * Date: 4/12/13
 * Time: 7:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class GraphicsHelper
{
	public static void drawReticule()
	{
		GL11.glBegin(GL11.GL_QUADS);
		fillRect(639, 359, 2, 2, true);
		fillRect(644, 359, 4, 2, true);
		fillRect(648, 356, 2, 8, true);
		fillRect(639, 364, 2, 4, true);
		fillRect(636, 368, 8, 2, true);
		fillRect(632, 359, 4, 2, true);
		fillRect(630, 356, 2, 8, true);
		fillRect(639, 352, 2, 4, true);
		fillRect(636, 350, 8, 2, true);
		GL11.glEnd();
	}

	public static void fillRect(int x, int y, int w, int h, boolean raw) {
		if (!raw) GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2i(x, y);
		GL11.glVertex2i(x, y + h);
		GL11.glVertex2i(x + w, y + h);
		GL11.glVertex2i(x + w, y);
		if (!raw) GL11.glEnd();
	}

	public static void make2D() {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Main.targetWidth, Main.targetHeight, 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
	}

	public static void make3D() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
}
