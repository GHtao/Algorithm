package com.gt.lk.every_day

import com.gt.lk.bean.ListNode
import com.gt.lk.bean.TreeNode
import com.gt.lk.utils.ListNodeUtil
import com.gt.lk.utils.TreeUtil
import java.lang.RuntimeException


/**
 * time 2020/11/18 0018
 * author GT
 */
object Day1118 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = exchange(intArrayOf(2,4,6,1,3,5))
//        println("result:${result.contentToString()}")

//        val result = missingNumber((intArrayOf(0,1,2,3,4,5,6,7,9)))
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
//        val result = deleteNode(head,3)
//        ListNodeUtil.printLinkNode(result!!)
        val root = TreeNode().apply {
            value = 4
            left = TreeNode().apply {
                value = 2
                left = TreeNode().apply {
                    value = 1

                }
                right = TreeNode().apply {
                    value = 3
                }
            }
            right = TreeNode().apply {
                value = 7
                left = TreeNode().apply {
                    value = 6

                }
                right = TreeNode().apply {
                    value = 9
                }
            }
        }
//        val result = mirrorTree(root)
//        println("result:$result")

        val result = maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5))
        println("result:$result")
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分
     */
    private fun exchange(array:IntArray):IntArray{
        var ji = array.size-1
        var ou = 0
        while(ou < ji){
            while(ou<ji && array[ji] % 2 == 0) ji--
            while(ou<ji && array[ou] % 2 == 1) ou++
            if(ji > ou){
                val temp = array[ji]
                array[ji] = array[ou]
                array[ou] = temp
            }
        }
        return array
    }

    /**
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
     * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字
     */
    private fun missingNumber(array:IntArray):Int{
        for(i in 1 until array.size){
            if(array[i] - array[i-1]>1){
                return array[i]-1
            }
        }
        return -1
    }

    /**
     * 删除链表中的一个节点 返回删除之后的头结点
     */
    private fun deleteNode(head:ListNode?,key:Int):ListNode?{
        if(head == null) return null
        if(head.value == key) return head.next
        var next = head
        while (next!!.next != null){
            if(next.next!!.value == key){
                next.next = next.next!!.next
                return head
            }
            next = next.next
        }
        throw  RuntimeException("没有找到元素")
    }

    /**
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像
     * 左右子树互换
     */
    private fun mirrorTree(root: TreeNode?):TreeNode?{
        if(root == null) return null
        val left = root.left
        val right = root.right
        if(root.left != null){
            root.right = mirrorTree(left)
        }
        if(root.right != null){
            root.left = mirrorTree(right)
        }
        return root
    }

    /**
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。
     * 求所有子数组的和的最大值。
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * O(n)
     */
    private fun maxSubArray(array:IntArray):Int{
        var reslut = 0
        for(i in 1 until array.size){
            array[i] = Math.max(array[i-1]+array[i],array[i])
            reslut = Math.max(reslut,array[i])
        }
        return reslut
    }
}