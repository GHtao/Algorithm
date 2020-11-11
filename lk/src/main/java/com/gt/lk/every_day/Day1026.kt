package com.gt.lk.every_day

/**
 * time 2020/10/26 0026
 * author GT
 */
object Day1026 {

    @JvmStatic
    fun main(args: Array<String>) {
        val maxValue = cuttingRope(8)
        println("maxValue:$maxValue")
    }

    /**
     * n 绳子长度
     * return 当前长度的最大乘积
     */
    private fun cuttingRope(n:Int):Int{
        val dp = IntArray(n + 1)
        dp[1] = 1//当前长度的最大乘积
        for (i in 2.. n) {
            for (j in 1 until i) {
                //左右两边最大长度的乘积的最大值
                dp[i] = Math.max(dp[i], (Math.max(j, dp[j])) * (Math.max(i - j, dp[i - j])))
            }
        }
        return dp[n]
    }

}