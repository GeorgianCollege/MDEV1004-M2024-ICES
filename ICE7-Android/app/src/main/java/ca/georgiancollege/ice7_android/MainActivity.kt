package ca.georgiancollege.ice7_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.ice7_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}