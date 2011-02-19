package com.dongwq.lang

import org.junit.Test

class FileOperateTest {

	/**
	 * 在GroovyApp 在IDE中时，System.getProperty("user.dir")返回的是当前项目具体路径
	 * 
	 * 甚至在IDE的GroovyConsole中也是
	 * 
	 * 但是从命令行打开的Groovy中打开的就一样了。它返回C:\Documents and Settings\Administrator
	 */
	@Test
	public void testPath()
	{
		
		def p = this.class.getClassLoader().getResource("");
		
		println p;
		
		p = this.class.getClassLoader().getResource("/src");
		
		println p;
		
		p = this.class.getClassLoader().getResource(".");
		
		println p;
		
		def url = ClassLoader.getSystemResource("");
		println url;
		
		println System.getProperty("user.dir");
	}
}
