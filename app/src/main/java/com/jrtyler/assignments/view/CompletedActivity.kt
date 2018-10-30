package com.jrtyler.assignments.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.jrtyler.assignments.R
import com.jrtyler.assignments.model.Assignment
import com.jrtyler.assignments.model.ClientRootModel
import com.jrtyler.assignments.model.Date
import kotlinx.android.synthetic.main.activity_completed.*
import kotlinx.android.synthetic.main.app_bar_completed.*
import kotlinx.android.synthetic.main.content_completed.*

class CompletedActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
		
		checkNumCompleted()

        // Creates a vertical layout Manager
        rv_completed.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        rv_completed.adapter = CompletedAdapter(this)
		
		ClientRootModel.dateNotCompletedLastOn = Date()

        nav_view.setNavigationItemSelectedListener(this)
    }
    
    private fun jumpToToday() {
		jumpToDate(Date())
	}
	
	private fun jumpToLastUsedDate() {
		jumpToDate(ClientRootModel.dateNotCompletedLastOn)
	}
	
	private fun jumpToDate(dateJump: Date) {
		var position = 0
		
		val completedDateMap = ClientRootModel.getAllAssignmentsCompletedGroupedByDate()
		val itemsCompleted : ArrayList<Any> = ArrayList()
		
		// iterate in reverse order
		for (date in completedDateMap.keys) {
			val tempList = ArrayList<Assignment>()
			for (assignment in completedDateMap[date]!!) {
				tempList.add(assignment)
			}
			tempList.reverse()
			for (assignment in tempList) {
				itemsCompleted.add(0, assignment)
				position++
			}
			itemsCompleted.add(0, date)
			position++
			if (date < dateJump) {
				position = 0
			}
		}
		
		if (position >= itemsCompleted.size)
			position = itemsCompleted.size - 1
		
		(rv_completed.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position, 0)
	}
	
	private fun checkNumCompleted() {
		if (ClientRootModel.getAllAssignmentsCompletedGroupedByDate().keys.size > 0) {
			no_completed_tv.visibility = GONE
		} else {
			no_completed_tv.visibility = VISIBLE
		}
	}
	
    override fun onResume() {
        super.onResume()

        rv_completed.adapter = CompletedAdapter(this)
		
		jumpToLastUsedDate()
		checkNumCompleted()

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
        menuInflater.inflate(R.menu.completed, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
		return when (item.itemId) {
			R.id.action_today -> {
				jumpToToday()
				true
			}
			R.id.action_mark -> {
				(rv_completed.adapter as CompletedAdapter).startCAB()
				true
			}
			else -> super.onOptionsItemSelected(item)
		}
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

            }
        }
		

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, CompletedActivity::class.java)
            return intent
        }
    }
}
