package com.gt.lk.every_day

import com.gt.lk.bean.TreeNode
import java.util.*
import kotlin.collections.ArrayList

/**
 * time 2020/11/4 0004
 * author GT
 */
object Day1104 {
    fun main(args: Array<String>) {
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
                    @JvmStatic
                    value = 7

                }
            }
        }
//        val list = printTree(root)
//        println("list:${list.toArray()!!.contentToString()}")
        val array = intArrayOf(1,3,2,6,5)
        val result = verifyPostOrder(array)
        println("result:$result")
    }

    /**
     * 打印二叉树的数组
     * 每层从左到右打印
     */
    private fun printTree(treeNode: TreeNode):ArrayList<Int>{
        val list = ArrayList<Int>()
        val queue = LinkedList<TreeNode>()
        queue.add(treeNode)
        while(!queue.isEmpty()){
            for(i in queue.indices){
                val temp = queue.poll()
                list.add(temp.value)
                if(temp.left != null) queue.add(temp.left!!)
                if(temp.right != null) queue.add(temp.right!!)
            }
        }
        return list
    }


    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果
     */
    private fun verifyPostOrder(array:IntArray):Boolean{
        return recur(array,0,array.size-1)
    }

    /**
     * i 左子树起始位置
     * j 根节点位置
     */
    private fun  recur(array:IntArray, i:Int, j:Int):Boolean {
        //只有一个元素的时候 就是正确的树
        if(i >= j) return true
        var p = i
        //查找第一个比root大的位置
        while(array[p] < array[j]) p++
        val m = p//记录位置
        //继续查找
        while(array[p] > array[j]) p++
        //如果p不等于j说明有右子树有比root小的值
        //递归查找 左右子树
        return p == j && recur(array, i, m - 1) && recur(array, m, j - 1)
    }

}