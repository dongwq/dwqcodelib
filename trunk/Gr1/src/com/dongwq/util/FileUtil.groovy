package com.dongwq.util

import org.junit.Test;

class FileUtil
{

	@Test
	public void  filterBlankLine()
	{
		def fileIn = new File("fileIn.txt");
		def fileOut = new File("fileOut.txt");

		fileIn.eachLine
		{
			if( it?.trim())
				fileOut<<it<<'\n';
		}
		
	}
}
