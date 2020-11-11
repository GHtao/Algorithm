package com.gt.algorithm.rxjava

/**
 * time 2020/10/22 0022
 * author GT
 */
class MySubscribeOnObservable(private val source:MyObservable,private val schedules: MySchedules)
    :MyObservable() {

    override fun subscribeActual(observer: MyObserver) {
        val parent = MySubscribeOnObserver(observer)
        schedules.schedule(MyRunnable(source,parent))
    }

    class MySubscribeOnObserver(private val downStream: MyObserver):MyObserver{
        override fun onNext(next: String) {
            downStream.onNext(next)
        }

        override fun onError(error: String) {
            downStream.onError(error)
        }

    }
    class MyRunnable(private val source: MyObservable,private val observer: MyObserver):Runnable{

        override fun run() {
            source.subscribe(observer)
        }

    }
}