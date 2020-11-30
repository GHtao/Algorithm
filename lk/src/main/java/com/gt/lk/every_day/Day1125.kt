package com.gt.lk.every_day

import com.gt.lk.bean.ListNode
import com.gt.lk.bean.TreeNode
import com.gt.lk.utils.ListNodeUtil
import java.util.*

/**
 * time 2020/11/25 0025
 * author GT
 */
object Day1125 {
    @JvmStatic
    fun main(args: Array<String>) {
        val list1 = ListNode().apply {
            value = 0
            next = ListNode().apply {
                value = 9
                next = ListNode().apply {
                    value = 1
                    next = ListNode().apply {
                        value = 2
                        next = ListNode().apply {
                            value = 4
                        }
                    }
                }
            }
        }
        val list2 = ListNode().apply {
            value = 3
            next = ListNode().apply {
                value = 8
                next = ListNode().apply {
                    value = 7
                }
            }
        }
//        val result = getIntersectionNode(list1,list2)
//        if(result != null){
//            ListNodeUtil.printLinkNode(result)
//        }else{
//            println("result:null")
//        }

//        val minStack = MinStack().apply {
//            push(9)
//            push(10)
//            push(7)
//            push(1)
//
//            println("min1:${min()}")
//            println("pop1:${pop()}")
//            println("min2:${min()}")
//            println("pop2:${pop()}")
//            println("top:${top()}")
//            println("min3:${min()}")
//
//        }

        val result = twoSum(intArrayOf(10,26,30,31,47,60),40)
        println("result:${result.contentToString()}")
    }

    /**
     * 输入两个链表，找出它们的第一个公共节点。
     *
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     */
    private fun getIntersectionNode(list1:ListNode?,list2:ListNode?):ListNode?{
        var node1 = list1
        var node2 = list2
        while (node1 != node2){//等于在后面添加了一个null节点 没有相同的时候null是相同的
            node1 = if(node1 != null){
                node1.next
            }else{
                list2
            }
            node2 = if(node2 != null){
                node2.next
            }else{
                list1
            }
        }
        return node1
    }

    /**
     *请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
     * 调用 min、push 及 pop 的时间复杂度都是 O(1)。
     */
    class MinStack{
        private val stack = Stack<Int>()
        //辅助栈 保存min的倒叙
        private val minStack = Stack<Int>()
        fun push(x:Int){
            stack.push(x)
            if(minStack.isEmpty() || minStack.peek() > x){
                minStack.push(x)
            }
        }

        fun pop():Int{
            return stack.pop()
        }

        fun top():Int{
            return stack.peek()
        }

        fun min():Int{
            return minStack.pop()
        }
    }

    /**
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。
     * 如果有多对数字的和等于s，则输出任意一对即可
     */
    private fun twoSum(array: IntArray,s:Int):IntArray{
        val result = IntArray(2)
        var i = 0
        var j = array.size -1
        while(i<j){
            val sum = array[i] + array[j]
            when{
                sum > s -> j--
                sum < s -> i++
                else ->{
                    result[0] = array[i]
                    result[1] = array[j]
                    return result
                }
            }
        }
        return result
    }

    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
     * 对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）
     *
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉树中
     */
    private fun lowestCommonAncestor(root:TreeNode?,p:TreeNode,q:TreeNode):TreeNode?{
        if(root == null || root == p || root == q) return root
        val left = lowestCommonAncestor(root.left,p,q)
        val right = lowestCommonAncestor(root.right,p,q)
        if(left == null) return right
        if(right == null) return left
        return root
    }
}