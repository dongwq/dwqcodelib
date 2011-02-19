package com.dongwq.test;

import groovy.lang.Closure;

import java.util.regex.Matcher;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test 

class RegExpTest {

	
	@Test
	public void testRegExp()
	{
		Matcher p = "Hello World HelloWorld !" =~/Hello/;
		
		println p.class.name;
		println p;
		println p.size();
		
		assert "hello World" ==~ /hello.*/;
		assert "hello World" ==~ /Hello/;
		
	}
	
	@Test
	public void testRegExpUsage1()
	{
		//finder operator
		String re = "ok23 ief23eifjef223";
		
		def mat = ( re =~ /[a-z]+\d+/);
		mat.each { println it }
		
		//Match
		println "1.23".replaceAll(/\d+/){
			num -> num.toInteger() + 1
		} 
		
		
		
//		def houston(Closure doit){
//			(10..1).each {
//				count -> doit(count)
//			}
//		}
//		houston{ println it}
		
		def max = { x,y -> [x,y].max()}
		def atLeastTen = max.curry(10);
		
		println atLeastTen(5);
		println atLeastTen(25);
		
	}
	
	@Test
	public void test3()
	{
		assert java.lang.String == /foo/.class
		
		assert ( /Count is \d/ == "Count is \\d" )
		
	}
}
