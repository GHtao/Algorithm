package com.gt.lk.every_day

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max

/**
 * time 2020/11/3 0003
 * author GT
 */
object Day1103 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = lengthOfLongestSubString("bbbb")

        val array = arrayOf(intArrayOf(1,3,1), intArrayOf(1,5,1), intArrayOf(4,2,1))
        val result = maxValue(array)
        println("result:$result")
    }

    /**
     * 获取最长子串的长度
     * 双指针
     */
    private fun lengthOfLongestSubString(str:String):Int{
        var start = 0
        var end = 1
        var maxLength = 0
        val hashMap = HashMap<Char,Int>()
        val chars = str.toCharArray()
        hashMap[chars[0]] = 0
        while (end < str.length){
            if(hashMap.containsKey(chars[end])){
                //包含结束 计算
                start = hashMap.remove(chars[end])!!+1
            }
            maxLength = max(maxLength,end-start+1)
            hashMap[chars[end]] = end
            end++
        }
        return maxLength
    }

    /**
     * 礼物的最大值
     * 只能向二维数组的左和下移动 找出可能的结果最大值
     * 动态规划
     */
    private fun maxValue(array:Array<IntArray>):Int{
        //每一个位置上可能的最大值
        //多开一个数组空间 减少边界判断
        val dp = Array(array.size+1){IntArray(array[0].size+1)}
        for(i in 1 .. array.size){
            val subSize = array[i-1].size
            for(j in 1 .. subSize){
                dp[i][j]= max(dp[i][j-1] + array[i-1][j-1], dp[i-1][j] + array[i-1][j-1])
            }
        }
        return dp[array.size][array[0].size]
    }
}