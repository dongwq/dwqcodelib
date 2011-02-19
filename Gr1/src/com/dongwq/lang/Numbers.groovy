package com.dongwq.lang

println 2**3

println 4/3
println 4.intdiv( 3)

println '1.23'.replaceAll(/./){ num -> num + 1}
println '1.23'.replaceAll(/\d/){ num -> num.toInteger() + 1}
println '1.23'.replaceAll(/\d+/){ num -> num.toInteger() + 1}