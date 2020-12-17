package com.gt.lk.every_day

import java.util.function.Function

/**
 * time 2020/12/17 0017
 * author GT
 */
object Day1217 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = subSort(intArrayOf(1,2,5,4,8,9))
//        println("result:${result.contentToString()}")

        val result = groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat"))
        result.forEach {
            println("result:${it.toArray().contentToString()}")
        }
    }

    /**
     * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
     * 注意：n-m尽量最小，也就是说，找出符合条件的最短序列。
     * 函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]
     */
    private fun subSort(array:IntArray):IntArray{
        if(array.isEmpty() || array.size == 1) return intArrayOf(-1,-1)
        var m = 0
        var n = array.size - 1
        //注意：数组中的数可能为负数
        var min = Integer.MAX_VALUE
        var max = Integer.MIN_VALUE
        //max为乱序的最大值，min为乱序的最小值
        for(i in array.indices){
            if(max <= array[i]){
                max = array[i]
            }else{
                //记录乱序数组小于最大值的最大索引
                m = i
            }
        }
        for(j in array.size - 1 downTo 0){
            if(min >= array[j]){
                min = array[j]
            }else{
                //记录乱序数组大于最大值的最小索引
                n = j
            }
        }
        return if(m > n) intArrayOf(n,m) else intArrayOf(-1,-1)
    }

    /**
     * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     */
    private fun groupAnagrams(array:Array<String>):ArrayList<ArrayList<String>>{
        val result = ArrayList<ArrayList<String>>()
        val hashMap = HashMap<String,ArrayList<String>>()
        for(i in array.indices){
            val chars = array[i].toCharArray().apply {
                sort()
            }
            val sortStr = String(chars)
            hashMap.computeIfAbsent(sortStr) { ArrayList() }.add(array[i])
        }
        hashMap.forEach { (_, u) ->
            result.add(u)
        }
        return result
    }
}