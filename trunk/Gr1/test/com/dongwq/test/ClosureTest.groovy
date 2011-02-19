package com.dongwq.test;
import org.junit.Test 

import static org.junit.Assert.*;

class ClosureTest
{
	
	@Test
	public void testUsage1()
	{
		def dbNumber =
		{ num -> num * 2};
		
		println dbNumber(3);
		
		def processThenPrint =
		{num, re, fPro -> re = fPro(num);println "hello, $num :$re"};
		
		processThenPrint(3,0, dbNumber);
		processThenPrint(23, 55){ num -> num * 3};
	}
	@Test
	public void testUsge2()
	{
		[1,2,3].each {println it; }
		
		def sum = 0;
		1.upto(10) {
			sum += it;
			 }
		println sum;
	}
	
	
	@Test
	public void testOwner()
	{
		def outerClosure = {
			println ""
			println "the owner of outerClosure: " + owner
			println "the delegate of outerClosure: " + delegate
			println "this in the outerClosure: " + this
			def innerClosure = {
				println ''
				println "  the owner of innerClosure: " + owner
				println "  the delegate of innerClosure: " + delegate
				println "  this in the innerClosure: " + this
				def innestClosure = {
					println ''
					println "    the owner of innestClosure: " + owner
					println "    the delegate of innestClosure: " + delegate
					println "    this in the innestClosure: " + this
				}
				println "innestClosure: " + innestClosure
				innestClosure()
			}
			println "innerClosure: " + innerClosure
			innerClosure()
		}
		
		println this
		println "outerClosure: " + outerClosure
		outerClosure()
	}
}
