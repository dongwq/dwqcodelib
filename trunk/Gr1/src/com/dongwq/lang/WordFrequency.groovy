package com.dongwq.lang

//在搜索引擎，语音识别等领域常会统计单词的出现频率，下面给出Groovy实现，打印出现频率最高的6个单词以及相应的出现次数:

def content   =  
		"""
    The Java Collections API is the basis   for   all the nice support that Groovy gives you
    through lists and maps. In fact, Groovy not only uses the same abstractions, it
    even works on the very same classes that make up the Java Collections API.
     """

def words  =  content.tokenize()

def wordFrequency  =  [:]

words.each
{
	wordFrequency[it]  =  wordFrequency.get(it,  0 )  +   1
} 

def wordList  =  wordFrequency.keySet().toList()

wordList.sort
{wordFrequency[it]} 

def result  =   ''  

wordList[ - 1 .. - 6 ].each
{
	result  +=  it.padLeft( 12 )  +   " :  "   +  wordFrequency[it]  +   "  \n"
} 

println result  