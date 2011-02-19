package com.dongwq.lang


names = ["Ted", "Fred", "Jed", "Ned"]
System.out.println(names)

shortNames = names.findAll
{ it.size() <= 3}

System.out.println( shortNames.size() )

shortNames.each
{ println it; };


println System.getProperty("user.dir");