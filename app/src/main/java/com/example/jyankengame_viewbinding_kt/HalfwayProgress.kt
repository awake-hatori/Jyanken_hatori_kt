package com.example.jyankengame_viewbinding_kt

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jyankengame_viewbinding_kt.databinding.HalfwayProgressBinding

class HalfwayProgress : AppCompatActivity() {
    private lateinit var binding: HalfwayProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HalfwayProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSource()
        binding.nextFight.setOnClickListener {
            Result.gameCounter++
            val intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setSource() {
        //何戦目かをセット
        binding.gameCounter.text = "${Result.gameCounter} 戦目だよ"
        //戦績をセット
        val winCount = ScoreInfo.getWinCounter()
        binding.winCount.text = "勝った数: $winCount"
        val loseCount = ScoreInfo.getLoseCounter()
        binding.loseCount.text = "負けた数: $loseCount"
        val drawCount = ScoreInfo.getDrawCounter()
        binding.drawCount.text = "引き分け数: $drawCount"
    }
}