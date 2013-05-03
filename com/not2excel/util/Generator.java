package com.not2excel.util;

import com.not2excel.main.Main;

/**
 * Created with IntelliJ IDEA.
 * User: steelers
 * Date: 4/12/13
 * Time: 10:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class Generator
{
	public static double mapDensity = .25, mapLeveling = .25, upperBounds = Main.mapSize / 64, T = Math.PI,
			heightSmooth            = 8;

	public double getMapHeight(int x, int z)
	{
		return 5.0; //get height from int array
	}

	public double getMapColor(int x, int z)
	{
		return 0.0; //
	}

	public void BuildMap()
	{
		try
		{
			heightSmooth = 8 * (Main.newSize / 128.0);
			upperBounds = Main.newSize / 64;
			System.gc();
			Main.mapSize = Main.newSize;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
