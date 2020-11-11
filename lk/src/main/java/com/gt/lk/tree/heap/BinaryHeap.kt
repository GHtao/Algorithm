package com.gt.lk.tree.heap

import java.lang.RuntimeException

/**
 * time 2020/6/16 0016
 * author GT
 * 二叉堆 下沉和上浮完成排序
 * 主要是增删改查的操作
 */
class BinaryHeap(private val maxNum:Int) {
    //0的索引不使用 所以要加一个长度
    private val keys = IntArray(maxNum+1)
    private var N = 0//数据长度
    /**
     * keys i 是否小于keys j
     */
    private fun compare(i:Int,j:Int):Boolean{
        return keys[i] < keys[j]
    }

    /**
     * 插入数据
     * 插入到底部 然后上浮
     */
    fun insert(value:Int){
        if(N+1 == maxNum){
            throw RuntimeException("到达最大的数据限制")
        }
        N++
        keys[N] = value
        swim(N)
    }

    /**
     * 删除顶部数据
     * 将顶部与底部交换 然后删除最后的数据
     * 再下沉
     */
    fun delMax():Int{
        if(N == 0){
            throw RuntimeException("没有数据")
        }
        val value = keys[1]
        swap(1,N)
        keys[N] = 0
        N--
        sink(1)
        return value
    }
    /**
     * 下沉方法
     */
    private fun sink(k:Int){
        var index = k
        while(leftIndex(index) <= N ){
            //需要左和右比较谁大
            var max = leftIndex(index)
            if(rightIndex(index)<=N && compare(max,rightIndex(index))){
                max = rightIndex(index)
            }
            if(compare(index,max)){
                swap(index,max)
                index = max
            }
        }
    }
    /**
     * 上浮方法
     */
    private fun swim(k:Int){
        //判断k和他父节点的大小
        var index = k
        while(index>1 && compare(parentIndex(index),index)){
            swap(index,parentIndex(index))
            index = parentIndex(index)
        }
    }

    /**
     * 交换两个节点数值
     */
    private fun swap(i:Int,j:Int){
        val temp = keys[i]
        keys[i] = keys[j]
        keys[j] = temp
    }
    /**
     * 获取父节点索引
     */
    private fun parentIndex(k:Int):Int{
        return k/2
    }

    /**
     * 获取左孩子索引
     */
    private fun leftIndex(k:Int):Int{
        return 2*k
    }
    /**
     * 获取右孩子索引
     */
    private fun rightIndex(k:Int):Int{
        return 2*k+1
    }
}