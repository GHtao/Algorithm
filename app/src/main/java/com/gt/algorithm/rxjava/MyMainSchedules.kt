package com.gt.algorithm.rxjava

import android.os.Handler
import android.os.Looper

/**
 * time 2020/10/22 0022
 * author GT
 */
class MyMainSchedules:MySchedules {

    private val main = Handler(Looper.getMainLooper())
    override fun schedule(runnable: Runnable) {
        main.post(runnable)
    }
}