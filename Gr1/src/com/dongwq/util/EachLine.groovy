package com.dongwq.util

//EachLine.groovy源码:

String file="fileText.txt";
//Scanner scanner= new Scanner();

//String file = arg[0];scanner.nextLine().trim();
def num = 0
new File(file).eachLine { line ->
	num++
	println "$num: $line"
}

String dir = "src";

def rootDir = new File(dir);
rootDir.list().each {
	print it
	 }