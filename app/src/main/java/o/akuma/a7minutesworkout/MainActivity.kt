package o.akuma.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import o.akuma.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.flStart.setOnClickListener {
            val intent = Intent(this, ExerciseActivity ::class.java)
            startActivity(intent)
        }


    }
}