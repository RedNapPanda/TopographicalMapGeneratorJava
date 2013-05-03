package com.not2excel.view;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created with IntelliJ IDEA.
 * User: steelers
 * Date: 4/10/13
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player
{
	public Camera camera;

	public Vector3f position;
	boolean jumping;
	boolean allowJump;
	float   jump;
	float   strength;
	float step  = 0.15f;
	float limit = 3.0f;

	public Player(float x, float y, float z)
	{
		position = new Vector3f(x, y, z);
		camera = new Camera(position);
		jumping = false;
		allowJump = true;
		jump = 0.0f;
		strength = limit;
	}

	/*
	could input a collision method for ground collision, not needed
	for the current project however
	 */

	public void jump()
	{
		jump += step;
		if(jump <= limit)
		{
			strength -= step;
			position.y += step * strength;
		}
		else
		{
			strength += step;
			if(position.y - step * strength <= 0.0f)
			{
				position.y = 0.0f;
				jumping = false;
				jump = 0.0f;
				strength = limit;
			}
			else
			{
				position.y -= step * strength;
			}
		}
	}
}
