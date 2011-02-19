package com.dongwq.lang

import groovy.sql.Sql

import org.junit.Before;
import org.junit.Test

class GroovySqlTest
{
	def sql;

	@Before
	public void init()
	{
		sql = Sql.newInstance("dbc:oracle:thin:@localhost:1521:ORCL", "scott",
						"tiger", "oracle.jdbc.OracleDriver")
	}

	@Test
	public void testUsage1()
	{

		sql.eachRow("select * from tb_example t")
		{ row ->
			println row.id + "," + row.name + "," + row.description
		}

		def tb_example = sql.dataSet("tb_example");

		//	tb_example.ea

	}
}
