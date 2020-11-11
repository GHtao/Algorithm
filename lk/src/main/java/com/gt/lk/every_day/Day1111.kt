package com.gt.lk.every_day

/**
 * time 2020/11/11 0011
 * author GT
 */
object Day1111 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = strToInt("-91283472332")
//        println("result:$result")
        val result = constructArr(intArrayOf(0,2,3,4,5))
        println("result:${result.contentToString()}")
    }

    /**
     * 将一个字符串转换成int值
     */
    private fun strToInt(str:String):Int{
        var res = 0
        val boundary = Integer.MAX_VALUE / 10
        var i = 0
        var sign = 1
        val length = str.length
        if(length == 0) return 0
        val chars = str.toCharArray()
        while(chars[i] == ' ')
            if(++i == length) return 0
        if(chars[i] == '-') sign = -1
        if(chars[i] == '-' || chars[i] == '+') i++
        for(j in i until length ) {
            if(chars[j] < '0' || chars[j] > '9') break
            //判断边界值
            if(res > boundary || res == boundary && chars[j] > '7')
                return if(sign == 1)Integer.MAX_VALUE else Integer.MIN_VALUE;
            res = res * 10 + (chars[j] - '0')
        }
        return sign * res
    }

    /**
     * 根据数组a 构建数组b
     * A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
     * 不能使用除法
     */
    private fun constructArr(a:IntArray):IntArray{
        val b = IntArray(a.size){1}
//        for(i in b.indices){
//            for(j in 0 until i){
//                if(b[i] == 1){
//                    b[i] = a[j]
//                }else{
//                    b[i] *= a[j]
//                }
//            }
//            for(j in i+1 until a.size){
//                if(b[i] == 1){
//                    b[i] = a[j]
//                }else{
//                    b[i] *= a[j]
//                }
//            }
//        }
        //计算上三角
        for(i in 1 until a.size){
            b[i] = b[i-1]*a[i-1]
        }
        //计算下三角
        var temp = 1
        for(i in a.size-2 downTo 0 ){
            temp *= a[i+1]
            b[i] *= temp
        }
        return b
    }
}