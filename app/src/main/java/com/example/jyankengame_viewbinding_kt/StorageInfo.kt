package com.example.jyankengame_viewbinding_kt

class StorageInfo(winCnt: Int, loseCnt: Int, drawCnt: Int) {
    private var storageWinCnt: Int = winCnt
    private var storageLoseCnt: Int = loseCnt
    private var storageDrawCnt: Int = drawCnt

    fun getStorageWinCnt(): Int {
        return storageWinCnt
    }

    fun getStorageLoseCnt(): Int {
        return storageLoseCnt
    }

    fun getStorageDrawCnt(): Int {
        return storageDrawCnt
    }
}
