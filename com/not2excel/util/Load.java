package com.not2excel.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: steelers
 * Date: 4/12/13
 * Time: 10:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class Load
{
	public static void loadData()
	{
	 	try
		 {
			 File file = new File(System.getProperty("user.home") + "/Desktop");
			 BufferedReader br = new BufferedReader(new FileReader(file));



			 br.close();
		 }
		 catch(IOException e)
		 {
			 e.printStackTrace();
		 }
	}
}
