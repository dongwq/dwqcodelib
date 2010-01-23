package org.arabidopsis.ahocorasick;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * Quick and dirty code: measures the amount of time it takes to construct an
 * AhoCorasick tree out of all the words in <tt>/usr/share/dict/words</tt>.
 */

public class TimeTrial
{
	static void countFreq()
	{
		// Í³¼Æ´ÊÆµ
		Map<String, Integer> freqCount = new TreeMap<String, Integer>();
		Scanner scan = null;
		try
		{
			scan = new Scanner(new File("./words.txt"));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		if (scan != null)
		{
			while (scan.hasNext())
			{

				String str = scan.next().trim();
				if (freqCount.get(str) == null) freqCount.put(str, 1);
				else freqCount.put(str, freqCount.get(str) + 1);
			}
		}

		for (String key : freqCount.keySet())
		{
			System.out.println( key + ":" + freqCount.get(key));
		}
	}

	static public void main(String[] args) throws IOException
	{

		long startTime = System.currentTimeMillis();
		AhoCorasick tree = new AhoCorasick();

		Scanner scan = new Scanner(new File("./words.txt"));

		while (scan.hasNext())
			tree.add(scan.next().getBytes(), null);

		tree.prepare();
		long endTime = System.currentTimeMillis();
		System.out.println("endTime - startTime = " +
				(endTime - startTime) +
				" milliseconds");

		startTime = System.currentTimeMillis();
		AhoCorasick tree1 = new AhoCorasick();
		BufferedReader reader = new BufferedReader
				(new InputStreamReader
				(new FileInputStream("./words.txt")));
		String line;

		while ((line = reader.readLine()) != null)
		{
			tree1.add(line.getBytes(), null);
		}

		tree1.prepare();
		endTime = System.currentTimeMillis();
		System.out.println("endTime - startTime = " +
				(endTime - startTime) +
				" milliseconds");
		
		
		countFreq();
	}
}
