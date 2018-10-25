package com.jrtyler.assignments.model

import java.util.*

class Course(var abbrev: String,
			 var name: String = "",
			 var professor: String = "",
			 var color: Color = Color.BLUE)
	: Comparable<Course> {
	
	override fun compareTo(other: Course): Int {
		if (abbrev != other.abbrev) return abbrev.compareTo(other.abbrev)
		if (name != other.name) return name.compareTo(other.name)
		return 0
	}

	val id: String = UUID.randomUUID().toString()

	var assignments: ArrayList<Assignment> = ArrayList()

	var meetings: ArrayList<Meeting> = ArrayList()

	override fun toString(): String {
		return abbrev
	}

	override fun equals(other: Any?): Boolean {
		if (other !is Course) return false
		if (this.id != other.id) return false
		return true
	}

	override fun hashCode(): Int {
		return id.hashCode()
	}

}