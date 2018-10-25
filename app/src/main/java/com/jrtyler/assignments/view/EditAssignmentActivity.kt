package com.jrtyler.assignments.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jrtyler.assignments.R
import com.jrtyler.assignments.model.Assignment
import com.jrtyler.assignments.model.ClientRootModel
import com.jrtyler.assignments.model.Date
import com.jrtyler.assignments.model.Time
import kotlinx.android.synthetic.main.activity_edit_assignment.*

class EditAssignmentActivity : AppCompatActivity() {
	
	lateinit var assignment: Assignment
	lateinit var editedAssignment: Assignment
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_edit_assignment)
		
		supportActionBar!!.title
		
		val existingAssignment = intent.getBooleanExtra(EDIT_EXISTING_ASSIGNMENT_KEY, false)
		if (existingAssignment) {
			assignment = intent.getSerializableExtra(ASSIGNMENT_KEY) as Assignment
			title = Html.fromHtml("<font color='#FFFFFF'>Edit Assignment</font>")
		} else {
			assignment = Assignment("", "", Date(), Time())
			title = Html.fromHtml("<font color='#FFFFFF'>Create Assignment</font>")
//			save_edits_btn.text = "Create"
		}
		
		editedAssignment = Assignment(assignment)
		
		// Set the status of each UI element based on the assignment
		name_et.setText(editedAssignment.name)
		due_date_et.setText(editedAssignment.dueDate.toString())
		due_time_et.setText(editedAssignment.dueTime.toString())
		val strings = Array(ClientRootModel.courses.size) {""}
		var currentCoursePosition= 0
		for (i in 0 until ClientRootModel.courses.size) {
			strings[i] = ClientRootModel.courses[i].abbrev
			if (ClientRootModel.courses[i].id == editedAssignment.courseId) {
				currentCoursePosition = i
			}
		}
		course_spinner.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, strings)
		course_spinner.setSelection(currentCoursePosition)
		notes_et.setText(editedAssignment.notes)
		
		// Set response listeners for each UI element
		name_et.addTextChangedListener(object: TextWatcher {
			override fun afterTextChanged(s: Editable) {}
			override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
			override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
				editedAssignment.name = s.toString()
			}
		})
		
		due_date_et.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
			if (hasFocus) {
				val datePickerFragment = DatePickerFragment()
				val bundle = Bundle()
				bundle.putSerializable("DATE", editedAssignment.dueDate)
				datePickerFragment.arguments = bundle
				datePickerFragment.show(supportFragmentManager, "datePicker")
			} else {
			}
		}
		
		due_time_et.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
			if (hasFocus) {
				val timePickerFragment = TimePickerFragment()
				val bundle = Bundle()
				bundle.putSerializable("TIME", editedAssignment.dueTime)
				timePickerFragment.arguments = bundle
				timePickerFragment.show(supportFragmentManager, "timePicker")
			} else {
			}
		}

		course_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
				val item = parent.getItemAtPosition(pos) as String
				for (course in ClientRootModel.courses)
					if (course.abbrev == item)
						editedAssignment.courseId = course.id
			}
			override fun onNothingSelected(parentView: AdapterView<*>) {}
		}
		
		
		
		
		
		
		
		
		
		
		

		notes_et.addTextChangedListener(object: TextWatcher {
			override fun afterTextChanged(s: Editable) {}
			override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
			override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
				editedAssignment.notes = s.toString()
			}
		})

		save_btn.setOnClickListener { _: View ->
			ClientRootModel.addAssignment(editedAssignment)
			ClientRootModel.dateNotCompletedLastOn = editedAssignment.dueDate
			finish()
		}

		cancel_btn.setOnClickListener{ _: View ->
			finish()
		}
	}
	
	companion object {
		private const val ASSIGNMENT_KEY = "ASSIGNMENT"
		private const val EDIT_EXISTING_ASSIGNMENT_KEY = "EDIT_EXISTING_ASSIGNMENT"
		
		fun newIntent(context: Context, assignment: Assignment?): Intent {
			val intent = Intent(context, EditAssignmentActivity::class.java)
			intent.putExtra(ASSIGNMENT_KEY, assignment)
			intent.putExtra(EDIT_EXISTING_ASSIGNMENT_KEY, true)
			return intent
		}
		
		fun newIntent(context: Context): Intent {
			val intent = Intent(context, EditAssignmentActivity::class.java)
			intent.putExtra(EDIT_EXISTING_ASSIGNMENT_KEY, false)
			return intent
		}
	}
}
