package com.dongwq.astool

import java.nio.file.Path
import java.nio.file.Paths

/**
 * Comment: ??
 * User: DongWQ, Email:dongwqs@gmail.com
 * Date: 11-12-17，下午6:29
 * @version 0.1
 */

String p = new File("").absolutePath

def  f =  new File(p + "/domainN.txt");

f.eachLine {
    if(it) {
        def sArr =  it.split("\\.")
        println sArr[0]
    }
}