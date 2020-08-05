package com.wickcentral.utils.datatime;

import java.util.concurrent.TimeUnit;

public class TimeUtils {
	
	
	public static final String timeElapsedInSeconds(long timeInNanoSec) {
		return String.format("%,d seconds", TimeUnit.SECONDS.convert(timeInNanoSec, TimeUnit.NANOSECONDS)) ;
	}
	
	public static final String timeElapsedInMilliSeconds(long timeInNanoSec) {
		return String.format("%,d milliseconds", TimeUnit.MILLISECONDS.convert(timeInNanoSec, TimeUnit.NANOSECONDS)) ;
	}

}
