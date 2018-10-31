package com.jrtyler.assignments.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
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
		
		val existingAssignment = intent.getBooleanExtra(EDIT_EXISTING_ASSIGNMENT_KEY, false)
		if (existingAssignment) {
			assignment = intent.getSerializableExtra(ASSIGNMENT_KEY) as Assignment
			title = Html.fromHtml("<font color='#FFFFFF'>Edit Assignment</font>")
		} else {
			assignment = intent.getSerializableExtra(ASSIGNMENT_KEY) as? Assignment ?: Assignment("", "", Date.tomorrow(), Time())
			title = Html.fromHtml("<font color='#FFFFFF'>Create Assignment</font>")
			save_btn.text = "Create"
		}
		
		editedAssignment = Assignment(assignment)
		val course = (ClientRootModel.getCourse(editedAssignment.courseId) ?: ClientRootModel.courses[0])
		editedAssignment.courseId = course.id
		save_btn.isEnabled = editedAssignment.name.isNotEmpty()
		
		// Set the status of each UI element based on the assignment
		name_et.setText(editedAssignment.name)
		due_date_et.setText(editedAssignment.dueDate.toString())
		due_time_et.setText(editedAssignment.dueTime.toString())
		course_et.setText(course.toString())
		notes_et.setText(editedAssignment.notes)
		
		// Set response listeners for each UI element
		name_et.addTextChangedListener(object: TextWatcher {
			override fun afterTextChanged(s: Editable) {}
			override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
			override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
				editedAssignment.name = s.toString()
				
				save_btn.isEnabled = s.isNotEmpty()
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
		
		course_et.setOnTouchListener { v, event ->
			if (event.action == MotionEvent.ACTION_UP) {
				val popupMenu = PopupMenu(this, v)
				
				for (courseM in ClientRootModel.courses) {
					popupMenu.menu.add(courseM.toString())
				}
				
				popupMenu.setOnMenuItemClickListener {item: MenuItem? ->
					
					for (courseI in ClientRootModel.courses)
						if (courseI.toString() == item?.title)
							editedAssignment.courseId = courseI.id
					
					course_et.setText(ClientRootModel.getCourse(editedAssignment.courseId).toString())
					
					true
				}
				
				popupMenu.show()
				
				popupMenu.setOnDismissListener {
					currentFocus?.clearFocus()
					currentFocus?.clearFocus()
				}
			}
			
			true
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
		
		fun newIntent(context: Context, assignment: Assignment?, existing: Boolean = true): Intent {
			val intent = Intent(context, EditAssignmentActivity::class.java)
			intent.putExtra(ASSIGNMENT_KEY, assignment)
			intent.putExtra(EDIT_EXISTING_ASSIGNMENT_KEY, existing)
			return intent
		}
		fun newIntent(context: Context): Intent {
			val intent = Intent(context, EditAssignmentActivity::class.java)
			intent.putExtra(EDIT_EXISTING_ASSIGNMENT_KEY, false)
			return intent
		}
	}
}
