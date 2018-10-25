package com.jrtyler.assignments.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.jrtyler.assignments.R
import com.jrtyler.assignments.model.ClientRootModel
import com.jrtyler.assignments.model.Date
import kotlinx.android.synthetic.main.activity_upcoming.*
import kotlinx.android.synthetic.main.app_bar_upcoming.*
import kotlinx.android.synthetic.main.content_upcoming.*

class UpcomingActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            startActivity(EditAssignmentActivity.newIntent(this))
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        // Creates a vertical layout Manager
        rv_upcoming.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        rv_upcoming.adapter = UpcomingAdapter(this)
		
		ClientRootModel.dateNotCompletedLastOn = Date()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()

        rv_upcoming.adapter = UpcomingAdapter(this)
		
		var position = 0
	
		val upcomingDateMap = ClientRootModel.getAllAssignmentsNotCompletedGroupedByDate()
		val itemsNotCompleted : ArrayList<Any> = ArrayList()
	
		for (date in upcomingDateMap.keys) {
			if (date >= ClientRootModel.dateNotCompletedLastOn) {
				position = itemsNotCompleted.size
				break
			}
			itemsNotCompleted.add(date)
			for (assignment in upcomingDateMap[date]!!) {
				itemsNotCompleted.add(assignment)
			}
		}
		
		rv_upcoming.scrollToPosition(position)

//        if (ClientRootModel.courses.size == 0) {
//            val findCoursesButton = find_courses_btn
//            findCoursesButton.setOnClickListener {
//                val intent = FindCoursesView.newIntent(this)
//                startActivity(intent)
//            }
//
//            val createCourseButton = create_course_btn
//            createCourseButton.setOnClickListener {
//                Toast.makeText(this, "Under Construction!", Toast.LENGTH_SHORT).show()
//            }
//            no_courses_layout.visibility = View.VISIBLE
//
//            fab.visibility = View.GONE
//        } else {
//            no_courses_layout.visibility = View.GONE
//            fab.visibility = View.VISIBLE
//        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.upcoming, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, UpcomingActivity::class.java)
            return intent
        }
    }
}
