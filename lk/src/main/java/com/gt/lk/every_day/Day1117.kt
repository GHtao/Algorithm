package com.gt.lk.every_day

import com.gt.lk.bean.ListNode
import java.util.*
import javax.management.relation.RelationSupport

/**
 * time 2020/11/17 0017
 * author GT
 */
object Day1117 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = minArray(intArrayOf(2,2,2,0,1))
//        println("result:$result")
        val head = ListNode().apply {
            value = 1
            next = ListNode().apply {
                value = 2
                next = ListNode().apply {
                    value = 3
                    next = ListNode().apply {
                        value = 4
                        next = ListNode().apply {
                            value = 5
                        }
                    }
                }
            }
        }
//        val reverse = reverseList(head)
//        ListNodeUtil.printLinkNode(reverse!!)

//        val result = fib(100)
//        println("result:$result")

//        val queue = CQueue()
//        queue.appendTail(1)
//        queue.appendTail(2)
//        queue.appendTail(3)
//        queue.appendTail(4)
//        var result = queue.deleteHead()
//        println("result:$result")
//        result = queue.deleteHead()
//        println("result:$result")

        val result = spiralOrder(arrayOf(intArrayOf(1,2,3),intArrayOf(4,5,6),intArrayOf(7,8,9)))
        println("result:${result.contentToString()}")

    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。 
     * array 是一个递增数组的旋转
     * 用二分查找  通过忽略左右的数据来找到目标值 O(log n)
     */
    private fun minArray(array:IntArray):Int{
//        for(i in 1 until array.size){
//            if(array[i] < array[i-1]) return array[i]
//        }
        var l = 0
        var h = array.size-1
        while(l<h){
            val mid = (l+h)/2
            when{
                array[mid] > array[l] -> l=mid
                array[mid] < array[l] -> h= mid
                else -> l++
            }
        }
        return array[l]
    }

    /**
     * 反转链表
     */
    private fun reverseList(head:ListNode?):ListNode?{
        var pre:ListNode? = null
        var next = head

        while(next != null){
            val temp = next.next
            next.next = pre
            pre = next
            next = temp
        }
        return pre
    }

    /**
     * 斐波那契数列
     * 0 1 1 2 3 5
     */
    private fun fib(n:Int):Int{
        var first = 0
        var second = 1
        var result = 0
        for(i in 2..n){
            result = (first + second)%1000000007
            first = second
            second = result
        }
        return result
    }

    /**
     * 用两个栈实现一个队列。
     * 队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
     * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
     */
    class CQueue{
        private val stack1 = Stack<Int>()
        private val stack2 = Stack<Int>()
        /**
         * 在尾部添加
         */
        fun appendTail(value:Int){
            stack1.push(value)
        }
        /**
         * 删除头部
         */
        fun deleteHead():Int{
            if(stack2.isEmpty()){
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop())
                }
            }
            if(stack2.isEmpty()) return -1
            return stack2.pop()
        }
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     * 输入：matrix = [[1,2,3],
     *                [4,5,6],
     *                [7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     */
    private fun spiralOrder(matrix:Array<IntArray>):IntArray{
        if (matrix.isEmpty() || matrix[0].isEmpty()) {
            return intArrayOf(0)
        }
        val rows = matrix.size
        val columns = matrix[0].size
        val visited = Array(rows){BooleanArray(columns)}
        val total = rows * columns
        val order = IntArray(total)
        var row = 0
        var column = 0
        val directions = arrayOf(intArrayOf(0,1),intArrayOf(1,0),intArrayOf(0,-1),intArrayOf(-1,0))
        var directionIndex = 0
        for (i in 0 until total) {
            order[i] = matrix[row][column]
            visited[row][column] = true
            val nextRow = row + directions[directionIndex][0]
            val nextColumn = column + directions[directionIndex][1]
            if (nextRow < 0
                    || nextRow >= rows
                    || nextColumn < 0
                    || nextColumn >= columns
                    || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % directions.size
            }
            row += directions[directionIndex][0]
            column += directions[directionIndex][1]
        }
        return order
    }
}