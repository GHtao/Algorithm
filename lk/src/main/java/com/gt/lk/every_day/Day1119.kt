package com.gt.lk.every_day

import com.gt.lk.bean.TreeNode

/**
 * time 2020/11/19 0019
 * author GT
 */
object Day1119 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = numWays(5)
//        println("result:$result")
        val root = TreeNode().apply {
            value = 3
            left = TreeNode().apply {
                value = 9
            }
            right = TreeNode().apply {
                value = 20
                left = TreeNode().apply {
                    value = 15
                }
                right = TreeNode().apply {
                    value = 7
                }
            }
        }
//        val result = maxDepth(root)
//        println("result:$result")

        val result = printNumbers(3)
        println("result:${result.toArray().contentToString()}")
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。
     * 求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     */
    private fun numWays(n:Int):Int{
        var a = 0
        var b = 1
        var sum = 0
        for(i in 1..n){
            sum =a+b
            a= b
            b=sum
        }
        return sum
    }

    /**
     * 输入一棵二叉树的根节点，求该树的深度。
     * 从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，
     * 最长路径的长度为树的深度
     */
    private fun maxDepth(root:TreeNode?):Int{
        if(root == null) return 0
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1
    }

    /**
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
     * 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999
     */
    private fun printNumbers(n:Int):ArrayList<Int>{
        val list = ArrayList<Int>()
        val size = (Math.pow(10.toDouble(),n.toDouble())).toInt()-1
        for(i in 1..size){
            list.add(i)
        }
        return list
    }

}