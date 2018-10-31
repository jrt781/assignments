package com.jrtyler.assignments.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.jrtyler.assignments.R
import com.jrtyler.assignments.model.Assignment
import com.jrtyler.assignments.model.ClientRootModel
import com.jrtyler.assignments.model.Color
import com.jrtyler.assignments.model.Course

import kotlinx.android.synthetic.main.activity_course_details.*
import kotlinx.android.synthetic.main.app_bar_course_details.*
import kotlinx.android.synthetic.main.content_course_details.*

class CourseDetailsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	
	private var course: Course? = null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_course_details)
		setSupportActionBar(toolbar)
		
		edit_course_fab.setOnClickListener { _ ->
			startActivity(EditCourseActivity.newIntent(this, course))
		}
		
		new_assignment_fab.setOnClickListener{ _ ->
			startActivity(EditAssignmentActivity.newIntent(this, Assignment(courseId = course?.id ?: ""), false))
		}
		
		val toggle = ActionBarDrawerToggle(
			this, drawer_layout, toolbar,
			R.string.navigation_drawer_open,
			R.string.navigation_drawer_close
		)
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()
		
		nav_view.setNavigationItemSelectedListener(this)
		recalculateCoursesForMenu()
		
		course = intent.getSerializableExtra(COURSE_KEY) as? Course
		
		initViews()
	}
	
	override fun onResume() {
		super.onResume()
		
		course = ClientRootModel.getCourse(course?.id)
		initViews()
	}
	
	private fun recalculateCoursesForMenu() {
		val coursesMenu = nav_view.menu.findItem(R.id.my_courses).subMenu
		coursesMenu.clear()
		for (course in ClientRootModel.courses) {
			val item = coursesMenu.add(course.abbrev)
			item.setIcon(R.drawable.nav_course)
		}
	}
	
	@SuppressLint("SetTextI18n")
	private fun initViews() {
		course_details_name_tv.text = course?.fullName
		
		val color:Int = when (course?.color) {
			Color.BLUE -> R.color.readableBlue
			Color.RED -> R.color.readableRed
			Color.GREEN -> R.color.readableGreen
			Color.YELLOW -> R.color.readableYellow
			Color.PINK -> R.color.readablePink
			Color.PURPLE -> R.color.readablePurple
			Color.ORANGE -> R.color.readableOrange
			Color.TEAL -> R.color.readableTeal
			else -> R.color.readableRed
		}
		
		course_details_color.setBackgroundColor(ContextCompat.getColor(this, color))
		
		course_details_notes_tv.text = course?.notes
		
		course_assignments_rv.layoutManager = LinearLayoutManager(this)
		val adapter = CourseUpcomingAdapter(this, course!!)
		course_assignments_rv.adapter = adapter
		need_assignments_for_course.visibility = if (adapter.itemCount > 0) GONE else VISIBLE
	}
	
	override fun onSupportNavigateUp(): Boolean {
		finish() // close this activity as oppose to navigating up
		return false
	}
	
	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		// Handle navigation view item clicks here.
		when (item.itemId) {
			R.id.nav_upcoming_assignments -> {
				val intent = UpcomingActivity.newIntent(this)
				startActivity(intent)
			}
			R.id.nav_completed_assignments -> {
				val intent = CompletedActivity.newIntent(this)
				startActivity(intent)
			}
			R.id.nav_create_course -> {
				val intent = EditCourseActivity.newIntent(this)
				startActivity(intent)
			}
			else -> {
				val course = ClientRootModel.getCourse(item.title.toString()) ?: return false
				val intent = CourseDetailsActivity.newIntent(this, course)
				startActivity(intent)
			}
		}
		
		drawer_layout.closeDrawer(GravityCompat.START)
		return true
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
