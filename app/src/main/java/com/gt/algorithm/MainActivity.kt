package com.gt.algorithm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gt.algorithm.rxjava.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyObservable.create(object : MyObservableObSubscribe {
            override fun subscribe(emitter: MyEmitter) {
                println("thread1:${Thread.currentThread().name}")
                emitter.onNext("aaa")
                emitter.onError("error")
            }
        }).map {
                println("thread2:${Thread.currentThread().name}")
                100
            }.subscribeOn(MySchedules.IO)
            .observerOn(MySchedules.MAIN)
            .map {
                println("thread3:${Thread.currentThread().name}")
                2000
            }
            .observerOn(MySchedules.IO)
            .subscribe(object : MyObserver {
                override fun onNext(next: String) {
                    println("thread4:${Thread.currentThread().name}")
                    println("next:$next")
                }

                override fun onError(error: String) {
                    println("error:$error")
                }
            })
    }
}