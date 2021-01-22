package com.gt.lk.every_day

import kotlin.math.abs

/**
 * time 2020/12/18 0018
 * author GT
 */
object Day1218 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = search(intArrayOf(15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14),5)
//        println("result:$result")
//        val result = smallestDifference(intArrayOf(1, 3, 15, 11, 2), intArrayOf(23, 127, 235, 19, 8))
//        println("result:$result")

        val result = getValidT9Words("2", arrayOf("a","b","c","d"))
        println("result:${result.toArray().contentToString()}")
    }

    /**
     * 给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。
     * 请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。
     * 若有多个相同元素，返回索引值最小的一个
     *  输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
     *  输出: 8（元素5在该数组中的索引）
     */
    private fun search(array:IntArray,target:Int):Int{
        for(i in array.indices){
            if(array[i] == target) return i
        }
        return -1
    }

    /**
     * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
     * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
     * 输出： 3，即数值对(11, 8)
     */
    private fun smallestDifference(a:IntArray,b:IntArray):Int{
        a.sort()
        b.sort()
        var i = 0
        var j = 0
        var min = Int.MAX_VALUE
        while(i<a.size && j<b.size){
            min = min.coerceAtMost(abs(a[i] - b[j]))
            if(a[i]>b[j]) j++ else i++
        }
        return min
    }

    /**
     * 用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。
     * 每个数字映射到0至4个字母。给定一个数字序列，实现一个算法来返回匹配单词的列表。
     * 你会得到一张含有有效单词的列表
     * 2 abc
     * 3 def
     * 4 ghi
     * 5 jkl
     * 6 mno
     * 7 pqrs
     * 8 tuv
     * 9 wxyz
     */
    private fun getValidT9Words(num:String,words:Array<String>):ArrayList<String>{
        val result = ArrayList<String>()
        val hashMap = HashMap<Char,CharArray>()
        hashMap['2'] = charArrayOf('a','b','c')
        hashMap['3'] = charArrayOf('d','e','f')
        hashMap['4'] = charArrayOf('g','h','i')
        hashMap['5'] = charArrayOf('j','k','l')
        hashMap['6'] = charArrayOf('m','n','o')
        hashMap['7'] = charArrayOf('p','q','r','s')
        hashMap['8'] = charArrayOf('t','u','v')
        hashMap['9'] = charArrayOf('w','x','y','z')
        val nums = num.toCharArray()
        words.forEach {
            var flag = true
            it.toCharArray().forEachIndexed { index, c ->
                hashMap[nums[index]]?.apply {
                    if(!contains(c)){
                        flag = false
                        return@forEachIndexed
                    }
                }
            }
            if(flag) result.add(it)
        }
        return result
    }
}