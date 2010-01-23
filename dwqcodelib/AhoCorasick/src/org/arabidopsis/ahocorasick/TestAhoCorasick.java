package org.arabidopsis.ahocorasick;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

/**
 * Junit test cases for AhoCorasick.
 */

public class TestAhoCorasick extends TestCase
{

	private static  AhoCorasick tree;

	static 
	{
		tree = new AhoCorasick();
	}

	public void testConstruction()
	{
		tree.add("hello".getBytes(), "hello".getBytes());
		tree.add("hi".getBytes(), "hi".getBytes());
		tree.prepare();

		State s0 = tree.getRoot();
		State s1 = s0.get((byte) 'h');
		State s2 = s1.get((byte) 'e');
		State s3 = s2.get((byte) 'l');
		State s4 = s3.get((byte) 'l');
		State s5 = s4.get((byte) 'o');
		State s6 = s1.get((byte) 'i');

		assertEquals(s0, s1.getFail());
		assertEquals(s0, s2.getFail());
		assertEquals(s0, s3.getFail());
		assertEquals(s0, s4.getFail());
		assertEquals(s0, s5.getFail());
		assertEquals(s0, s6.getFail());

		assertEquals(0, s0.getOutputs().size());
		assertEquals(0, s1.getOutputs().size());
		assertEquals(0, s2.getOutputs().size());
		assertEquals(0, s3.getOutputs().size());
		assertEquals(0, s4.getOutputs().size());
		assertEquals(1, s5.getOutputs().size());
		assertEquals(1, s6.getOutputs().size());

		assertTrue(s6 != null);
	}

	public void testExample()
	{
		tree.add("he".getBytes(), "he".getBytes());
		tree.add("she".getBytes(), "she".getBytes());
		tree.add("his".getBytes(), "his".getBytes());
		tree.add("hers".getBytes(), "hers".getBytes());
		assertEquals(10, tree.getRoot().size());
		tree.prepare(); // after prepare, we can't call size()
		State s0 = tree.getRoot();
		State s1 = s0.get((byte) 'h');
		State s2 = s1.get((byte) 'e');

		State s3 = s0.get((byte) 's');
		State s4 = s3.get((byte) 'h');
		State s5 = s4.get((byte) 'e');

		State s6 = s1.get((byte) 'i');
		State s7 = s6.get((byte) 's');

		State s8 = s2.get((byte) 'r');
		State s9 = s8.get((byte) 's');

		assertEquals(s0, s1.getFail());
		assertEquals(s0, s2.getFail());
		assertEquals(s0, s3.getFail());
		assertEquals(s0, s6.getFail());
		assertEquals(s0, s8.getFail());

		assertEquals(s1, s4.getFail());
		assertEquals(s2, s5.getFail());
		assertEquals(s3, s7.getFail());
		assertEquals(s3, s9.getFail());

		assertEquals(0, s1.getOutputs().size());
		assertEquals(0, s3.getOutputs().size());
		assertEquals(0, s4.getOutputs().size());
		assertEquals(0, s6.getOutputs().size());
		assertEquals(0, s8.getOutputs().size());
		assertEquals(1, s2.getOutputs().size());
		assertEquals(1, s7.getOutputs().size());
		assertEquals(1, s9.getOutputs().size());
		assertEquals(2, s5.getOutputs().size());
	}

	public void testStartSearchWithSingleResult()
	{
		tree.add("apple".getBytes(), "apple".getBytes());
		tree.prepare();
		SearchResult result =
				tree.startSearch("washington cut the apple tree".getBytes());
		assertEquals(1, result.getOutputs().size());
		assertEquals("apple",
				new String((byte[])
				result.getOutputs().iterator().next()));
		assertEquals(24, result.getLastIndex());
		assertEquals(null, tree.continueSearch(result));
	}

	public void testStartSearchWithAdjacentResults()
	{
		tree.add("john".getBytes(), "john".getBytes());
		tree.add("jane".getBytes(), "jane".getBytes());
		tree.prepare();
		SearchResult firstResult =
				tree.startSearch("johnjane".getBytes());
		SearchResult secondResult =
				tree.continueSearch(firstResult);
		assertEquals(null, tree.continueSearch(secondResult));
	}

