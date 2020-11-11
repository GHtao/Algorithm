package com.gt.lk.every_day

import com.gt.lk.bean.TreeNode
import java.security.PrivateKey
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min

/**
 * time 2020/11/6 0006
 * author GT
 */
object Day1106 {
    private val result = ArrayList<String>()
    private val treeResult = ArrayList<ArrayList<Int>>()
    @JvmStatic
    fun main(args: Array<String>) {
//        permutation("abc")
//        result.forEach {
//           print("$it,")
//        }

//        val profit = maxProfit(intArrayOf(7,1,5,3,6,4))
//        println("profit:$profit")

//        val validate = validateStackSequence(intArrayOf(1,2,3,4,5), intArrayOf(1,2,4,3,5))
//        println("validate:$validate")

        val root = TreeNode().apply {
            value = 5
            left = TreeNode().apply {
                value = 4
                left = TreeNode().apply {
                    value = 11
                    left = TreeNode().apply {
                        value = 7
                    }
                    right = TreeNode().apply {
                        value = 2
                    }
                }

            }
            right = TreeNode().apply {
                value = 8
                left = TreeNode().apply {
                    value = 13

                }
                right = TreeNode().apply {
                    value = 4
                    left = TreeNode().apply {
                        value = 5
                    }
                    right = TreeNode().apply {
                        value = 1
                    }
                }
            }
        }
        pathNum(root,22)
        treeResult.forEach {
            println("tree:${it.toArray().contentToString()}")
        }
    }

    /**
     * 打印出该字符串中字符的所有排列
     */
    private fun permutation(str:String){
        val temp = ArrayList<Char>()
        recur(str,temp)
    }

    private fun recur(str:String,list:ArrayList<Char>){
        if(list.size == str.length){//长度相等时保留结果
            var s = ""
            list.forEach {
                s += it
            }
            result.add(s)
        }
        for(i in str.indices){
            //如果允许重复的字母 这里判断可以再添加一个元素所在的位置用HashMap
            if(list.contains(str.toCharArray()[i])){
                continue//已包含结果 就跳过
            }
            list.add(str.toCharArray()[i])
            recur(str,list)
            list.remove(str.toCharArray()[i])
        }
    }

    /**
     * 把某股票的价格按照时间先后顺序存储在数组中 可获取的最大利润
     * [7,1,5,3,6,4]
     * 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5
     */
    private fun maxProfit(prices:IntArray):Int{
        //保留前面的最低价格
        var cost = Int.MAX_VALUE
        //保留前几天的最大利润
        var profit = 0
        prices.forEach {
            cost = min(cost,it)
            profit = max(profit,it - cost)
        }
        return profit
    }

    /**
     * 第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
     * 序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
     * 但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列
     */
    private fun validateStackSequence(push:IntArray,pop:IntArray):Boolean{
        val stack = Stack<Int>()
//        val map = HashMap<Int,Int>()
//        var lastPushIndex = 0
//        push.forEachIndexed { index, i ->
//            map[i] = index
//        }
//        pop.forEach {
//            //包含pop的首位
//            if(stack.contains(it)){
//                if(stack.pop() != it){//栈顶的不相等 就失败
//                    return false
//                }
//            }else{
//                //找到出栈元素在入栈队列里的索引
//                val index = map[it]!!
//                //上次入栈的位置  上次入栈的位置比这次要入栈的元素靠后 就是错误的
//                if(lastPushIndex > index) return false
//                for(i in lastPushIndex .. index){
//                    stack.push(push[i])
//                }
//                //弹出栈顶
//                stack.pop()
//                //入栈位置后移一位
//                lastPushIndex = index+1
//            }
//        }
//        return true

        var i = 0
        push.forEach {
            stack.push(it)
            while(!stack.isEmpty() && stack.peek() == pop[i]){
                stack.pop()
                i++
            }
        }
        return stack.isEmpty();
    }

    /**
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
     * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径
     */
    private fun pathNum(root:TreeNode,num:Int){
        val temp = ArrayList<Int>()
        pathNum(root,num,temp)
    }
    private fun pathNum(root:TreeNode,num:Int,list:ArrayList<Int>){
        list.add(root.value)
        if(root.left == null && root.right == null){
            var sum = 0
            list.forEach {
                sum += it
            }
            if(sum == num) treeResult.add(ArrayList(list))
        }else{
            if(root.left != null){
                pathNum(root.left!!,num,list)
            }
            if(root.right != null){
                pathNum(root.right!!,num,list)
            }
        }
        list.remove(root.value)
    }
}