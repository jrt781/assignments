package com.jrtyler.assignments.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jrtyler.assignments.R
import com.jrtyler.assignments.model.ClientRootModel
import com.jrtyler.assignments.model.Color
import com.jrtyler.assignments.model.Course

import kotlinx.android.synthetic.main.activity_course_details.*
import kotlinx.android.synthetic.main.content_course_details.*

class CourseDetailsActivity : AppCompatActivity() {
	
	private var course: Course? = null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_course_details)
		setSupportActionBar(toolbar)
		
		edit_course_fab.setOnClickListener { _ ->
			startActivity(EditCourseActivity.newIntent(this, course))
		}
		
		course = intent.getSerializableExtra(COURSE_KEY) as? Course
		
		initViews()
	}
	
	override fun onResume() {
		super.onResume()
		
		course = ClientRootModel.getCourse(course?.id)
		initViews()
	}
	
	@SuppressLint("SetTextI18n")
	private fun initViews() {
		course_details_name_tv.text = course?.fullName
		
		val color:Int = when (course?.color) {
			Color.BLUE -> R.color.readableBlue
			Color.RED -> R.color.readableRed
			Color.GREEN -> R.color.readableGreen
			Color.YELLOW -> R.color.readableYellow
			else -> R.color.readableRed
		}
		
		course_details_color.setBackgroundColor(ContextCompat.getColor(this, color))
		
		course_details_notes_tv.text = course?.notes
		
		course_assignments_rv.layoutManager = LinearLayoutManager(this)
		course_assignments_rv.adapter = CourseUpcomingAdapter(this, course!!)
	}
	
	override fun onSupportNavigateUp(): Boolean {
		finish() // close this activity as oppose to navigating up
		return false
	}
	
	companion object {
		private const val COURSE_KEY = "ASSIGNMENT"
		
		fun newIntent(context: Context, course: Course): Intent {
			val intent = Intent(context, CourseDetailsActivity::class.java)
			intent.putExtra(COURSE_KEY, course)
			return intent
		}
	}
	
}
