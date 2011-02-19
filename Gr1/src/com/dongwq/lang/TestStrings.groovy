package com.dongwq.lang

import java.util.regex.Pattern.LastMatch;
/**
 * 
 * GStrings不同于Java strings在于它们允许以${..}的语法嵌入变量。如果一个字符串由双引号或三重引号括起来，并且包含一个非转义符（unescaped）$，它必定是groovy.lang.GString的一个实例；否则，就是java.lang.String的一个实例。

${..}里可以包含任意有效的Groovy表达式，包括方法调用或变量名。只有调用GString的toString方法时（例如，把结果输出到控制台），${..}表达式才被求值。


 */

def firstname='kate';
lastname = 'green';

println firstname * 2;

println firstname - lastname

println firstname.padLeft(20)

def fullname= '$firstname $lastname';
// Multi-line strings
//def twister = '''\
//She sells, 
//sea shellsBy the sea shore'''
//def lines =twister.split('\n')
//assert lines.size() == 2
def address = """
$fullname
 123 
First AveNew York""".trim()
def lines = address.split('\n')
assert lines.size() == 3

def path = /C:\Windows\System32/
println path


def string = 'an apple a day';

println string.toList().unique().sort().join()

words = ['bob', 'alpha', 'rotator', 'omega', 'reviver']
bigPalindromes= words.findAll{w-> w == w.reverse() && w.size() > 5}
println bigPalindromes



