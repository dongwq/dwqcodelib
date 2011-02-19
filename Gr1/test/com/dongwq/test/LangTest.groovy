package com.dongwq.test;

import static org.junit.Assert.*

import org.junit.Test

import com.dongwq.bean.Person

public class LangTest {
	
	@Test
	public void testShell()
	{
		//println evaluate('1 + 2');
		GroovyShell shell = new GroovyShell()
		Object result = shell.evaluate("12+23")
		
		println shell.evaluate(" 3 + 33 / 2");
	}
	@Test
	public void testBasic()
	{
		def re = 35/10;
		println re.class;
		
		def re2 = 35 + 2;
		println 're2.class = ' + re2.class;
		
		println 35d/10;
		
		println 35.class
		
		println 35.intdiv(10);//这是与java中的不同之处啊。35/10=3,原因？？
		
		println 35.2342d.round(3)
		println 35.2342f.trunc(2)
//		println 35.2342.round(3) // 上在的可以，但是这里的不行，因为无明确结束位置
	}
	
	@Test
	public void testSpreadOper()
	{
		def personList = new ArrayList<Person>();
		
		def namesList = ['234', '234', '23424']
		
		for(n in namesList)
		{
			def p = new Person();
			p.name = n;
			personList.add(p);
		}
		
		println personList*.name ;
	}	
	
}
