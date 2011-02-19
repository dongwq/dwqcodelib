package com.dongwq.util

import org.junit.Test

class CommonRegex
{

	def isNumber = /[0-9]+(\.[0-9]+)?/
	def isMail = /[a-zA-Z][^@\.]+@[^@\.]+\.[^@\.]+/


	def re = /13[0-9]{9}/


	@Test
	public void testRegex()
	{
		def amail = 'fgp@sina.com'

		def re = /(.*)@(.*)\.(.*)/

		def matcher = (amail =~ re)



		println matcher[0]
	}
}
