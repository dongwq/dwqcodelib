package com.dongwq.lang

import com.dongwq.bean.Man


class BasicSyntax01
{
	static main(args)
	{
		println 'good';

		println new Man().intr();

		def frequence = ["the": 5, "hello": 2, "world": 2]

		println frequence;
		
		def a = null;
		// 如果a为“空”（null，空串""，[]，[:]），那么结果为?:之后的那个值; 如果不为“空”，那么结果就是a
		def result = a ?: "default result"
		println result

		println fibo(5);

		print fibo(1);
		def isFirst = true;
		for( i in 2..10)
		{
			print ', ' + fibo(i);
		}
		println fibo(11)

		Man man2 = new Man();
		man2.printSize(null);

		def animals = [
			'ant',
			'bee',
			'cat'
		]; // native list syntax
		
		// 当使用any和every时是要判断 集合中元素是否满足条件
	
		def beforeDog = animals.every{  pet ->
			// closure support
			pet < 'dog' // overloading
			//return pet;
			//println pet
		}
		println beforeDog;

		println animals.join(",");
	}

	static int fibo(int n)
	{
		if( n == 0) return 1;
		else if( n == 1) return 1;
		else return fibo(n - 1) + fibo(n-2);
	}
}

