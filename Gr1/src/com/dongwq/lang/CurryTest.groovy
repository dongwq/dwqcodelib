package com.dongwq.lang

import org.junit.Test



class CurryTest
{

	@Test
	public void testUsage1()
	{
		def lSubtract =
		{ x, y -> return x - y }
		def rSubtract =
		{ y, x -> return x - y }
		def dec = rSubtract.curry(1)
		// dec = { x -> return x - 1 }
		def cent = lSubtract.curry(100)
		// cent = { y -> return 100 - y }
		def p = dec.call(5)                      // explicit call
		def q = cent(25)                         // implicit call
		println "p: ${p}"                        // p is 4
		println "q: ${q}"

		def sr = lSubtract.curry(3,10);

		def r1 = sr();
		println "r1: $r1"
		
		
	}
}
