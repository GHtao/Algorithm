package com.gt.algorithm.rxjava


/**
 * time 2020/10/22 0022
 * author GT
 */
class MyObserverOnObservable(private val source:MyObservable,private val schedules: MySchedules):MyObservable() {

    override fun subscribeActual(observer: MyObserver) {
        val parent = MyObserverOnObserver(observer,schedules)
        source.subscribe(parent)
    }

    private class MyObserverOnObserver(private val downStream:MyObserver,private val schedules: MySchedules):MyObserver{
        override fun onNext(next: String) {
            schedules.schedule(Runnable {
                downStream.onNext(next)
            })
        }

        override fun onError(error: String) {
            schedules.schedule(Runnable {
                downStream.onError(error)
            })
        }
    }
}