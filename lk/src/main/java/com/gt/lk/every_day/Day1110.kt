package com.gt.lk.every_day

import kotlin.math.pow

/**
 * time 2020/11/10 0010
 * author GT
 */
object Day1110 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = singleNum(intArrayOf(4,3,3,3))
        val result = dicesProbability(10)
        println("result:${result.contentToString()}")
    }

    /**
     * 在一个数组中除一个数字只出现一次之外，其他数字都出现了三次。
     * 找出那个只出现一次的数字
     */
    private fun singleNum(array:IntArray):Int{
        var ones = 0
        var twos = 0
        for(num in array){
            ones = (ones xor num) and twos.inv()//inv按位取反
            twos = (twos xor num) and ones.inv()
        }
        return ones
    }

    /**
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     */
    private val hashMap = HashMap<Int,Int>()
    private fun dicesProbability(n:Int):FloatArray{
        val list = ArrayList<Int>()
        val size = 5*n+1
        val array = FloatArray(size)
        dicesProbability(n,list)
        for(i in n .. 6*n){
            //总数为6.0.pow(n)次
            array[i-n] = hashMap[i]!!.toFloat()/(6.0.pow(n).toFloat())
        }

        return array
    }
    private fun dicesProbability(n:Int,list:ArrayList<Int>){
        if(list.size == n){//计算数值的结果
            var sum = 0
            list.forEach {
                sum += it
            }
            if(hashMap.containsKey(sum)){
                hashMap[sum] = hashMap[sum]!!+1
            }else{
                hashMap[sum] = 1
            }
            return
        }
        for(i in 1..6){
            list.add(i)
            dicesProbability(n,list)
            list.remove(i)
        }
    }
}