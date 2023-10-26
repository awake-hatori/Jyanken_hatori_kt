package com.example.jyankengame_viewbinding_kt

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jyankengame_viewbinding_kt.databinding.FinalResultBinding

class FinalResult : AppCompatActivity() {
    private lateinit var binding: FinalResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FinalResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSource()
        binding.toTitle.setOnClickListener {
            //総合スコアを累積
            setOverallScore()
            ScoreInfo.resetInfo()
            val intent = Intent(application, Title::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setSource() {
        //戦績をセット
        val winCount = ScoreInfo.getWinCounter()
        binding.winCount.text = "勝った数: $winCount"
        val loseCount = ScoreInfo.getLoseCounter()
        binding.loseCount.text = "負けた数: $loseCount"
        val drawCount = ScoreInfo.getDrawCounter()
        binding.drawCount.text = "引き分け数: $drawCount"
        //最終結果画像をセット
        val finalResult = getFinalResult(winCount, loseCount)
        if (finalResult == "win") binding.finalResult.setImageResource(R.drawable.charwin)
        if (finalResult == "lose") binding.finalResult.setImageResource(R.drawable.charlose)
        if (finalResult == "draw") binding.finalResult.setImageResource(R.drawable.chardraw)
    }

    private fun getFinalResult(winCount: Int, loseCount: Int): String {
        if (winCount == loseCount) return "draw"
        return if (winCount > loseCount) "win" else "lose"
    }

    private fun setOverallScore() {
        OverallScoreInfo.setOverallWinCnt(ScoreInfo.getWinCounter())
        OverallScoreInfo.setOverallLoseCnt(ScoreInfo.getLoseCounter())
        OverallScoreInfo.setOverallDrawCnt(ScoreInfo.getDrawCounter())
    }
}