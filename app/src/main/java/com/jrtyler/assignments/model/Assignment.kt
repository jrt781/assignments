package com.jrtyler.assignments.model

import java.io.Serializable
import java.util.UUID
import kotlin.collections.HashSet

data class Assignment(var name: String = "",
					  var courseId: String,
					  var dueDate: com.jrtyler.assignments.model.Date = Date(),
					  var dueTime: Time = Time(),
					  var notes: String = "") : Comparable<Assignment>, Serializable {

	constructor(assignment: Assignment) : this(assignment.name, assignment.courseId,
			assignment.dueDate, assignment.dueTime, assignment.notes) {
		id = assignment.id
	}

	private var tags: MutableSet<Tag> = HashSet()

	var status = AssignmentStatus.NOT_DONE

	var id: String = UUID.randomUUID().toString()
		private set

	fun addTag(tag: Tag) {
		tags.add(tag)
	}

	fun getTags() : MutableSet<Tag> {
		return tags
	}

	override fun compareTo(other: Assignment): Int {
		if (this.dueDate.compareTo(other.dueDate) != 0) return this.dueDate.compareTo(other.dueDate)
		if (this.dueTime.compareTo(other.dueTime) != 0) return this.dueTime.compareTo(other.dueTime)
		if (this.name.compareTo(other.name) != 0) return this.name.compareTo(other.name)
		return 0
	}

	override fun equals(other: Any?): Boolean {
		if (other == null) return false
		if (other !is Assignment) return false
		if (this.dueDate != other.dueDate) return false
		if (this.dueTime != other.dueTime) return false
		if (this.name != other.name) return false

		return true
	}

	override fun hashCode(): Int {
		var result = name.hashCode()
		result = 31 * result + dueDate.hashCode()
		result = 31 * result + dueTime.hashCode()
		result = 31 * result + notes.hashCode()
		result = 31 * result + tags.hashCode()
		result = 31 * result + status.hashCode()
		return result
	}

}