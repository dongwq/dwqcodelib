package com.dongwq.test;

import org.junit.Test;

import static org.junit.Assert.*;

class PerformorceTest {

	@Test
	public void test1()
	{
		def t1= System.currentTimeMillis()
		7000.times{
			new MyDomainClass(firstName:'fox', secondName:'gem')
		}
		def t2= System.currentTimeMillis()
		println "Creating Domain Class by Named Parameters: ${t2-t1}"
		 
		t1= System.currentTimeMillis()
		7000.times{
			def dc= new MyDomainClass()
			dc.firstName= 'fox'
			dc.secondName= 'gem'
		}
		t2= System.currentTimeMillis()
		println "Creating Domain Class by Default Constructor: ${t2-t1}"
	}
}
 
class MyDomainClass {
    String firstName
    String secondName
 
    static constraints = {
    }
}