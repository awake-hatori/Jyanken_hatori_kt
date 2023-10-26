package com.example.jyankengame_viewbinding_kt

object ScoreInfo {
    @JvmStatic
    private var winCounter = 0
    private var loseCounter = 0
    private var drawCounter = 0

    fun addWinCounter() {
        winCounter++
    }

    fun addLoseCounter() {
        loseCounter++
    }

    fun addDrawCounter() {
        drawCounter++
    }

    fun getWinCounter(): Int {
        return winCounter
    }

    fun setWinCounter(winCounter: Int) {
        this.winCounter = winCounter
    }

    fun getLoseCounter(): Int {
        return loseCounter
    }

    fun setLoseCounter(loseCounter: Int) {
        this.loseCounter = loseCounter
    }

    fun getDrawCounter(): Int {
        return drawCounter
    }

    fun setDrawCounter(drawCounter: Int) {
        this.drawCounter = drawCounter
    }

    fun resetInfo() {
        winCounter = 0
        loseCounter = 0
        drawCounter = 0
        Select.numOfMatch = 1
        Result.gameCounter = 1
    }
}