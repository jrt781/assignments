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
	
	override fun equals(other: Any?): Boolean {
		if (other == null) return false
		val that = other as com.jrtyler.assignments.model.Date
		if (this.year != that.year) return false
		if (this.month != that.month) return false
		if (this.day != that.day) return false
		return true
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
	
	
	
	companion object {
		
		private fun numDaysOfMonth(month: Int): Int {
			return when(month) {
				1 ->  31
				2 ->  28
				3 ->  31
				4 ->  30
				5 ->  31
				6 ->  30
				7 ->  31
				8 ->  31
				9 ->  30
				10 ->  31
				11 ->  30
				12 ->  31
				else -> 31
			}
		}
		
		fun tomorrow():com.jrtyler.assignments.model.Date {
			val date = com.jrtyler.assignments.model.Date(
				Calendar.getInstance().get(Calendar.DATE) + 1,
				Calendar.getInstance().get(Calendar.MONTH) + 1,
				Calendar.getInstance().get(Calendar.YEAR))
			
			if (date.day > numDaysOfMonth(date.month)) {
				date.day = 1
				date.month++
				if (date.month > 12) {
					date.month = 1
					date.year++
				}
			}
			
			return date
		}
	}
	
}