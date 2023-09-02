package o.akuma.a7minutesworkout

import android.annotation.SuppressLint
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
    private var timePassed = 10
    private var exerciseTimePassed = 30

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setSupportActionBar(binding.tbExercise)

        // this ensures the back button appears on the toolbar
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseList = Constants.defaultExerciseList()

        //this provides the same behaviour with the back button to the button on the nav
        binding.tbExercise.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()
    }

    private fun setupRestView() {

        binding.flRestView.visibility = View.VISIBLE
        binding.tvTitle.visibility = View.VISIBLE

        //when the rest view comes up, the exercise view becomes invisible and vice versa

        binding.tvExerciseName.visibility = View.INVISIBLE
        binding.flExerciseView.visibility = View.INVISIBLE
        binding.ivImage.visibility = View.INVISIBLE
        binding.tvUpcomingLabel.visibility = View.VISIBLE
        binding.tvUpcomingExerciseName.visibility = View.VISIBLE

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }

        binding.tvUpcomingExerciseName.text =
            exerciseList!![currentExercisePosition + 1].getName()
        restProgressBar()
    }

    @SuppressLint("SetTextI18n")
    private fun setupExerciseView() {
        binding.flRestView.visibility = View.INVISIBLE
        binding.tvTitle.visibility = View.INVISIBLE
        binding.tvUpcomingLabel.visibility = View.INVISIBLE
        binding.tvUpcomingExerciseName.visibility = View.INVISIBLE
        //binding.tvTitle.text = Constants.name.... lets see how this works

        //binding.tvTitle.text = "Exercise Name"
        binding.tvExerciseName.visibility = View.VISIBLE
        binding.flExerciseView.visibility = View.VISIBLE
        binding.ivImage.visibility = View.VISIBLE


        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        binding.ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding.tvExerciseName.text = exerciseList!![currentExercisePosition].getName()


        exerciseProgressBar()
    }

    private fun restProgressBar() {
        binding.progressBar.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding.progressBar.progress = timePassed - restProgress
                binding.tvTimer.text = (timePassed - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@ExerciseActivity,
                    "Here now we will start the exercise.", Toast.LENGTH_SHORT
                )
                    .show()
                currentExercisePosition++
                setupExerciseView()
            }

        }.start()
    }

    private fun exerciseProgressBar() {
        binding.progressBarExercise.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding.progressBarExercise.progress = exerciseTimePassed - exerciseProgress
                binding.tvTimerExercise.text = (exerciseTimePassed - exerciseProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePosition < exerciseList?.size!! - 1) {
                    setupRestView()
                } else {
                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulations You Have Completed " +
                                "The 7 Minutes Workout", Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
    }

}