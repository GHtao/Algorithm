package com.gt.lk.every_day

/**
 * time 2020/11/13 0013
 * author GT
 */
object Day1113 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = findRepeatNum(intArrayOf(2,3,1,0,2,5,3))
        val result = findContinuousSequence(15)
        result.forEach {
            println("result:${it.contentToString()}")
        }
    }

    /**
     * 找出数组中任意重复的一个数字
     */
    private fun findRepeatNum(array:IntArray):Int{
        val set = HashSet<Int>()
        array.forEach {
            if(set.contains(it)){
                return it
            }
            set.add(it)
        }
        return Int.MIN_VALUE
    }

    /**
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列
     */
    private fun findContinuousSequence(target:Int):ArrayList<IntArray>{
        val vec = ArrayList<IntArray>()
        var l = 1
        var r = 2
        //从L开始有没有和满足target
        while (l<r){
            //l到r的和
            val sum = (l + r) * (r - l + 1) / 2;
            when {
                sum == target -> {//等于目标值
                    val res = IntArray(r - l + 1)
                    for(i in l..r){
                        res[i - l] = i
                    }
                    vec.add(res)
                    //l前移
                    l++
                }
                sum < target -> {
                    //r前移
                    r++
                }
                else -> {
                    l++
                }
            }
        }

        return vec
    }
}