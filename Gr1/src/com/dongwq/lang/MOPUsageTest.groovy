package com.dongwq.lang

import org.junit.Test

import com.dongwq.bean.Person

class MOPUsageTest
{
	
	@Test
	public void testUsage1()
	{
		//println "http://thirstyhead.com".toURL().text
	//	println "ipconfig /all".execute().text
	}

	/**
	 * MOP用来快速的访问属性，并构建友好的打印信息
	 */
	@Test
	public void testMOP1()
	{
		Person person = new Person(age:22, name:"dn");
		
		int sName = person.metaClass.properties.name*.size().max();
		//int sType = person.metaClass.properties.type*.size().max();
		
		println sName
		person.metaClass.properties.each
		{
			println "name:$it.name".padRight(sName+8) + ", type:$it.type".padRight( 30 + 2) + ", value:${person."$it.name"}"
		}
	}
	
	/**
	 * MOP用来构造工厂方法来构造对象，或者类似模板方法与工厂方法之间，模板方法调用。也可以使用闭包来灵活实现
	 */
	@Test
	public void testFactoryMethod(){
		
		def a = getObjectByClassName("Person");
		println a.class;
		
		
	}
	def getObjectByClassName(String className)
	{
		Class<?> cls = Class.forName("com.dwq.bean." + className);
		return cls.newInstance();
	}
	
	/**
	 * invokeMethod的重载，是简单的实现动态代理的方法
	 */
	
	
	
	/**
	 * invokeMethod效果的方法定义优先级
	 */
	
	public void testInvokeMethod()
	{
		
	}
}


