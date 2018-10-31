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

class EditCourseActivity : AppCompatActivity() {
	
	lateinit var course: Course
	lateinit var editedCourse: Course
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_edit_course)
		
		val existingCourse = intent.getBooleanExtra(EDIT_EXISTING_COURSE_KEY, false)
		if (existingCourse) {
			course = intent.getSerializableExtra(COURSE_KEY) as Course
			title = Html.fromHtml("<font color='#FFFFFF'>Edit Course</font>")
		} else {
			course = Course("")
			title = Html.fromHtml("<font color='#FFFFFF'>Create Course</font>")
			save_btn.text = "Create"
		}
		
		editedCourse = Course(course)
		
		save_btn.isEnabled = editedCourse.abbrev.isNotEmpty()
		
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
				save_btn.isEnabled = s.isNotEmpty()
			}
		})
		
		color_et.setOnTouchListener { v, event ->
			if (event.action == MotionEvent.ACTION_UP) {
				val popupMenu = PopupMenu(this, v)
				
				popupMenu.menu.add(Color.RED.toString())
				popupMenu.menu.add(Color.ORANGE.toString())
				popupMenu.menu.add(Color.YELLOW.toString())
				popupMenu.menu.add(Color.GREEN.toString())
				popupMenu.menu.add(Color.TEAL.toString())
				popupMenu.menu.add(Color.BLUE.toString())
				popupMenu.menu.add(Color.PURPLE.toString())
				popupMenu.menu.add(Color.PINK.toString())
				
				popupMenu.setOnMenuItemClickListener {item: MenuItem? ->
					
					editedCourse.color = when(item?.title) {
						Color.BLUE.toString() -> Color.BLUE
						Color.RED.toString() -> Color.RED
						Color.GREEN.toString() -> Color.GREEN
						Color.YELLOW.toString() -> Color.YELLOW
						Color.PINK.toString() -> Color.PINK
						Color.PURPLE.toString() -> Color.PURPLE
						Color.ORANGE.toString() -> Color.ORANGE
						Color.TEAL.toString() -> Color.TEAL
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
			if (!existingCourse) {
				val intent = CourseDetailsActivity.newIntent(baseContext, editedCourse)
				startActivity(intent)
			}
			finish()
		}
		
		cancel_btn.setOnClickListener{ _: View ->
			finish()
		}
	}
	
	companion object {
		private const val COURSE_KEY = "COURSE"
		private const val EDIT_EXISTING_COURSE_KEY = "EDIT_EXISTING_COURSE"
		
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
