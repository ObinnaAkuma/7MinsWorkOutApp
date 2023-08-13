package o.akuma.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import o.akuma.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    lateinit var binding: ActivityExerciseBinding

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
}