package com.dongwq.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test

class ListMapRangeTest
{
	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testList()
	{
		def objList=[1, new Date(), 'jan']

		println objList;

		def letters = 'a'..'j';

		println letters;

		def numbers = 1..<10;

		println numbers;

		def sb= new StringBuffer()
		[1:'a', 2:'b', 3:'c'].each
		{ k, v->
			sb<< "$k:$v, "
		}
		assert sb.toString() == '1:a, 2:b, 3:c, '
	}

	@Test
	public void testSet()
	{
		def a = [1, 3, 3, 38, 322, 22];
		Set bSet = a;

		bSet.each
		{ print "$it, "; };

		println "";
		bSet.each
		{ print "$it, "; };

		def b = a.unique();

		println b;
	}

	@Test
	public void testListSort()
	{
		def list1 = [2, 3,, 4, 5, 8];

		list1.sort
		{ Math.random() };

		println list1
	}

	@Test
	public void testMapOperator()
	{
		def map = ['a':1, 'b':2];

		map.each
		{ key, value ->
			map[key] = value * 2
		}

		assert map == ['a':2, 'b':4]
		
		def cd = "varCd"
		def mapb = [(cd):"cdv", cd:"noBraceCD"];
		println mapb
		
	}
	@Test
	public void testMapGroupBy()
	{
		def userDict = [
			[name:'ok', city:'London'],
			[name:'Alice',city:'London'],
			[name:'Bob',city:'La'],
			[name:'Cate',city:'ShangH'],
			[name:'David',city:'ShangH'],
			[name:'Eson',city:'ShangHa']
		];

		def gByCity =  userDict.groupBy
		{ it.city }

		println gByCity
		println gByCity['La']
	}

	@Test
	public void testRangeFromTo()
	{
		def a = 1..50
		println a.from
		println a.to
	}
}
