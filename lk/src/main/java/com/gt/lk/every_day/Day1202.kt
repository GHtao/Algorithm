package com.gt.lk.every_day

/**
 * time 2020/12/2 0002
 * author GT
 */
object Day1202 {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = waysToChange(5)
        println("result:$result")
    }

    /**
     * 给定数量不限的硬币，
     * 币值为25分、10分、5分和1分，编写代码计算n分有几种表示法
     */
    private fun waysToChange(n:Int):Int{
        val coins = intArrayOf(25,10,5,1)
        val dp = IntArray(n+1)//n对应的几种表示方法
        dp[0] = 1
        coins.forEach {
            for (i in it .. n) {
                dp[i] = (dp[i] + dp[i - it])
            }
        }
        return dp[n]
    }

    /**
     * 不用临时变量，直接交换numbers = [a, b]中a与b的值
     *
     * 减法
     *
     * 异或
     */
    private fun swapNumbers(num:IntArray){
        num[0] = num[0]+num[1]//两个数的和
        num[1] = num[0] - num[1]//1=0=和-1
        num[0] = num[0] - num[1]//0=1=和-1（1已经代表0了）
    }
}