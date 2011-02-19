package com.dongwq.jmock.usage;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dongwq.service.UserService;

public class UserServiceJMockTest {
	
	Mockery context = new Mockery();
	UserService userService = null;
	
	@Before
	public void init()
	{
		userService = context.mock(UserService.class);
	}
	
	@Test
	public void testUserServiceSayHello()
	{
		final String message = "dwq  abHelloSuperLeo";
		
		context.checking(new Expectations() {    
            {    
                one(userService).sayHello("HelloSuperLeo");    
                will(returnValue(message));    
            }    
        });    
		
		
        // 测试成功    
        String result = userService.sayHello("HelloSuperLeo");    
   
        // 测试失败    
        // String result = userService.sayHello("fdjsasdfa");    
   
        Assert.assertSame(result, message);    

	}
}

