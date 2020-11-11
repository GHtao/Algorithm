package com.gt.algorithm.rxjava

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger


/**
 * time 2020/10/22 0022
 * author GT
 */
interface MySchedules {
    companion object{
        val IO = MyIoSchedules()
        val MAIN = MyMainSchedules()
    }

    fun schedule(runnable: Runnable)
}