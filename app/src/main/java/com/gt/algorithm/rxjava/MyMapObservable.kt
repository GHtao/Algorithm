package com.gt.algorithm.rxjava

/**
 * time 2020/10/22 0022
 * author GT
 */
class MyMapObservable(private val source: MyObservable,
                      private val block:(String)->Int):MyObservable() {

    override fun subscribeActual(observer: MyObserver) {
        val emitter = MyMapEmitter(observer,block)
        source.subscribe(emitter)
    }

    class MyMapEmitter(private val downStream:MyObserver,private val block: (String) -> Int):MyObserver{

        override fun onNext(next: String) {
            val mapValue = block(next)
            downStream.onNext(mapValue.toString())
        }

        override fun onError(error: String) {
            downStream.onError(error)
        }
    }
}