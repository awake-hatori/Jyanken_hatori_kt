package com.example.jyankengame_viewbinding_kt

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jyankengame_viewbinding_kt.databinding.TitleBinding

class Title : AppCompatActivity() {
    private lateinit var binding: TitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TitleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSource()
        binding.nextScene.setOnClickListener {
            val intent = Intent(application, Select::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("StringFormatMatches")
    private fun setSource() {
        //総合スコアをリセット
        binding.reset.setOnClickListener {
            OverallScoreInfo.resetInfo()
            setSource()
        }
        //総合スコア
        binding.overallScore.text = getString(
            R.string.OverallScore,
            OverallScoreInfo.getOverallWinCnt(),
            OverallScoreInfo.getOverallLoseCnt(),
            OverallScoreInfo.getOverallDrawCnt()
        )
    }
}