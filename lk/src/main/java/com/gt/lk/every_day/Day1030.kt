package com.gt.lk.every_day

import java.util.*
import kotlin.math.max

/**
 * time 2020/10/30 0030
 * author GT
 *
 */
object Day1030 {

    @JvmStatic
    fun main(args: Array<String>) {
        val maxQueue = MaxQueue()
        maxQueue.pushBack(1)
        maxQueue.pushBack(2)
        maxQueue.pushBack(4)
        maxQueue.pushBack(1)

        println("max:${maxQueue.maxValue()}")
        println("max:${maxQueue.popFront()}")
        println("max:${maxQueue.maxValue()}")
        println("max:${maxQueue.popFront()}")
        println("max:${maxQueue.popFront()}")
        println("max:${maxQueue.maxValue()}")

        val array = intArrayOf(1,2,10,4,1,4,3,3)
        val resultArr = singleNumbers(array)
        println("result arr:${resultArr.contentToString()}")
    }

    /**
     * 先入先出
     * 定义一个队列并实现函数 max_value 得到队列里的最大值，
     * 要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
     * 若队列为空，pop_front 和 max_value 需要返回 -1
     */
    class MaxQueue{
        private val linkedList = LinkedList<Int>()
        private var max:Int = -1
        /**
         * 获取队列最大值
         */
        fun maxValue():Int{
            if(linkedList.isEmpty()) return -1
            return max
        }

        /**
         * 数据压入队列
         */
        fun pushBack(value:Int){
            if(value > max) max = value
            linkedList.addLast(value)
        }

        /**
         * 获取队列的头部数据
         */
        fun popFront():Int{
            return if(linkedList.isEmpty()){
                -1
            }else{
                val remove = linkedList.removeFirst()
                if(remove == max){//移出的数据等于max 就重新给max赋值
                    max = linkedList.max() ?: -1
                }
                remove
            }
        }
    }

    /**
     * 找出数组中只出现一次的两个数
     * 输入：[1,2,10,4,1,4,3,3]
     * 输出：[2,10] 或 [10,2]
     * 空间复杂度O(1)
     * 时间复杂度O(n)
     * 使用异或 a^b^a = b
     */
    private fun singleNumbers(nums:IntArray):IntArray{
        var temp = 0
        //先得到两个不一致数值的异或结果
        nums.forEach {
            temp = temp.xor(it)
        }
        //找到两个数最低位的相等的二进制数值
        var div = 1
        while((div and  temp) == 0){
            div = div.shl(1)
        }
        var a = 0
        var b = 0
        //根据最低位相等的数值 将原来数组分成两个数组 再分别做异或 就可以得到两个不一样的值
        nums.forEach {
            if((div and it) == 0){
                a = a.xor(it)
            }else{
                b = b.xor(it)
            }
        }
        return intArrayOf(a,b)
    }
}