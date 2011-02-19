package com.dongwq.lang

import groovy.time.TimeCategory;


use(TimeCategory)
{
	println new Date() + 1.hour + 3.weeks -2.days + 4.weeks
}