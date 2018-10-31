package com.jrtyler.assignments.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.jrtyler.assignments.R
import com.jrtyler.assignments.model.*
import kotlinx.android.synthetic.main.date_list_item.view.*
import kotlinx.android.synthetic.main.upcoming_assignment_list_item.view.*

class CourseUpcomingAdapter (private val context: Context, private val course: Course)
	: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	
	companion object {
		const val DATE_TYPE = 0
		const val ASSIGNMENT_TYPE = 1
		const val ERROR_TYPE = 2
	}
	
	var itemsNotCompleted : ArrayList<Any> = ArrayList()
	
	init {
		updateItems()
	}
	
	private fun updateItems() {
		itemsNotCompleted.clear()
		
		val upcomingDateMap = ClientRootModel.getAllAssignmentsNotCompletedGroupedByDateForCourse(course.id)
		
		for (date in upcomingDateMap.keys) {
			itemsNotCompleted.add(date)
			for (assignment in upcomingDateMap[date]!!) {
				itemsNotCompleted.add(assignment)
			}
		}
		notifyDataSetChanged()
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		when (viewType) {
			DATE_TYPE -> return UpcomingDateViewHolder(
				LayoutInflater.from(context)
					.inflate(R.layout.date_list_item, parent, false)
			)
			ASSIGNMENT_TYPE -> return UpcomingAssignmentViewHolder(
				LayoutInflater.from(context)
					.inflate(R.layout.upcoming_assignment_list_item, parent, false))
		}
		return UpcomingAssignmentViewHolder(
			LayoutInflater.from(context)
				.inflate(R.layout.upcoming_assignment_list_item, parent, false))
	}
	
	override fun getItemCount(): Int {
		return itemsNotCompleted.size
	}
	
	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		when (holder.itemViewType) {
			DATE_TYPE -> {
				val viewHolder0 =  holder as UpcomingDateViewHolder
				viewHolder0.dateTv.text = itemsNotCompleted[position].toString()
			}
			
			ASSIGNMENT_TYPE -> {
				val viewHolder1 = holder as UpcomingAssignmentViewHolder
				viewHolder1.update(position)
			}
		}
	}
	
	override fun getItemViewType(position: Int): Int {
		val item = itemsNotCompleted[position]
		if (item is Date){
			return DATE_TYPE
		} else if (item is Assignment) {
			return ASSIGNMENT_TYPE
		}
		return ERROR_TYPE
	}
	
	// =================================================================================================================
	// === Start multi-select ==========================================================================================
	// =================================================================================================================
	
	private var multiSelect = false
	
	private var selectedItems = ArrayList<Int>()
	
	private val actionModeCallbacks: ActionMode.Callback = object: ActionMode.Callback {
		override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
			multiSelect = true
			val inflater = mode.menuInflater
			inflater.inflate(R.menu.toolbar_cab, menu)
			mode.title = "Mark as Complete"
			
			return true
		}
		
		override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
			// Can be called anytime the CAB is "invalidated"
			// Place code here that you want executed to update your CAB while it's displayed
			// Return true to tell Android that the CAB has been updated
			return false
		}
		
		override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
			// When there are more than one itemsCompleted in the menu, check which one was clicked
			val numCompleted = selectedItems.size
			for (intItem in selectedItems) {
				(itemsNotCompleted[intItem] as Assignment).status = AssignmentStatus.DONE
			}
			updateItems()
			mode.finish()
			
			// Snackbar
			if (numCompleted > 0) {
				val s = if (numCompleted == 1) "" else "s"
				Snackbar.make((context as AppCompatActivity).window.decorView.findViewById(R.id.upcoming_layout),
					"Marked $numCompleted assignment$s as complete",
					Snackbar.LENGTH_LONG).show()
			}
			
			return true
		}
		
		override fun onDestroyActionMode(mode: ActionMode) {
			multiSelect = false
			selectedItems.clear()
			notifyDataSetChanged()
		}
	}
	
	// =================================================================================================================
	// === End multi-select ============================================================================================
	// =================================================================================================================
	
	inner class UpcomingAssignmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		
		private var upcomingAssignmentDueTimeTv: TextView = view.upcoming_assignment_due_time_tv
		
		private var upcomingAssignmentNameTv: TextView = view.upcoming_assignment_name_tv
		
		private var assignment: Assignment? = null
		
		private var card: MaterialCardView = view.assignment_card
		
		private var courseVtv: VerticalTextView = view.course_abbrev_tv
		
		@SuppressLint("PrivateResource")
		private fun selectItem(item: Int) {
			if (multiSelect) {
				if (selectedItems.contains(item)) {
					selectedItems.remove(item)
					card.setBackgroundColor(ContextCompat.getColor(context,
						R.color.cardview_light_background
					))
				} else {
					selectedItems.add(item)
					card.setBackgroundColor(ContextCompat.getColor(context,
						R.color.colorLightTint
					))
				}
			} else {
				val intent = AssignmentDetailsActivity.newIntent(itemView.context,
					this.assignment ?: return, false)
				itemView.context.startActivity(intent)
			}
		}
		
		@SuppressLint("PrivateResource")
		fun update(value: Int) {
			
			this.assignment = itemsNotCompleted[value] as Assignment
			upcomingAssignmentNameTv.text = assignment?.name
			upcomingAssignmentDueTimeTv.text = assignment?.dueTime.toString()
			
			if (selectedItems.contains(value)) {
				card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorLightTint))
			} else {
				card.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_light_background))
			}
			
			courseVtv.visibility = View.GONE
			
			itemView.setOnLongClickListener {view ->
				if (!multiSelect) {
					(view.context as AppCompatActivity).startActionMode(actionModeCallbacks, ActionMode.TYPE_PRIMARY)
				}
				selectItem(value)
				true
			}
			
			itemView.setOnClickListener{ _ -> selectItem(value) }
		}
		
	}
	
	class UpcomingDateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		var dateTv: TextView = view.upcoming_date_tv
	}
}