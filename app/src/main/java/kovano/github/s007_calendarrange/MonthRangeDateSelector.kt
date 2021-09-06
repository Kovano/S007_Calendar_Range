package kovano.github.s007_calendarrange

import android.annotation.SuppressLint
import com.google.android.material.datepicker.RangeDateSelector
import java.util.*

@SuppressLint("RestrictedApi")
class MonthRangeDateSelector: RangeDateSelector() {
    override fun select(selection: Long) {
        val selectedDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        selectedDate.timeInMillis = selection

        val selectedDayOfMonth: Int = selectedDate[Calendar.DAY_OF_MONTH]

        val firstDayOfMonth = selectedDate.clone() as Calendar
        firstDayOfMonth.add(Calendar.DAY_OF_MONTH, -selectedDayOfMonth + 1)
        val lowerBound: Long = firstDayOfMonth.timeInMillis


        val lastDayOfMonth = selectedDate.clone() as Calendar
        lastDayOfMonth.add(Calendar.MONTH, 1)
        lastDayOfMonth.add(Calendar.DAY_OF_MONTH, -selectedDayOfMonth)
        val upperBound: Long = lastDayOfMonth.timeInMillis

        super.select(lowerBound)
        super.select(upperBound)
    }

}
