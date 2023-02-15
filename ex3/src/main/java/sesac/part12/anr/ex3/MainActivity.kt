package sesac.part12.anr.ex3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val backgroundScope = CoroutineScope(Dispatchers.Default+ Job())

    lateinit var button: Button
    lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        resultView = findViewById(R.id.resultView)

        button.setOnClickListener {
            backgroundScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    for(i in 1..2_000_000_000) {
                        sum += i
                    }
                }
                withContext(Dispatchers.Main) {
                    resultView.text = "sum : $sum"
                }
            }
        }
    }
}