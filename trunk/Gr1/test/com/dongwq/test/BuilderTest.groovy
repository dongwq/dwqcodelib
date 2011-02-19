package com.dongwq.test;
import groovy.xml.MarkupBuilder;

import static org.junit.Assert.*;

def page = new MarkupBuilder();
page.html
{
	head
	{ title "hello"}
	body
	{
		ul
		{
			
			for( count in 1..10)
			{
				li "World $count"
			}
		}
	}
}