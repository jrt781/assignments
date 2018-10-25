package com.jrtyler.assignments.view

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.text.format.DateFormat
import android.widget.EditText
import android.widget.TimePicker
import com.jrtyler.assignments.model.Meridian
import com.jrtyler.assignments.model.Time

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val time = arguments!!.getSerializable("TIME") as Time

		var hour = time.hour
		if (time.meridian == Meridian.PM)
			hour += 12
		if (time.hour == 12)
			hour -= 12

		// Create a new instance of TimePickerDialog and return it
		return TimePickerDialog(activity, this, hour, time.minute, DateFormat.is24HourFormat(activity))
	}

	override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
		// Do something with the time chosen by the user
		var hour = hourOfDay
		if (hour >= 12) hour -= 12
		if (hour == 0) hour += 12

		val meridian = if (hourOfDay < 12) Meridian.AM else Meridian.PM

		val time = Time(hour, minute, meridian)
		(activity as EditAssignmentActivity).editedAssignment.dueTime = time
		(activity!!.currentFocus as EditText).setText(time.toString())
	}

	override fun onDismiss(dialog: DialogInterface?) {
		activity?.currentFocus?.clearFocus()
	}
}