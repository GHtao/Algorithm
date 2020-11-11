package com.gt.algorithm.rxjava


/**
 * time 2020/10/22 0022
 * author GT
 */
class MyCreateObservable(private val source:MyObservableObSubscribe)
    :MyObservable() {


    override fun subscribeActual(observer: MyObserver) {
        val emitter = MyCreateEmitter(observer)
        source.subscribe(emitter)
    }

    class MyCreateEmitter(private val observer: MyObserver):MyEmitter{

        override fun onNext(next: String) {
            observer.onNext(next)
        }

        override fun onError(error: String) {
            observer.onError(error)
        }
    }
}