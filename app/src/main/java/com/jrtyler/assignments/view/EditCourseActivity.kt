package com.jrtyler.assignments.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.jrtyler.assignments.R
import com.jrtyler.assignments.model.ClientRootModel
import com.jrtyler.assignments.model.Color
import com.jrtyler.assignments.model.Course
import kotlinx.android.synthetic.main.activity_edit_course.*
import kotlinx.android.synthetic.main.upcoming_assignment_list_item.*

class EditCourseActivity : AppCompatActivity() {
	
	lateinit var course: Course
	lateinit var editedCourse: Course
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_edit_course)
		
		val existingAssignment = intent.getBooleanExtra(EDIT_EXISTING_COURSE_KEY, false)
		if (existingAssignment) {
			course = intent.getSerializableExtra(COURSE_KEY) as Course
			title = Html.fromHtml("<font color='#FFFFFF'>Edit Course</font>")
		} else {
			course = Course("")
			title = Html.fromHtml("<font color='#FFFFFF'>Create Course</font>")
			save_btn.text = "Create"
		}
		
		editedCourse = Course(course)
		
		// Set the status of each UI element based on the course
		name_et.setText(editedCourse.name)
		abbrev_et.setText(editedCourse.abbrev)
		color_et.setText(editedCourse.color.toString())
		
		// Set response listeners for each UI element
		name_et.addTextChangedListener(object: TextWatcher {
			override fun afterTextChanged(s: Editable) {}
			override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
			override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
				editedCourse.name = s.toString()
			}
		})
		
		abbrev_et.addTextChangedListener(object: TextWatcher {
			override fun afterTextChanged(s: Editable) {}
			override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
			override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
				editedCourse.abbrev = s.toString()
			}
		})
		
		color_et.setOnTouchListener { v, event ->
			if (event.action == MotionEvent.ACTION_UP) {
				val popupMenu = PopupMenu(this, v)
				
				popupMenu.menu.add("Blue")
				popupMenu.menu.add("Red")
				popupMenu.menu.add("Yellow")
				popupMenu.menu.add("Green")
				
				popupMenu.setOnMenuItemClickListener {item: MenuItem? ->
					
					editedCourse.color = when(item?.title) {
						"Blue" -> Color.BLUE
						"Red" -> Color.RED
						"Green" -> Color.GREEN
						"Yellow" -> Color.YELLOW
						else -> Color.RED
					}
					
					color_et.setText(editedCourse.color.toString())
					
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
		
		save_btn.setOnClickListener { _: View ->
			ClientRootModel.addCourse(editedCourse)
			finish()
		}
		
		cancel_btn.setOnClickListener{ _: View ->
			finish()
		}
	}
	
	companion object {
		private const val COURSE_KEY = "ASSIGNMENT"
		private const val EDIT_EXISTING_COURSE_KEY = "EDIT_EXISTING_ASSIGNMENT"
		
		fun newIntent(context: Context, course: Course?): Intent {
			val intent = Intent(context, EditCourseActivity::class.java)
			intent.putExtra(COURSE_KEY, course)
			intent.putExtra(EDIT_EXISTING_COURSE_KEY, true)
			return intent
		}
		
		fun newIntent(context: Context): Intent {
			val intent = Intent(context, EditCourseActivity::class.java)
			intent.putExtra(EDIT_EXISTING_COURSE_KEY, false)
			return intent
		}
	}
}
