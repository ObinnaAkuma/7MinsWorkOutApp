package o.akuma.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import o.akuma.a7minutesworkout.databinding.ActivityExerciseBinding

@Suppress("DEPRECATION")
class ExerciseActivity : AppCompatActivity() {

    lateinit var binding: ActivityExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var timePassed = 5
    private var exerciseTimePassed = 30

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0


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

        setupRestView()
    }

    private fun setupRestView(){
        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        restProgressBar()
    }

    private fun setupExerciseView(){
        binding.flProgressBar.visibility = View.INVISIBLE
        binding.tvTitle.text = "Exercise Name"
        binding.flExerciseView.visibility = View.VISIBLE

        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        exerciseProgressBar()
    }

    private fun restProgressBar(){
        binding.progressBar.progress = restProgress
        restTimer = object : CountDownTimer(5000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding.progressBar.progress = timePassed - restProgress
                binding.tvTimer.text = (timePassed - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"Here now we will start the exercise.",Toast.LENGTH_SHORT)
                    .show()
                setupExerciseView()
            }

        }.start()
    }

    private fun exerciseProgressBar(){
        binding.progressBarExercise.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding.progressBarExercise.progress = exerciseTimePassed - exerciseProgress
                binding.tvTimerExercise.text = (exerciseTimePassed - exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,
                "30 Seconds are over, go to rest view",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if(restTimer!= null){
            restTimer?.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
    }

}