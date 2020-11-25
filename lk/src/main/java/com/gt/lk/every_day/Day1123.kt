package com.gt.lk.every_day

import com.gt.lk.bean.ListNode
import com.gt.lk.bean.TreeNode
import java.util.*
import kotlin.collections.ArrayList


/**
 * time 2020/11/23 0023
 * author GT
 */
object Day1123 {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode().apply {
            value = 1
            left = TreeNode().apply {
                value = 2
                left = TreeNode().apply {
                    value = 4
                }
                right = TreeNode().apply {
                    value = 3
                }
            }
            right = TreeNode().apply {
                value = 2
                left = TreeNode().apply {
                    value = 3
                }
                right = TreeNode().apply {
                    value = 4
                }
            }
        }
//        val result = kthLargest(root,2)
//        println("result:$result")

        val list1 = ListNode().apply {
            value = 1
            next = ListNode().apply {
                value = 5
                next = ListNode().apply {
                    value = 9
                }
            }
        }
        val list2 = ListNode().apply {
            value = 2
            next = ListNode().apply {
                value = 3
                next = ListNode().apply {
                    value = 4
                    next = ListNode().apply {
                        value = 6
                        next = ListNode().apply {
                            value = 7
                            next = ListNode().apply {
                                value = 8
                            }
                        }
                    }
                }
            }
        }
//        val result = mergeTwoList(list1,list2)
//        ListNodeUtil.printLinkNode(result)

//        val result = levelOrder(root)
//        result.forEach {
//            println("result:${it.contentToString()}")
//        }

//        val result = majorityElement(intArrayOf(4,4,3,4,1,5))
//        println("result:$result")

        val result = isSymmetric(root)
        println("result:$result")
    }

    /**
     * 给定一棵二叉搜索树，请找出其中第k大的节点
     * 中序遍历 找到倒数第k个数
     *
     */
    private fun kthLargest(root:TreeNode?,k:Int):Int{
        val array = ArrayList<Int>()
        kthLargest(root, array)
        return array[array.size-k]
    }
    private fun kthLargest(root: TreeNode?,list:ArrayList<Int>){
        if(root == null) return
        kthLargest(root.left,list)
        list.add(root.value)
        kthLargest(root.right,list)
    }

    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的
     */
    private fun mergeTwoList(list1:ListNode?,list2:ListNode?):ListNode{
        var next1 = list1
        var next2 = list2
        val result = ListNode()
        var temp = result
        while (next1 != null && next2 != null){
            if(next1.value <= next2.value){
                temp.next = next1
                next1 = next1.next
            }else{
                temp.next = next2
                next2 = next2.next
            }
            temp = temp.next!!
        }
        if(next1 != null){
            temp.next = next1
        }
        if(next2 != null){
            temp.next = next2
        }
        return result.next!!
    }

    /**
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行
     */
    private fun levelOrder(root:TreeNode?):ArrayList<IntArray>{
        val list = ArrayList<IntArray>()
        val queue = LinkedList<TreeNode>()
        queue.add(root!!)
        while(!queue.isEmpty()){
            val size = queue.size
            val array = IntArray(size)
            for(i in 0 until size){
                val temp = queue.poll()
                array[i] = temp.value
                if(temp.left != null) queue.add(temp.left!!)
                if(temp.right != null) queue.add(temp.right!!)
            }
            list.add(array)
        }
        return list
    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
     * 超过半数才可以用投票这种方式
     */
    private fun majorityElement(array: IntArray):Int{
        var x = 0
        var votes = 0
        array.forEach {num->
            if(votes == 0) x = num
            votes += if(num == x) 1 else -1
        }
        return x
    }

    /**
     * 用来判断一棵二叉树是不是对称的。
     * 如果一棵二叉树和它的镜像一样，那么它是对称的
     */
    private fun isSymmetric(treeNode: TreeNode?):Boolean{
        if(treeNode == null) return true
        return isSymmetric(treeNode.left,treeNode.right)
    }
    private fun isSymmetric(l: TreeNode?,r: TreeNode?):Boolean{
        if(l == null && r == null ) return true
        if(l == null || r == null || l.value != r.value) return false
        return isSymmetric(l.left,r.right) && isSymmetric(l.right,r.left)
    }
}