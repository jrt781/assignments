package com.jrtyler.assignments.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat
import com.jrtyler.assignments.R
import com.jrtyler.assignments.model.*

import kotlinx.android.synthetic.main.activity_assignment_details.*
import kotlinx.android.synthetic.main.content_assignment_details.*

class AssignmentDetailsActivity : AppCompatActivity() {

    private var assignment: Assignment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment_details)
        setSupportActionBar(toolbar)

        edit_assignment_fab.setOnClickListener { _ ->
            startActivity(EditAssignmentActivity.newIntent(this, assignment))
        }
		
        assignment = intent.getSerializableExtra(ASSIGNMENT_KEY) as? Assignment
		
		val lastDate = assignment?.dueDate
		if (lastDate != null)
			ClientRootModel.dateNotCompletedLastOn = lastDate

        initViews()

        if (ClientRootModel.getAssignment(assignment?.id)?.status == AssignmentStatus.DONE)
            complete_button.text = "Incomplete"

        complete_button.setOnClickListener {
            ClientRootModel.getAssignment(assignment?.id)?.status =
                    if (ClientRootModel.getAssignment(assignment?.id)?.status == AssignmentStatus.DONE)
                        AssignmentStatus.NOT_DONE
                    else
                        AssignmentStatus.DONE
            finish()
        }
        delete_button.setOnClickListener {
            ClientRootModel.removeAssignment(assignment?.id)
            finish()
        }

        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
    }
    
    override fun onResume() {
        super.onResume()
        
        assignment = ClientRootModel.getAssignment(assignment?.id)
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        assignment_details_name_tv.text = assignment?.name

        val dueDateString = assignment?.dueDate.toString()
        val dueTimeString = assignment?.dueTime.toString()
        assignment_details_due_tv.text = "$dueDateString at $dueTimeString"
		
	
		val course: Course? = ClientRootModel.getCourse(assignment?.courseId)
	
		val color:Int = when (course?.color) {
			Color.BLUE -> R.color.readableBlue
			Color.RED -> R.color.readableRed
			Color.GREEN -> R.color.readableGreen
			Color.YELLOW -> R.color.readableYellow
			else -> R.color.readableRed
		}
	
		assignment_details_course_tv.text = course?.toString()
		assignment_details_course_tv.setBackgroundColor(ContextCompat.getColor(this, color))
		assignment_details_course_tv.setOnClickListener {
			startActivity(CourseDetailsActivity.newIntent(this, course!!))
		}

        assignment_details_notes_tv.text = assignment?.notes
    }

    override fun onSupportNavigateUp(): Boolean {
        finish() // close this activity as oppose to navigating up
        return false
    }

    companion object {
        private const val ASSIGNMENT_KEY = "ASSIGNMENT"

        fun newIntent(context: Context, assignment: Assignment): Intent {
            val intent = Intent(context, AssignmentDetailsActivity::class.java)
            intent.putExtra(ASSIGNMENT_KEY, assignment)
            return intent
        }
    }

}