	public void testStartSearchOnEmpty()
	{
		tree.add("cipher".getBytes(), new Integer(0));
		tree.add("zip".getBytes(), new Integer(1));
		tree.add("nought".getBytes(), new Integer(2));
		tree.prepare();
		SearchResult result = tree.startSearch("".getBytes());
		assertEquals(null, result);
	}

	public void testMultipleOutputs()
	{
		tree.add("x".getBytes(), "x");
		tree.add("xx".getBytes(), "xx");
		tree.add("xxx".getBytes(), "xxx");
		tree.prepare();

		SearchResult result = tree.startSearch("xxx".getBytes());
		assertEquals(1, result.getLastIndex());
		assertEquals(new HashSet(Arrays.asList(new String[] { "x" })),
				result.getOutputs());

		result = tree.continueSearch(result);
		assertEquals(2, result.getLastIndex());
		assertEquals(new HashSet(Arrays.asList(new String[] { "xx", "x" })),
				result.getOutputs());

		result = tree.continueSearch(result);
		assertEquals(3, result.getLastIndex());
		assertEquals(new HashSet(Arrays
				.asList(new String[] { "xxx", "xx", "x" })),
				result.getOutputs());

		assertEquals(null, tree.continueSearch(result));
	}

	public void testIteratorInterface()
	{
		tree.add("moo".getBytes(), "moo");
		tree.add("one".getBytes(), "one");
		tree.add("on".getBytes(), "on");
		tree.add("ne".getBytes(), "ne");
		tree.prepare();
		Iterator iter = tree.search("one moon ago".getBytes());

		assertTrue(iter.hasNext());
		SearchResult r = (SearchResult) iter.next();
		assertEquals(new HashSet(Arrays.asList(new String[] { "on" })),
				r.getOutputs());
		assertEquals(2, r.getLastIndex());

		assertTrue(iter.hasNext());
		r = (SearchResult) iter.next();
		assertEquals(new HashSet(Arrays.asList(new String[] { "one", "ne" })),
				r.getOutputs());
		assertEquals(3, r.getLastIndex());

		assertTrue(iter.hasNext());
		r = (SearchResult) iter.next();
		assertEquals(new HashSet(Arrays.asList(new String[] { "moo" })),
				r.getOutputs());
		assertEquals(7, r.getLastIndex());

		assertTrue(iter.hasNext());
		r = (SearchResult) iter.next();
		assertEquals(new HashSet(Arrays.asList(new String[] { "on" })),
				r.getOutputs());
		assertEquals(8, r.getLastIndex());

		assertFalse(iter.hasNext());

		try
		{
			iter.next();
			fail();
		} catch (NoSuchElementException e)
		{
		}

	}

	/**
	 * 实现测试：串匹配 和词频统计功能
	 */
	public  void largerTextExample()
	{
		String text = "你好abc,ac,abc,def,ac,okt, ac,dfdfe, ac , what is it 你好啊,bc";
		String[] terms = {"你好","ac",	"abc", "bc"};
		for (int i = 0; i < terms.length; i++)
		{
			
			tree.add(terms[i].getBytes(), terms[i]);
			System.out.println( terms[i]);
		}
		tree.prepare();

		Set termsThatHit = new HashSet();
		Iterator iter = tree.search(text.getBytes());
		
//		统计词频
		Map<String, Integer> freqCount = new HashMap<String, Integer>();
		for (; iter.hasNext();)
		{
			SearchResult result = (SearchResult) iter.next();
			Set set = result.getOutputs();
			System.out.println(set);
			for(Iterator it = set.iterator();it.hasNext();)
			{
				String str = (String)it.next();
				if( freqCount.get(str) == null)
					freqCount.put(str, 1);
				else
					freqCount.put(str, freqCount.get(str)+1);
			}
		}
		for(String key: freqCount.keySet())
		{
			System.out.println( "key=" + key + ", freq="+ freqCount.get(key) );
		}
		
	}
	public static void main(String[]args)
	{
		TestAhoCorasick tac = new TestAhoCorasick();
		tac.largerTextExample();
	}
}
