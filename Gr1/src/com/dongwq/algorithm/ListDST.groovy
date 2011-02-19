package com.dongwq.algorithm

import org.junit.Before
import org.junit.Test

class ListDST
{
	static class Node
	{
		String name;
		Node nextNode;

		Node(String name)
		{
			this.name = name;
		}

		Node addAndGetNext(Node nextNode)
		{
			this.nextNode = nextNode;
			return nextNode;
		}

		Node addNext(Node nextNode)
		{
			this.nextNode = nextNode;
			return this;
		}
	}

	static printList(Node head)
	{
		Node p = head;
		boolean isFirst = true;
		while(p )
		{
			if(isFirst)
			{
				print p.name;
				isFirst = false;
			}else{
				print "," + p.name
			}
			p = p.nextNode;
		}
		println "";
	}

	static Node reverseList(Node head)
	{
		Node p = head;
		Node p1 = p.nextNode;

		while( p1)
		{
			Node p2 = p1.nextNode;

			p1.nextNode = p;

			p = p1;
			p1 = p2;
		}
		head.nextNode = null;
		head = p;
		return head;
	}

	Node head;

	@Before
	public void init()
	{
		head = new Node("A");
		head.addAndGetNext(new Node("B") ).addAndGetNext(new Node("D")).addAndGetNext(new Node("E") );
		
		print "Original List:".padLeft(25); 
		printList( head);
	}
	
	@Test
	public void testReverseList()
	{
		Node a = reverseList(head);
		
		print "Reverse List:".padLeft(25);
		printList( a);
	}
}

