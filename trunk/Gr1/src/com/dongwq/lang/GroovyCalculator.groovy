package com.dongwq.lang

import  groovy.swing. *
import  javax.swing. *
import  java.awt. *
/* 
 * Copyright 2007 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 


/** 
 * Groovy Calculator for studying SwingBuilder
 * 
 *  @author  Daniel Sun(realbluesun@hotmail.com)
 *
 *  @since  0.1
 */ 

class  GroovyCalculator
{
	SwingBuilder swing  =   new  SwingBuilder()
	JFrame frame
	def toolkit  =  Toolkit.getDefaultToolkit()
	def screenSize  =  toolkit.getScreenSize()
	
	
	def WIDTH  =   320 
	def HEIGHT  =   200 
	int  X  =  (screenSize.width  -  WIDTH)  /   2 
	int  Y  =  (screenSize.height  -  HEIGHT)  /   2 
	
	boolean  flag  =   false 
	
	private   void  run()
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
		frame  =  swing.frame(title:  ' Demo ' ,
				size: [WIDTH, HEIGHT],
				location: [X, Y],
				defaultCloseOperation: javax.swing.WindowConstants.DISPOSE_ON_CLOSE)
		{
			
			
			
			panel(layout:  new  BorderLayout())
			{
				textField(id:  " expr " , constraints: BorderLayout.NORTH)
				
				panel(constraints: BorderLayout.CENTER)
				{
					tableLayout
					{
						tr
						{
							td
							{
								button(text: " 7 " )
								{
									action(getAction( " 7 " ))
								}
							}
							td
							{
								button(text: " 8 " )
								{
									action(getAction( " 8 " ))
								}
							}
							td
							{
								button(text: " 9 " )
								{
									action(getAction( " 9 " ))
								}
							}
							td
							{
								button(text: " / " )
								{
									action(getAction( " / " ))
								}
							}
						}
						tr
						{
							td
							{
								button(text: " 4 " )
								{
									action(getAction( " 4 " ))
								}
							}
							td
							{
								button(text: " 5 " )
								{
									action(getAction( " 5 " ))
								}
							}
							td
							{
								button(text: " 6 " )
								{
									action(getAction( " 6 " ))
								}
							}
							td
							{
								button(text: " * " )
								{
									action(getAction( " * " ))
								}
							}
						}
						tr
						{
							td
							{
								button(text: " 1 " )
								{
									action(getAction( " 1 " ))
								}
							}
							td
							{
								button(text: " 2 " )
								{
									action(getAction( " 2 " ))
								}
							}
							td
							{
								button(text: " 3 " )
								{
									action(getAction( " 3 " ))
								}
							}
							td
							{
								button(text: " - " )
								{
									action(getAction( " - " ))
								}
							}
						}
						tr
						{
							td
							{
								button(text: " 0 " )
								{
									action(getAction( " 0 " ))
								}
							}
							td
							{
								button(text: " = " )
								{
									action(name: " = " , closure:  this .&eval)
								}
							}
							td
							{
								button(text: " C " )
								{
									action(name: " C " , closure:  this .&clear)
								}
							}
							td
							{
								button(text: " + " )
								{
									action(getAction( " + " ))
								}
							}
						}
					}
				}
			}
		}
		
		swing.expr.setEditable( false )
		swing.expr.setHorizontalAlignment(JTextField.RIGHT)
		swing.expr.setBackground(Color.WHITE)
		
		frame.pack()
		frame.setResizable( false )
		frame.setVisible( true )
	}
	
	private   void  append(EventObject evt  =   null )
	{
		if  (flag)
		{
			swing.expr.text  =   "" 
			flag  =   false
		}
		
		String name  =  evt.source.text
		swing.expr.text  <<=  name
		swing.expr.text  =  swing.expr.text.replaceAll( " // " ,  " / " )
	}
	
	private   void  clear(EventObject evt  =   null )
	{
		swing.expr.text  =   ""
	}
	
	private   void  eval(EventObject evt  =   null )
	{
		String expr  =  swing.expr.text
		
		def b =   new  Binding()
		def conf = new  org.codehaus.groovy.control.CompilerConfiguration()
		conf.setSourceEncoding( ' unicode ' )
		def groovyShell  =   new  GroovyShell(b,conf)
		
		try
		{
			swing.expr.text  =  groovyShell.evaluate(expr)
		}  catch  (Throwable t)
		{
			swing.expr.text  =   " invalid expression! " 
			flag  =   true
		}
	}
	
	def getAction(name)
	{
		return  swing.action(name:name, closure:  this .& append)
	}
	
	static   void  main(args)
	{
		new  GroovyCalculator().run()
	}
} 