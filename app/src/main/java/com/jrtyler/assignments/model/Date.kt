package com.jrtyler.assignments.model

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import java.util.Date

class Date(var day: Int = Calendar.getInstance().get(Calendar.DATE),
		   var month: Int = Calendar.getInstance().get(Calendar.MONTH) + 1,
		   var year: Int = Calendar.getInstance().get(Calendar.YEAR))
	: Comparable<com.jrtyler.assignments.model.Date>, Serializable {
	override fun compareTo(other: com.jrtyler.assignments.model.Date): Int {
		if (this.year < other.year) return -1
		if (this.year > other.year) return 1
		if (this.month < other.month) return -1
		if (this.month > other.month) return 1
		if (this.day < other.day) return -1
		if (this.day > other.day) return 1
		return 0
	}

	var dayOfWeek: DayOfWeek = DayOfWeek.MONDAY
		get() = calculateDayOfWeek()

	private fun calculateDayOfWeek(): DayOfWeek {
		val c : Calendar = Calendar.getInstance()
		c.set(year, month - 1, day)
		val date: Date = c.time
		val format2 = SimpleDateFormat("EEEE", Locale.US)
		val dayOfWeekString = format2.format(date)
		when (dayOfWeekString) {
			"Monday" -> return DayOfWeek.MONDAY
			"Tuesday" -> return DayOfWeek.TUESDAY
			"Wednesday" -> return DayOfWeek.WEDNESDAY
			"Thursday" -> return DayOfWeek.THURSDAY
			"Friday" -> return DayOfWeek.FRIDAY
			"Saturday" -> return DayOfWeek.SATURDAY
			"Sunday" -> return DayOfWeek.SUNDAY
		}
		return DayOfWeek.MONDAY
	}

	override fun toString(): String {
		val monthString = abbrevMonth(month)
		val dayOfWeekString = fullDayOfWeek(dayOfWeek)
		return "$dayOfWeekString, $monthString $day"
	}
	
	private fun fullDayOfWeek(dayOfWeek: DayOfWeek): String {
		return when(dayOfWeek) {
			DayOfWeek.SUNDAY ->  "Sunday"
			DayOfWeek.MONDAY ->  "Monday"
			DayOfWeek.TUESDAY ->  "Tuesday"
			DayOfWeek.WEDNESDAY ->  "Wednesday"
			DayOfWeek.THURSDAY ->  "Thursday"
			DayOfWeek.FRIDAY ->  "Friday"
			DayOfWeek.SATURDAY ->  "Saturday"
		}
	}
	
	private fun abbrevDayOfWeek(dayOfWeek: DayOfWeek): String {
		return when(dayOfWeek) {
			DayOfWeek.SUNDAY ->  "Sun"
			DayOfWeek.MONDAY ->  "Mon"
			DayOfWeek.TUESDAY ->  "Tues"
			DayOfWeek.WEDNESDAY ->  "Wed"
			DayOfWeek.THURSDAY ->  "Thurs"
			DayOfWeek.FRIDAY ->  "Fri"
			DayOfWeek.SATURDAY ->  "Sat"
		}
	}
	
	private fun fullMonth(month: Int): String {
		return when(month) {
			1 ->  "January"
			2 ->  "February"
			3 ->  "March"
			4 ->  "April"
			5 ->  "May"
			6 ->  "June"
			7 ->  "July"
			8 ->  "August"
			9 ->  "September"
			10 ->  "October"
			11 ->  "November"
			12 ->  "December"
			else -> "ERROR"
		}
	}
	
	private fun abbrevMonth(month: Int): String {
		return when(month) {
			1 ->  "Jan"
			2 ->  "Feb"
			3 ->  "Mar"
			4 ->  "Apr"
			5 ->  "May"
			6 ->  "Jun"
			7 ->  "Jul"
			8 ->  "Aug"
			9 ->  "Sep"
			10 ->  "Oct"
			11 ->  "Nov"
			12 ->  "Dec"
			else -> "ERROR"
		}
	}
	
}