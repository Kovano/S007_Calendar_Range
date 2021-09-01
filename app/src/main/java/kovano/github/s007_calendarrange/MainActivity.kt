package kovano.github.s007_calendarrange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

/** 1. Не забыть проверить в gradle на уровне проекта наличие
allprojects {
    repositories {
//        \/ вот тут указание на гугл обязательно должно быть: для библиотеки материалов дизайна гугла material.io
        google()
        ...
    }
}
*/

/** 2. И не забыть указать в gradle на уровне модуля
dependencies {
  ...
//   \/ и вот тут вот библиотеку подключаю для datePicker, уже подключена
implementation 'com.google.android.material:material:1.4.0'
}
*/

class MainActivity : AppCompatActivity() {

//Объявление переменных для связи с ID элементов UI
    private lateinit var naghataBtn1 : Button
    private lateinit var singleText : TextView
    private lateinit var text2 : TextView
    private lateinit var naghataBtn2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        naghataBtn1 = findViewById(R.id.btn1)//Связь переменной и кнопки вызова выбора одного дня
        singleText = findViewById(R.id.textSingleDate)//Текст с выбранным одним днем

        naghataBtn2 = findViewById(R.id.btn2)//Связь переменной и кнопки вызова диалога выбора диапазона
        text2 = findViewById(R.id.tv_text_result)//Текст с выбранным диапазоном дат


//Объявление (малого) диалога выбора (одной) даты
        val smallDialog : MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker() ;
        smallDialog.setTitleText("Выберите дату")
        val littlePicker : MaterialDatePicker<*> = smallDialog.build()

//Слушатель нажатия для вызова (малого) диалого выбора (одной) даты, должен быть обязательно
//после объявления переменной littlePicker
        naghataBtn1.setOnClickListener {
            littlePicker.show(supportFragmentManager, littlePicker.toString())
        }
//Перелив выбранной (одной) даты в текст
        littlePicker.addOnPositiveButtonClickListener {
            singleText.text = ("Выбранная одна дата "+littlePicker.headerText)
        }

//Слушатель нажатия для вызова (большого) диалога выбора диапазона дат
        naghataBtn2.setOnClickListener {
            dialogDiapazonaDate()
        }
    }

// объявление функции диалога выбора диапазона дат

    private fun dialogDiapazonaDate() {
        val bigDateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Выберите диапазон дат")
            .build()

        bigDateRangePicker.show(
            supportFragmentManager, "date_range_pucker"
        )

//Перелив выбранного диапазона дат в переменные
        bigDateRangePicker.addOnPositiveButtonClickListener { bigDatePicked ->
            val startBigDate = bigDatePicked.first
            val finishBigDate = bigDatePicked.second

            /*Toast.makeText(this, //результат внезапно в единицах Long
            "$startBigDate $finishBigDate",
            Toast.LENGTH_SHORT).show()*/

            if (startBigDate != null && finishBigDate != null){

            text2.text = " Старт диапазона " + converterLongToDate(startBigDate) +
            "\n Финиш диапазона " + converterLongToDate(finishBigDate)
            }
        }

    }
//объявление функции конвертирующей [Long от календарика] в [некий удобный для человека формат даты]
    private fun converterLongToDate(time:Long):String {
        val date1 = Date(time)
        val format1 = SimpleDateFormat(
            "dd.MM.yyyy", //можно и так "dd-MM-yyyy HH:mm"
            Locale.getDefault()
        )
        return format1.format(date1)
    }
}