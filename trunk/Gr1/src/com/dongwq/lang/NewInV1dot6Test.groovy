package com.dongwq.lang

import org.junit.Test;

class NewInV1dot6Test
{

	@Test
	public void testMultiAssign()
	{
		def (int a, String b) = [3, "efwf"];

		assert a == 3
		assert b == 'efwf'

		def firstname, lastname;
		int c;
		(firstname, lastname,  c) = "dong weiqiang 1 b".tokenize();

		assert firstname == 'dong'
		assert lastname == 'weiqiang'
		assert c == 49;
	}

	@Test
	public void testMultiAssign4Swap()
	{
		def a = 1, b = 3;
		(a, b) = [b, a]

		assert a == 3 && b == 1;
	}

	@Test
	public void test4MethodReturn()
	{
		assert method() == 1
		
		
		assert method(false) == 1
		assert method(true) == 2
		
	}

	def method()
	{
		if (true) 1
		else 0
	}
	
	def method(bool) {
		try {
			if (bool) throw new Exception("foo")
			1
		} catch(e) {
			2
		} finally {
			3
		}
	}
	
	
}
