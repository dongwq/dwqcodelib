package com.dongwq.webservice

import groovyx.net.ws.WSClient

import org.junit.Test

class ClientEndTest {

	@Test
	public void testWSClient()
	{
		import groovyx.net.ws.WSClient
		
		def proxy = new WSClient("http://localhost:6980/MathService?wsdl", this.class.classLoader)
		proxy.initialize() // from 0.5.0
		def result = proxy.add(1.0 as double, 2.0 as double)
		assert (result == 3.0)
		
		println result
		
		result = proxy.square(3.0 as double)
		assert (result == 9.0)
		
		println result
	}
}

