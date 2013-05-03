package com.not2excel.view;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created with IntelliJ IDEA.
 * User: steelers
 * Date: 4/10/13
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Camera
{
	public Vector3f position = null;

	float pitch = 0.0f;
	float yaw   = 135.0f;

	public Camera(Vector3f pos)
	{
		position = pos;
	}

	public void pitch(float value)
	{
		pitch += value;
		if (pitch > 85f) pitch = 84.999f;
		if (pitch < -85f) pitch = -84.999f;
	}

	public void yaw(float value)
	{
		yaw += value;
		if (yaw > 360f) yaw = 0.001f;
		if (yaw < 0f) yaw = 359.999f;
	}

	public void lookAndMove()
	{
		GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
		GL11.glTranslatef(position.x, -position.y, position.z);
	}

	public void moveForward(float distance)
	{
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw));
		position.z += distance * (float) Math.cos(Math.toRadians(yaw));
	}

	public void moveBackward(float distance)
	{
		position.x += distance * (float) Math.sin(Math.toRadians(yaw));
		position.z -= distance * (float) Math.cos(Math.toRadians(yaw));

	}

	public void moveLeft(float distance)
	{
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw - 90));
		position.z += distance * (float) Math.cos(Math.toRadians(yaw - 90));
	}

	public void moveRight(float distance)
	{
		position.x -= distance * (float) Math.sin(Math.toRadians(yaw + 90));
		position.z += distance * (float) Math.cos(Math.toRadians(yaw + 90));
	}
}
