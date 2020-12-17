package com.gt.lk.every_day

import com.sun.glass.ui.View

/**
 * time 2020/12/15 0015
 * author GT
 */
object Day1215 {
    @JvmStatic
    fun main(args: Array<String>) {
//        LRUCache(2).apply {
//            put(1,1)
//            put(2,2)
//            var value = get(1)
//            println("result:${value}")
//            put(3,3)
//            value = get(2)
//            println("result:${value}")
//            put(4,4)
//            value = get(1)
//            println("result:${value}")
//            value = get(3)
//            println("result:${value}")
//            value = get(4)
//            println("result:${value}")
//        }
        val result = pathWithObstacles(
            arrayOf(intArrayOf(0,1,0),
            intArrayOf(0,1,0),
            intArrayOf(0,0,0)))
        result.forEach {
            println("result:${it.contentToString()}")
        }
    }

    /**
     * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。
     * 机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。
     * 设计一种算法，寻找机器人从左上角移动到右下角的路径
     * 障碍物位置值为1 通行的为0
     */
    private fun pathWithObstacles(array:Array<IntArray>):ArrayList<IntArray>{
        val result = ArrayList<IntArray>()
        path(array,0,0,result)
        return result
    }
    private fun path(array:Array<IntArray>,row:Int,line:Int,result: ArrayList<IntArray>):Boolean{
        if(row >= array.size || line >= array[row].size || array[row][line]==1)return false
        val intArray = intArrayOf(row,line)
        result.add(intArray)
        if(row == array.size -1 && line == array[row].size-1) return true
        if(!(path(array,row,line+1,result) || path(array,row+1,line,result))){
            result.remove(intArray)//不符合条件的再移除掉
            return false
        }
        return true
    }

    /**
     * 最近最少使用缓存
     */
    class LRUCache(private val capacity:Int){
        private val linkedHashMap = LinkedHashMap<Int,Int>(capacity,0.75f,true)
        /**
         * 获取数据
         */
        fun get(key:Int):Int{
            return linkedHashMap[key] ?: -1
        }
        /**
         * 存入数据
         */
        fun put(key:Int,value:Int){
            linkedHashMap[key] = value
            if(linkedHashMap.size > capacity){
                //超过容量之后删除 最老的数据
                val next = linkedHashMap.iterator().next()
                linkedHashMap.remove(next.key)
            }
        }
    }
}