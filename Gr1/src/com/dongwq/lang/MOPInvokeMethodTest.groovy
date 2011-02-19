package com.dongwq.lang

import org.junit.Test;

class MOPInvokeMethodTest
{
	@Test
	public void testInvokeMethod()
	{
		Foo foo = new Foo();

		//foo.ok();


		Foo.metaClass.invokeMethod =
		{ String name,args1 ->

			def m = Foo.metaClass.getMetaMethod(name,args1)
			def result
			if(m)
				result = m.invoke(delegate,args1)
			else
				result = 'test'
			result
		}
		
		foo.ok();
	}
}

class Foo implements GroovyInterceptable
{
	def foo()
	{
		'foo'
	}

	def invokeMethod(String name,def args)
	{
		println ":invoke, $name(${args.join(',')})"
		def m = Foo.metaClass.getMetaMethod(name,args1)
			def result
			if(m)
				result = m.invoke(delegate,args1)
			else
				result = 'test'
			result
	}

	//	def methodMissing(String name,def args)
	//	{
	//		println ":methodMissing, $name(${args.join(',')})"
	//	}
}

