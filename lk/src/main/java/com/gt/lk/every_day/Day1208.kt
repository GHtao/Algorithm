package com.gt.lk.every_day

/**
 * time 2020/12/8 0008
 * author GT
 */
object Day1208 {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = subSets(intArrayOf(1,2,3))
        result.forEach {
            println("result:${it.toIntArray().contentToString()}")
        }
    }

    /**
     * 编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
     * 说明：解集不能包含重复的子集
     * 输入： nums = [1,2,3]
     * 输出：[
     * [3],
     * [1],
     * [2],
     * [1,2,3],
     * [1,3],
     * [2,3],
     * [1,2],
     * []
     * ]
     */
    private fun subSets(array:IntArray):List<List<Int>>{
        val result = ArrayList<List<Int>>()
        result.add(ArrayList())
        array.forEach {
            val size = result.size
            for(i in 0 until size){
                val list = ArrayList(result[i])
                list.add(it)
                result.add(list)
            }
        }
        return result
    }
}