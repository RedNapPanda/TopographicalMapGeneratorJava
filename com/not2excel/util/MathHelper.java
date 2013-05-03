package com.not2excel.util;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created with IntelliJ IDEA.
 * User: steelers
 * Date: 4/10/13
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class MathHelper
{
	enum Orientation
	{
		N,
		NE,
		E,
		SE,
		S,
		SW,
		W,
		NW;
	}

	public static double calculateDistance(Vector3f pos, double x, double y, double z)
	{
		return Math.sqrt((pos.x - x) * (pos.x - x) + (pos.y - y) * (pos.y - y) + (pos.z - z) * (pos.z - z));
	}

	public static Orientation getDirection(float yaw)
	{
		if (yaw <= 22.5 && yaw > 337.5)
		{
			return Orientation.N;
		}
		if (yaw <= 337.5 && yaw > 292.5)
		{
			return Orientation.NE;
		}
		if (yaw <= 292.5 && yaw > 247.5)
		{
			return Orientation.E;
		}
		if (yaw <= 247.5 && yaw > 202.5)
		{
			return Orientation.SE;
		}
		if (yaw <= 202.5 && yaw > 157.5)
		{
			return Orientation.S;
		}
		if (yaw <= 157.5 && yaw > 112.5)
		{
			return Orientation.SW;
		}
		if (yaw <= 112.5 && yaw > 67.5)
		{
			return Orientation.W;
		}
		if (yaw <= 67.5 && yaw > 22.5)
		{
			return Orientation.NW;
		}
		return null;
	}
}
