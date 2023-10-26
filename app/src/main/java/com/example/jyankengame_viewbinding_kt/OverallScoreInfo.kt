package com.example.jyankengame_viewbinding_kt

object OverallScoreInfo {
    @JvmStatic
    private var overallWinCnt = 0
    private var overallLoseCnt = 0
    private var overallDrawCnt = 0

    fun getOverallWinCnt(): Int {
        return overallWinCnt
    }

    fun setOverallWinCnt(overallWinCnt: Int) {
        this.overallWinCnt += overallWinCnt
    }

    fun getOverallLoseCnt(): Int {
        return overallLoseCnt
    }

    fun setOverallLoseCnt(overallLoseCnt: Int) {
        this.overallLoseCnt += overallLoseCnt
    }

    fun getOverallDrawCnt(): Int {
        return overallDrawCnt
    }

    fun setOverallDrawCnt(overallDrawCnt: Int) {
        this.overallDrawCnt += overallDrawCnt
    }

    fun resetInfo() {
        overallWinCnt = 0
        overallLoseCnt = 0
        overallDrawCnt = 0
    }
}