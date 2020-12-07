package com.gt.lk.every_day

import com.gt.lk.bean.ListNode
import com.gt.lk.utils.ListNodeUtil

/**
 * time 2020/11/30 0030
 * author GT
 */
object Day1130 {
    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(intArrayOf( 5, 1, 9,11),
            intArrayOf(2, 4, 8,10),
            intArrayOf(13, 3, 6, 7),
            intArrayOf(15,14,12,16))
//        rotate(matrix)
//        rotateNoSpace(matrix)
//        matrix.forEach {
//            println("result:${it.contentToString()}")
//        }

//        val result = oneEditWay("pales","ple")
//        println("result:$result")

        val l1 = ListNode().apply {
            value = 7
            next = ListNode().apply {
                value = 1
                next = ListNode().apply {
                    value = 6
                }
            }
        }
        val l2 = ListNode().apply {
            value = 5
            next = ListNode().apply {
                value = 9
                next = ListNode().apply {
                    value = 2
                }
            }
        }
        val result = addTwoNumbers(l1,l2)
        ListNodeUtil.printLinkNode(result)
    }

    /**
     * 旋转矩阵
     */
    private fun rotate(matrix:Array<IntArray>):Array<IntArray>{
        val array = Array(matrix.size){IntArray(matrix[0].size)}
        for(i in matrix.indices){
            for(j in matrix[i].indices){
                array[j][matrix.size-i-1] = matrix[i][j]
            }
        }
        return array
    }

    /**
     * 不用额外的空间 旋转矩阵
     */
    private fun rotateNoSpace(matrix:Array<IntArray>){
        for(i in 0 until  matrix.size/2){
            for(j in matrix[i].indices){//上下翻转
                val temp = matrix[i][j]
                matrix[i][j] = matrix[matrix.size-i-1][j]
                matrix[matrix.size-i-1][j] = temp
            }
        }
        //对角线翻转
        for(i in matrix.indices){
            for(j in 0 until i){
                val temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j] [i] = temp
            }
        }
    }

    /**
     * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。
     * 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑
     */
    private fun oneEditWay(s1:String,s2:String):Boolean{
        var count = 0
        var index1 = 0
        var index2 = 0
        val arr1 = s1.toCharArray()
        val arr2 = s2.toCharArray()
        if(Math.abs(arr1.size-arr2.size)>1) return false
        while(index1<arr1.size && index2 < arr2.size){
            when{
                arr1[index1] == arr2[index2]->{
                    index1++
                    index2++
                }
                else->{
                    when {
                        arr1[index1+1] == arr2[index2] -> {
                            count++
                            index1++
                        }
                        arr1[index1] == arr2[index2+1] -> {
                            count++
                            index2++
                        }
                        arr1[index1+1] == arr2[index2+1] -> {
                            count++
                            index1++
                            index2++
                        }
                    }
                }
            }
        }
        count += Math.max(arr1.size-index1,arr2.size-index2)
        println("count:$count")
        return count <= 1
    }

    /**
     * 给定两个用链表表示的整数，每个节点包含一个数位。
     * 这些数位是反向存放的，也就是个位排在链表首部。
     * 编写函数对这两个整数求和，并用链表形式返回结果
     */
    private fun addTwoNumbers(l1: ListNode?,l2:ListNode?):ListNode{
        val node = ListNode()
        var num1 = 0
        var num2 = 0
        var count = 1
        var temp1 = l1
        var temp2 = l2
        while (temp1 != null || temp2 != null ){
            if(temp1 != null){
                num1 += temp1.value * count
                temp1 = temp1.next
            }
            if(temp2 != null){
                num2 += temp2.value * count
                temp2 = temp2.next
            }
            count *= 10
        }
        var num = num1 + num2
        var temp = node
        while(num >0){
            temp.next = ListNode().apply { value = num % 10 }
            temp = temp.next!!
            num /= 10
        }
        return node.next!!
    }
}