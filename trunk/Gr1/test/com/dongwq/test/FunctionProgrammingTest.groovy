package com.dongwq.test;
import org.junit.Test 

import static org.junit.Assert.*;

class FunctionProgrammingTest {

	def fac(n){ n == 0 ? 1: n* fac(n-1); }
	//def integers(n){ cons(n, { integers(n+1) } )}
	
	@Test
	public void testFun()
	{
		println fac(4);
		
		// now define and user infinite streams
//		
//		def naNo = integers(1);
//		
//		println naNo.take(10).join(",");
		
		
			
	}
}
