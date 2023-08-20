package o.akuma.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import o.akuma.a7minutesworkout.databinding.ActivityExerciseBinding

@Suppress("DEPRECATION")
class ExerciseActivity : AppCompatActivity() {

    lateinit var binding: ActivityExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var timePassed = 10



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setSupportActionBar(binding.tbExercise)

        // this ensures the back button appears on the toolbar
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding.tbExercise.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun restProgressBar(){
        binding.progressBar.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding.progressBar.progress = timePassed - restProgress
                binding.tvTimer.text = (timePassed - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"Here now we will start the exercise.",Toast.LENGTH_SHORT)
                    .show()
            }

        }.start()
    }

}