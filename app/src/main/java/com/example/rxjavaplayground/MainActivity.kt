package com.example.rxjavaplayground

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavaplayground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@MainActivity
        }

        viewModel.topRatingData.observe(this) { topRating ->
            val topRatingOne = topRating[5]?.name
            binding.text.text = topRatingOne // Output : Oreo game
            Log.d("MainActivity", topRatingOne.toString())
        }
    }
}