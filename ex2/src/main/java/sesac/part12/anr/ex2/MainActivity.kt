package sesac.part12.anr.ex2

import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var startView: ImageView
    lateinit var pauseView: ImageView
    lateinit var textView: TextView

    var isFirst = true

    lateinit var asyncTask: MyAsyncTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startView = findViewById<ImageView>(R.id.main_startBtn)
        pauseView = findViewById<ImageView>(R.id.main_pauseBtn)
        textView = findViewById<TextView>(R.id.main_textView)
        startView.setOnClickListener {
            if (isFirst) {
                asyncTask.isRun = true
                asyncTask.execute()
                isFirst = false
            } else {
                asyncTask.isRun = true
            }
        }
        pauseView.setOnClickListener {
            asyncTask.isRun = false
        }

        asyncTask = MyAsyncTask()
    }

    inner class MyAsyncTask:  AsyncTask<Void?, Int?, String>() {
        var loopFlag = true
        var isRun = false
        override fun doInBackground(vararg p0: Void?): String {
            var count = 10
            while (loopFlag) {
                SystemClock.sleep(1000)
                if (isRun) {
                    count--
                    publishProgress(count)
                    if (count == 0) {
                        //종료
                        loopFlag = false
                    }
                }
            }
            return "Finish!!!"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            textView.setText(values[0].toString())
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            textView.setText(result)
        }

    }

}