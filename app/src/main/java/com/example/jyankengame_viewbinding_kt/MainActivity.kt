package com.example.jyankengame_viewbinding_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.jyankengame_viewbinding_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    enum class Hand {
        Gu, Ch, Pa
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.jGu.setOnClickListener {
            toResult(binding.jGu)
        }
        binding.jCh.setOnClickListener {
            toResult(binding.jCh)
        }
        binding.jPa.setOnClickListener {
            toResult(binding.jPa)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (Result.gameCounter == 1) return
        Result.gameCounter--
        finish()
    }

    private fun toResult(imageButton: ImageButton) {
        val intent = Intent(application, Result::class.java)
        setPlayerHand(intent, imageButton)
        intent.putExtra("cpu_hand", getCpuHand())
        startActivity(intent)
    }

    private fun setPlayerHand(intent: Intent, imageButton: ImageButton) {
        if (imageButton == binding.jGu)
            intent.putExtra("player_hand", Hand.Gu.ordinal)
        if (imageButton == binding.jCh)
            intent.putExtra("player_hand", Hand.Ch.ordinal)
        if (imageButton == binding.jPa)
            intent.putExtra("player_hand", Hand.Pa.ordinal)
    }

    private fun getCpuHand(): Int {
        val range = (0..2)
        return range.random()
    }
}