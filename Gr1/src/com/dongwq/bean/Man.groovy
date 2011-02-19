package com.dongwq.bean

class Man
{
	def name="董伟强";
	def intr()
	{
		return "hello,$name";
	}
	
	def printSize(obj)
	{ // optional duck typing
		println obj?.size() ;	// safe dereferencing
	}
}