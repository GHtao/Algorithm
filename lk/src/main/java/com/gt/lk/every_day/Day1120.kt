package com.gt.lk.every_day

import java.util.*

/**
 * time 2020/11/20 0020
 * author GT
 */
object Day1120 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = search(intArrayOf(5,7,7,8,8,10),8)
//        println("result:$result")

//        val result = maxSlidingWindow(intArrayOf(1,3,-1,-3,5,3,6,7),3)
//        println("result:${result.contentToString()}")

        val result = hanMingWeight(0b01111111111111111111111111111101)
        println("result:${result}")

    }

    /**
     * 统计一个数字在排序数组中出现的次数
     */
    private fun search(array:IntArray,target:Int):Int{
        var l = 0
        var h = array.size-1
        while(l<=h){
            val mid = (l+h)/2
            when{
                array[mid]>target->{
                    h = mid-1
                }
                array[mid]<target->{
                    l=mid+1
                }
                else->{
                    var hi = mid
                    var li = mid
                    while(hi<array.size && array[hi]==target)hi++
                    while(li>=0 && array[li]==target)li--
                    return hi-li-1
                }
            }
        }
        return 0
    }

    /**
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值
     */
    private fun maxSlidingWindow(nums:IntArray,k:Int):IntArray{
        if(nums.isEmpty() || k == 0) return intArrayOf(0)
        val deque =  LinkedList<Int>()
        val res = IntArray(nums.size - k + 1)
        for(i in 0 until k) { // 未形成窗口
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast()
            deque.addLast(nums[i])
        }
        res[0] = deque.peekFirst()
        for(i in k until nums.size) { // 形成窗口后
            if(deque.peekFirst() == nums[i - k])
                deque.removeFirst()
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast()
            deque.addLast(nums[i])
            res[i - k + 1] = deque.peekFirst()
        }
        return res
    }

    /**
     * 输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。
     * 例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2
     */
    private fun hanMingWeight(n:Int):Int{
        var count = 0
        var num = n
        while (num != 0){
            if(num and 1 == 1) count++
            num = num.shr(1)
        }

        return count
    }
}