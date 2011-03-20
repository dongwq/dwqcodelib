package com.dongwq.webservice

import groovyx.net.ws.WSServer

//class ServerEndTest
//{
//	@Test
//	public void testWSServer()
//	{
//		
//	}
//}

def server = new WSServer()

server.setNode("com.dongwq.webservice.MathService", "http://localhost:6980/MathService")

server.start()