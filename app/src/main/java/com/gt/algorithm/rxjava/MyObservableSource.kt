package com.gt.algorithm.rxjava

/**
 * time 2020/10/22 0022
 * author GT
 */
interface MyObservableSource {
    fun subscribe(observer:MyObserver)
}