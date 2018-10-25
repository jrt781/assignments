package com.jrtyler.assignments.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.widget.DatePicker
import android.widget.EditText
import com.jrtyler.assignments.model.Date

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val date = arguments!!.getSerializable("DATE") as Date

		// Create a new instance of DatePickerDialog and return it
		return DatePickerDialog(activity, this, date.year, date.month - 1, date.day)
	}

	override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
		// Do something with the date chosen by the user
		val date = Date(year = year, month = month + 1, day = day)
		(activity as EditAssignmentActivity).editedAssignment.dueDate = date
		(activity?.currentFocus as EditText).setText(date.toString())
	}

	override fun onDismiss(dialog: DialogInterface?) {
		activity?.currentFocus?.clearFocus()
	}
}