package com.jrtyler.assignments.model

import java.util.*
import kotlin.collections.ArrayList

object ClientRootModel {

	var courses: ArrayList<Course> = ArrayList()
		private set

	private var initialized = false
	
	var dateNotCompletedLastOn: Date = Date()

	private fun checkInit() {
		if (!initialized) {
			ServerRootModel.initTempData()
			courses = ArrayList(ServerRootModel.courses)
			initialized = true
		}
	}

	fun getAssignmentsNotCompleted(): ArrayList<Assignment> {
		checkInit()
		val upcomingDateMap = getAllAssignmentsNotCompletedGroupedByDate()
		val upcomingAssignments = ArrayList<Assignment>()

		for (date in upcomingDateMap.keys) {
			for (assignment in upcomingDateMap[date]!!) {
				upcomingAssignments.add(assignment)
			}
		}

		return upcomingAssignments
	}

	fun getAllAssignmentsNotCompletedGroupedByDate(): TreeMap<Date, TreeSet<Assignment>> {
		checkInit()
		val upcomingDateMap = TreeMap<Date, TreeSet<Assignment>>()
		for (course in courses) {
			for (assignment in course.assignments) {
				if (assignment.status == AssignmentStatus.NOT_DONE) {
					if (!upcomingDateMap.containsKey(assignment.dueDate)) {
						upcomingDateMap[assignment.dueDate] = TreeSet()
					}
					upcomingDateMap[assignment.dueDate]?.add(assignment)
				}
			}
		}
		return upcomingDateMap
	}

	fun getAllAssignmentsCompletedGroupedByDate(): TreeMap<Date, TreeSet<Assignment>> {
		checkInit()
		val completedDateMap = TreeMap<Date, TreeSet<Assignment>>()
		for (course in courses) {
			for (assignment in course.assignments) {
				if (assignment.status == AssignmentStatus.DONE) {
					if (!completedDateMap.containsKey(assignment.dueDate)) {
						completedDateMap[assignment.dueDate] = TreeSet()
					}
					completedDateMap[assignment.dueDate]?.add(assignment)
				}
			}
		}
		return completedDateMap
	}

	fun getCourse(courseId: String?): Course? {
		checkInit()
		for (course in courses) {
			if (course.id == courseId) {
				return course
			}
		}
		return null
	}

	fun getAssignment(assignmentId: String?): Assignment? {
		checkInit()
		for (course in courses) {
			for (assignment in course.assignments) {
				if (assignment.id == assignmentId) {
					return assignment
				}
			}
		}
		return null
	}

	fun removeAssignment(assignmentId: String?) {
		checkInit()
		for (course in courses) {
			for (assignment in course.assignments) {
				if (assignment.id == assignmentId) {
					course.assignments.remove(assignment)
					break
				}
			}
		}
	}

	fun addAssignment(assignment: Assignment) {
		checkInit()
		removeAssignment(assignment.id)

		val course = getCourse(assignment.courseId)
		course?.assignments?.add(assignment)
	}
	
	fun getAllAssignmentsNotCompletedGroupedByDateForCourse(courseId: String): TreeMap<Date, TreeSet<Assignment>> {
		checkInit()
		val upcomingDateMap = TreeMap<Date, TreeSet<Assignment>>()
		
		val course = getCourse(courseId) as Course
		for (assignment in course.assignments) {
			if (assignment.status == AssignmentStatus.NOT_DONE) {
				if (!upcomingDateMap.containsKey(assignment.dueDate)) {
					upcomingDateMap[assignment.dueDate] = TreeSet()
				}
				upcomingDateMap[assignment.dueDate]?.add(assignment)
			}
		}
		return upcomingDateMap
	}
	
}