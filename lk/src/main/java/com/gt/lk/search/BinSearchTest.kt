package com.gt.lk.search

/**
 * time 2020/6/23 0023
 * author GT
 * 二分查找
 */
object BinSearchTest {
    @JvmStatic
    fun main(args: Array<String>) {
//        val index = search(intArrayOf( -1,0,3,5,9,12),-1)
        val index = searchLeft(intArrayOf( -1,-1,5,5,9,12),-1)
        val right = searchRight(intArrayOf( -1,-1,5,5,9,12),-1)
        println("index:$index")
        println("right:$right")
    }

    /**
     * 二分查找
     */
    private fun search(arr:IntArray,target:Int):Int{
        if(arr.isEmpty()) return -1
        var l = 0
        var h = arr.size-1
        while(l <= h ){
            val mid = l +(h-l)/2
            if(arr[mid] == target){
                return mid
            }else if(arr[mid] > target){
                h = mid - 1
            }else if(arr[mid] < target){
                l = mid +1
            }
        }
        return -1
    }

    /**
     * 寻找左侧边界
     */
    private fun searchLeft(arr:IntArray,target:Int):Int{
        if(arr.isEmpty()) return -1
        var l = 0
        var h = arr.size-1
        while(l <= h ){
            val mid = l +(h-l)/2
            if(arr[mid] == target){
                h = mid -1//终止循环
            }else if(arr[mid] > target){
                h = mid - 1
            }else if(arr[mid] < target){
                l = mid +1
            }
        }
        if(l >= arr.size || arr[l] != target)
            return -1
        return l
    }

    /**
     * 寻找右侧边界
     */
    private fun searchRight(arr:IntArray,target:Int):Int{
        if(arr.isEmpty()) return -1
        var l = 0
        var h = arr.size-1
        while(l <= h ){
            val mid = l +(h-l)/2
            if(arr[mid] == target){
                l = mid + 1//终止循环
            }else if(arr[mid] > target){
                h = mid - 1
            }else if(arr[mid] < target){
                l = mid +1
            }
        }
        if(h < 0 || arr[h] != target)
            return -1
        return h
    }
}