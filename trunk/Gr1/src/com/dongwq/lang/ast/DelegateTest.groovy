package com.dongwq.lang.ast

class Greeting1{
    def hello(){return "Hello 1!" }
}
class Greeting2{
    def hello(){return "Hello 2!" }
}
class Man{
    @Delegate Greeting1 greeting1
    @Delegate Greeting2 greeting2
}

def man= new Man(greeting1: new Greeting1(),
                 greeting2: new Greeting2())
assert man.hello() == 'Hello 2!'
