package com.gt.algorithm.rxjava

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

/**
 * time 2020/10/22 0022
 * author GT
 */
class MyIoSchedules:MySchedules {

    private val io = Executors.newCachedThreadPool {
        val index = AtomicInteger(0)
        Thread(it,"my-cache-${index.getAndIncrement()}")
    }

    override fun schedule(runnable: Runnable) {
        io.submit(runnable)
    }
}