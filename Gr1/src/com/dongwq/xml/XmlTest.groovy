package com.dongwq.xml;

import static org.junit.Assert.*
import groovy.xml.MarkupBuilder

import org.junit.Test

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy
import com.thoughtworks.xstream.persistence.PersistenceStrategy
import com.thoughtworks.xstream.persistence.XmlArrayList

class XmlTest
{

	@Test
	public void testObj2XML()
	{
		def inputList = [
			new Input(name:'名称',id:'name1'),
			new Input(name:"Dong", id:'ejfef')
		];
		XStream xs = new XStream();
		xs.alias 'input', Input.class

		def input = xs.toXML( inputList );
		println input;

		xs.toXML(inputList, new FileWriter("testXStream.xml") );
		//def inputs = new XmlSlurper().parse(input);

		def markup = new MarkupBuilder();

		markup.persons{
			for( Input inp in inputList)
			{
				person()
				{
					name inp.name
					id inp.id
				}
			}
		}

		print markup.toString()
	}
	@Test
	public void testOK()
	{
		// prepares the file strategy to directory /tmp
		PersistenceStrategy strategy = new FilePersistenceStrategy(new File("tmp"));
		// creates the list:
		List list = new XmlArrayList(strategy);

		// adds four authors
		list.add(new Author("joe walnes"));
		list.add(new Author("joerg schaible"));
		list.add(new Author("mauro talevi"));
		list.add(new Author("guilherme silveira"));

		// adding an extra author
		Author mistake = new Author("mama妈妈");
		list.add(mistake);
	}
}

class Input
{
	def name, id;
}


class Author
{
	private String name;
	public Author(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
}