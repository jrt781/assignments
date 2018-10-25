package com.jrtyler.assignments.model

import java.io.Serializable
import java.util.*

class Time(var hour: Int = Calendar.getInstance().get(Calendar.HOUR) + 1,
		   var minute: Int = 0,
		   var meridian: Meridian
		   		= if ((Calendar.getInstance().get(Calendar.AM_PM) == Calendar.PM
						   && Calendar.getInstance().get(Calendar.HOUR) != 11)
				   || (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM
						   && Calendar.getInstance().get(Calendar.HOUR) == 11)
				   ) {
					   Meridian.PM
				   } else {
					   Meridian.AM
				   }
			)
	: Comparable<Time>, Serializable {

	override fun compareTo(other: Time): Int {
		if (this.meridian == Meridian.AM && other.meridian == Meridian.PM) return -1
		if (this.meridian == Meridian.PM && other.meridian == Meridian.AM) return 1
		if (this.hour == 12) return -1
		if (other.hour == 12) return 1
		if (this.hour < other.hour) return -1
		if (this.hour > other.hour) return 1
		if (this.minute < other.minute) return -1
		if (this.minute > other.minute) return 1
		return 0
	}

	override fun toString(): String {
		var meridianString = "AM"
		if (meridian == Meridian.PM) meridianString = "PM"

		var minuteString = minute.toString()
		if (minute < 10) minuteString = "0$minuteString"

		return "$hour:$minuteString $meridianString"
	}

}