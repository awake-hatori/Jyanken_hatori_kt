package com.example.jyankengame_viewbinding_kt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jyankengame_viewbinding_kt.databinding.ResultBinding

class Result : AppCompatActivity() {
    enum class Hand {
        Gu, Ch, Pa
    }

    enum class GameResult(val id: Int) {
        Win(2), Lose(1), Draw(0);
    }

    companion object {
        @JvmStatic
        var gameCounter: Int = 1
        lateinit var storage: Array<StorageInfo?>
    }

    private lateinit var binding: ResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //最初の勝負でストレージを初期化
        val firstFight = 1
        if (gameCounter == firstFight) {
            storage = arrayOfNulls(Select.numOfMatch)
        }
        //ストレージに保存
        val tempStorage =
            StorageInfo(
                ScoreInfo.getWinCounter(),
                ScoreInfo.getLoseCounter(),
                ScoreInfo.getDrawCounter()
            )
        storage[gameCounter - 1] = tempStorage
        //プレイヤーの手をセット
        val intent = intent
        val playerHand = intent.getIntExtra("player_hand", 0)
        if (playerHand == Hand.Gu.ordinal) binding.playerHand.setImageResource(R.drawable.j_gu02)
        if (playerHand == Hand.Ch.ordinal) binding.playerHand.setImageResource(R.drawable.j_ch02)
        if (playerHand == Hand.Pa.ordinal) binding.playerHand.setImageResource(R.drawable.j_pa02)
        //CPUの手をセット
        val cpuHand = intent.getIntExtra("cpu_hand", 0)
        if (cpuHand == Hand.Gu.ordinal) binding.cpuHand.setImageResource(R.drawable.j_gu02)
        if (cpuHand == Hand.Ch.ordinal) binding.cpuHand.setImageResource(R.drawable.j_ch02)
        if (cpuHand == Hand.Pa.ordinal) binding.cpuHand.setImageResource(R.drawable.j_pa02)
        //じゃんけんの結果をセット
        val gameResult = getGameResult(playerHand, cpuHand)
        if (gameResult == GameResult.Draw.id)
            setSource(R.drawable.draw, "引き分け", gameResult)
        if (gameResult == GameResult.Lose.id)
            setSource(R.drawable.lose, "あなたの負け...", gameResult)
        if (gameResult == GameResult.Win.id)
            setSource(R.drawable.win, "あなたの勝ち!!", gameResult)
        //シーン遷移
        if (Select.mode == Select.Mode.BruteForce) {
            if (gameCounter == Select.numOfMatch)
                changeScene(FinalResult::class.java)
            else changeScene(HalfwayProgress::class.java)
        } else {
            if (isFinish()) changeScene(FinalResult::class.java)
            else changeScene(HalfwayProgress::class.java)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        rollbackInfo()
        finish()
    }

    private fun getGameResult(playerHand: Int, cpuHand: Int): Int {
        return (playerHand - cpuHand + 3) % 3
    }

    private fun setSource(imageDrawable: Int, text: String, gameResult: Int) {
        //勝敗画像
        binding.charSrc.setImageResource(imageDrawable)
        //勝敗テキスト
        binding.jyankenResult.text = text
        //戦績を加算
        if (gameResult == GameResult.Draw.id) ScoreInfo.addDrawCounter()
        if (gameResult == GameResult.Lose.id) ScoreInfo.addLoseCounter()
        if (gameResult == GameResult.Win.id) ScoreInfo.addWinCounter()
        //ボタンテキスト
        if (Select.numOfMatch == 1) binding.nextScene.text = "結果発表！"
        else if (gameCounter == Select.numOfMatch) binding.nextScene.text = "結果発表！"
        else binding.nextScene.text = "途中経過へ"
    }

    private fun <T> changeScene(cls: Class<T>) {
        binding.nextScene.setOnClickListener {
            val intent = Intent(application, cls)
            startActivity(intent)
        }
    }

    private fun isFinish(): Boolean {
        val winCounter = ScoreInfo.getWinCounter()
        val loseCounter = ScoreInfo.getLoseCounter()
        val drawCounter = ScoreInfo.getDrawCounter()
        //引き分けが確定したか
        val drawCondition = Select.numOfMatch / 2 + Select.numOfMatch % 2
        if (Select.numOfMatch % 2 == 0 && drawCounter == Select.numOfMatch / 2 ||
            Select.numOfMatch % 2 == 1 && drawCounter == drawCondition
        ) {
            return true
        }
        //全勝負終えたか
        Log.d("Result", "全勝負終えたか：" + (gameCounter == Select.numOfMatch).toString())
        if (gameCounter == Select.numOfMatch) return true
        //勝利が確定したか
        if (Select.numOfMatch - winCounter - drawCounter < winCounter) return true
        //負けが確定したか
        if (Select.numOfMatch - loseCounter - drawCounter < loseCounter) return true
        return false
    }

    private fun rollbackInfo() {
        val beforeInfoIndex = gameCounter - 1
        val tempStorage = storage[beforeInfoIndex]
        tempStorage?.getStorageWinCnt()?.let { ScoreInfo.setWinCounter(it) }
        tempStorage?.getStorageLoseCnt()?.let { ScoreInfo.setLoseCounter(it) }
        tempStorage?.getStorageDrawCnt()?.let { ScoreInfo.setDrawCounter(it) }
    }
}