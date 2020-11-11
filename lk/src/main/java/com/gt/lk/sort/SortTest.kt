package com.gt.lk.sort

/**
 * time 2020/6/18 0018
 * author GT
 */
object SortTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val array = intArrayOf(4,2,6,5,7,9,1)
//        bubble(array)
        quick(array,0,array.size-1)
        println(array.contentToString())
    }

    private fun bubble(arr:IntArray){
        for(i in arr.indices){
            var isChange = false
            for(j in 1 until arr.size){
                if(arr[j-1] > arr[j]){
                    val temp = arr[j-1]
                    arr[j-1] = arr[j]
                    arr[j] = temp
                    isChange = true
                }
                if(!isChange) return
            }
        }
    }

    private fun quick(arr:IntArray,li:Int,hi:Int){
        var l = li
        var h = hi
        if(l >= h) return
        val temp = arr[l]
        while( l < h){
            while(l<h && arr[h] >= temp){
                h--
            }
            while(l<h && arr[l] <= temp){
                l++
            }
            if(l<h){
                val t = arr[l]
                arr[l] = arr[h]
                arr[h] = t
            }
        }
        arr[li] = arr[l]
        arr[l] = temp
        quick(arr,li,l-1)
        quick(arr,l+1,hi)
    }

    private fun heap(arr:IntArray){

    }
}