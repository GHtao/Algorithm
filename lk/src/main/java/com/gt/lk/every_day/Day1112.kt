package com.gt.lk.every_day

/**
 * time 2020/11/12 0012
 * author GT
 */
object Day1112 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = nthUglyNum(10)
        val result = findNthDigit(11)
        println("result:$result")
    }

    /**
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。
     * 求按从小到大的顺序的第 n 个丑数
     */
    private fun nthUglyNum(n:Int):Int{
        //n-1个数的丑数 n>=1
        val dp = IntArray(n){0}
        dp[0] = 1
        var a = 0//对应2
        var b = 0//对应3
        var c = 0//对应5
        var min = 0
        for(i in 1 until n){
            //是前面某一个丑数乘以2 3 5的最小值
            val temp1 = dp[a] * 2
            val temp2 = dp[b] * 3
            val temp3 = dp[c] * 5
            min = temp1.coerceAtMost(temp2)
            min = min.coerceAtMost(temp3)
            if(min == temp1) a++
            if(min == temp2) b++
            if(min == temp3) c++
            dp[i] = min
        }
        return dp[n-1]
    }

    /**
     * 数字以0123456789101112131415…的格式序列化到一个字符序列中。
     * 在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等
     * 位数  范围     数字个数  位数个数
     * 1    0-9      9       9
     * 2    10-99    90      2*90
     * 3    100-999  900     3*900
     */
    private fun findNthDigit(n:Int):Int{
        var temp = n
        var count = 9
        var digit = 1
        var start = 1
        while (temp > count){
            //减去前面的位数
            temp -= count
            digit++
            start *= 10
            //计算新的数量
            count = 9*digit*start
        }
        //得出起始的数字
        val num = start + (temp - 1) / digit // 2.
        //计算数字的第几位
        return num.toString().toCharArray()[(temp - 1) % digit] - '0' // 3.
    }
}