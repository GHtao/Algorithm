package com.gt.algorithm.rxjava

import android.util.Log
import java.util.concurrent.locks.LockSupport

/**
 * time 2020/10/22 0022
 * author GT
 */
object RxJavaText {

    @JvmStatic
    fun main(args:Array<String>){
        MyObservable.create(object :MyObservableObSubscribe{
            override fun subscribe(emitter: MyEmitter) {
                println("thread1:${Thread.currentThread().name}")
                emitter.onNext("aaa")
                emitter.onError("error")
            }
        }).subscribeOn(MySchedules.IO)
            .map {
            println("thread2:${Thread.currentThread().name}")
            100
        }.subscribeOn(MySchedules.IO)
            .observerOn(MySchedules.MAIN)
            .subscribe(object :MyObserver{
            override fun onNext(next: String) {
                println("thread3:${Thread.currentThread().name}")
                println("next:$next")
            }

            override fun onError(error: String) {
                println("error:$error")
            }
        })

        LockSupport.parkNanos(1000)
    }
}