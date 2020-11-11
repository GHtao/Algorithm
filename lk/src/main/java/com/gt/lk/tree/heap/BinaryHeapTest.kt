package com.gt.lk.tree.heap

/**
 * time 2020/6/16 0016
 * author GT
 */
object BinaryHeapTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val binaryHeap = BinaryHeap(10)
        binaryHeap.insert(4)
        binaryHeap.insert(1)
        binaryHeap.insert(6)
        binaryHeap.insert(8)
        binaryHeap.insert(7)
        binaryHeap.delMax()
        println("aaa")
    }
}