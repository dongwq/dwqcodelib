package com.dongwq.test;

import org.junit.Test;

import static org.junit.Assert.*;

class GStringTest {

	@Test
	public void testG()
	{
		/**
		* 闭包用“{}”括起，“->”前面是参数，后面是处理语句，可以使用call调用，也可以在“{}”后直接使用“()”来执行。
		闭包可以有多个参数，各个参数用“,”隔开。如果只有一个参数的话可以省略不写，可以使用关键字“it”来代表。
		
		*/
		def clo = { p1, p2 ->
		   println '${p2}, ${p2}'
		   println "${p1}, ${p2}"
		   
		   println "$p1, $p2";
		}
		
		clo.call('hello','world');
		
		/**
		（7）inject
		遍历集合，第一次将传递的值和集合项目传给闭包，将处理结果作为传递的值，和下一个集合项目传给闭包，依此类推。
		
		java 代码
		def value = [1, 2, 3].inject('counting: ') { str, item -> str + item }
		assert value == "counting: 123"
		value = [1, 2, 3].inject(0) { count, item -> count + item }
		assert value == 6
		*/
	}
	
	
	@Test
	public void testGOper()
	{
		println "text" * 3<<" hello";
	}
	@Test
	public void testNullBlank()
	{
		def a = "";
		def b = null;
		
		def c = " ";
		c = null;
		if( a )print " a "
		if(b) println " b "
		if(c?.trim()) println " c " ; // 对于串判断时要去掉null, "", "  "三种情况的，可用这种写法。
	}
}
