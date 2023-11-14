package com.bignerdranch.android.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import java.text.DateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale
import java.util.TimeZone

class DatePickerFragment : DialogFragment() {

    private val args: DatePickerFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dateListener = DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, day: Int ->
            val resultDate = GregorianCalendar(year, month, day).time
            setFragmentResult(REQUEST_KEY_DATE, bundleOf(BUNDLE_KEY_DATE to resultDate))

            // Update the date
            val callingButton = activity?.findViewById<Button>(R.id.crime_date)
            callingButton?.text = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault()).format(resultDate)
        }

        val calendar = Calendar.getInstance()
        calendar.time = args.crimeDate
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDate = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDate
        )

        return datePickerDialog
    }

    companion object {
        const val REQUEST_KEY_DATE = "REQUEST_KEY_DATE"
        const val BUNDLE_KEY_DATE = "BUNDLE_KEY_DATE"
    }
}