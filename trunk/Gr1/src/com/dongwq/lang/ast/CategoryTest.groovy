package com.dongwq.lang.ast

import org.junit.Test

class CategoryTest
{

	@Test
	public void testUsage()
	{
		use(NumberCategory)
		{
			def dist = 300.meters

			assert dist instanceof Distance
			assert dist.toString() == "300m"
		}
	}
}

final class Distance
{
	def number
	String toString()
	{
		"${number}m"
	}
}


class NumberCategory
{
	static Distance getMeters(Number self)
	{
		new Distance(number: self)
	}
}




