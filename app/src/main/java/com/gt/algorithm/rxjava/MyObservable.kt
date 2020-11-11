package com.gt.algorithm.rxjava

/**
 * time 2020/10/22 0022
 * author GT
 */
abstract class MyObservable:MyObservableSource {

    companion object{
        fun create(source:MyObservableObSubscribe):MyObservable{
            return MyCreateObservable(source)
        }
    }

    fun observerOn(schedules: MySchedules):MyObservable{
        return MyObserverOnObservable(this,schedules)
    }

    fun subscribeOn(schedules: MySchedules):MyObservable{
        return MySubscribeOnObservable(this,schedules)
    }

    fun map(block:(String)->Int):MyObservable{
        return MyMapObservable(this,block)
    }

    override fun subscribe(observer: MyObserver) {
        subscribeActual(observer)
    }

    protected abstract fun subscribeActual(observer: MyObserver)
}