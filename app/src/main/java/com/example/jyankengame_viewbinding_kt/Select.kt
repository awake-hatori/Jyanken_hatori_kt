package com.example.jyankengame_viewbinding_kt

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.jyankengame_viewbinding_kt.databinding.SelectBinding

class Select : AppCompatActivity() {
    enum class Mode {
        BruteForce, StarCollecting
    }

    companion object {
        @JvmStatic
        var mode = Mode.BruteForce
        var numOfMatch = 1
    }

    private lateinit var binding: SelectBinding

    @SuppressLint("RtlHardcoded", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        switchText()
        setNumOfMatch()
        binding.start.setOnClickListener {
            val intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun switchText() {
        binding.selectMode.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                @SuppressLint("SetTextI18n")
                override fun onProgressChanged(
                    seek: SeekBar?, progress: Int, fromUser: Boolean
                ) {
                    if (progress == 0) {
                        mode = Mode.BruteForce
                        binding.modeName.text = "総当たり戦"
                        binding.rule.text = "1.対戦形式は任意で1～10まで対戦出来ます\n" +
                                "2.対戦は勝敗に問わずカウントします\n" +
                                "3.設定した値まで終了しません\n" +
                                "4.結果は総合的に判定します"
                    } else {
                        mode = Mode.StarCollecting
                        binding.modeName.text = "星取り戦"
                        binding.rule.text = "1.対戦形式は任意で1～10まで対戦出来ます\n" +
                                "2.設定した回戦数の半分以上を満たした場合終了します\n" +
                                "3.設定した回数を達した場合終了します\n" +
                                "4.設定した回数が半分以上があいこだった場合は引き分けてとします"
                    }
                }

                override fun onStartTrackingTouch(seek: SeekBar?) {}
                override fun onStopTrackingTouch(seek: SeekBar?) {}
            })
    }

    private fun setNumOfMatch() {
        binding.seekbar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                @SuppressLint("SetTextI18n")
                override fun onProgressChanged(
                    seek: SeekBar?, progress: Int, fromUser: Boolean
                ) {
                    numOfMatch = progress
                    val min = 1
                    if (progress < min) {
                        seek?.progress = min
                    }
                    binding.count.text = "回数：$numOfMatch"
                }

                override fun onStartTrackingTouch(seek: SeekBar?) {}
                override fun onStopTrackingTouch(seek: SeekBar?) {}
            })
    }
}