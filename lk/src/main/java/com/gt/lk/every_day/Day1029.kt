package com.gt.lk.every_day

/**
 * time 2020/10/29 0029
 * author GT
 */
object Day1029 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = sumNum(100)
        val result = translateNum(12258)
        println("result:$result")
    }

    /**
     * 求1+2+3+...+n
     * 用递归和$$运算来替换循环
     * val flag = n>0 &&(n += sunNum(n-1))>0
     * return n
     */
    private fun sumNum(n:Int):Int{
        var result = 0
        for (i in 0..n){
            result += i
        }
        return result
    }

    /**
     * 将数字转换成字符串的可能情况
     * 0=a 1=b ... 25=z
     *
     * 滚动数组
     */
    private fun translateNum(num:Int):Int{
        val src = num.toString()
        var p = 0
        var q = 0
        var r = 1
        for(i in src.indices){
            //滑动数组 计算一次 每一位向前移动一次
            p = q
            q = r
            r = q
            if(i == 0) continue
            val pre = src.substring(i-1,i+1)
            if(pre <= "25" && pre >= "10") r += p
        }
        return r
    }

}