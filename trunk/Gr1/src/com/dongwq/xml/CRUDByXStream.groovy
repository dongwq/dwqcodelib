package com.dongwq.xml

import java.io.FileWriter;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

class CRUDByXStream
{
	Person p;
	XStream x;

	@Before
	public void init()
	{
		p = new Person('dong', 'weiqang')

		p.setFax( new PhoneNumber(23,"2344") );
		p.setPhone( new PhoneNumber(131328, "13132845"));

		x = new XStream(  new DomDriver());
		
		//set alias
		x.aliasType("person", Person);
	}

	@Test
	public void testObj2Xml()
	{
		def file = new FileWriter(new File("xml/CRUDByXStream.xml"));

		x.toXML p, file
	}
	
	@Test
	public void testObjAppend2XML()
	{
		
	}
}
