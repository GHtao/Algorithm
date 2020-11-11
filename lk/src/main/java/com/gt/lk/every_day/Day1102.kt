package com.gt.lk.every_day

import com.gt.lk.bean.TreeNode
import java.util.*
import kotlin.collections.ArrayList


/**
 * time 2020/11/2 0002
 * author GT
 */
object Day1102 {
    @JvmStatic
    fun main(args: Array<String>) {
        val array = arrayOf(
            intArrayOf(1,   4,  7, 11, 15),
            intArrayOf(2,   5,  8, 12, 19),
            intArrayOf(3,   6,  9, 16, 22),
            intArrayOf(10, 13, 14, 17, 24),
            intArrayOf(18, 21, 23, 26, 30))

        val result = findNumIn2DArray(array,30)
//        val result = findNumIn2DArray(array,20,0,0)
//        println("result:$result")

        val root = TreeNode().apply {
            value = 3
            left = TreeNode().apply {
                value = 9
                left = TreeNode().apply {
                    value = 4
                }
                right = TreeNode().apply {
                    value = 6
                }
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
        val treeResult = levelOrder(root)
        treeResult.forEach {
            print("[")
            it.forEach { v->
                print("$v,")
            }
            print("]")
            println()
        }
    }

    /**
     * 从左右和上下有序的二维数组成查找 target是否存在
     * 递归
     */
    private fun findNumIn2DArray(array: Array<IntArray>,target:Int,index1:Int,index2:Int):Boolean{
        if(index1 >array.size-1 || index2 > array[index1].size-1) return false

        if(array[index1][index2] == target) return true
        if(array[index1][index2] > target)  return false

        return findNumIn2DArray(array,target, index1, index2 +1) ||
                findNumIn2DArray(array,target, index1 +1, index2)
    }

    /**
     * 循环的方式
     * 从右上角开始查找
     */
    private fun findNumIn2DArray(array: Array<IntArray>,target:Int):Boolean{

        val rows = array.size
        var row = 0
        var column = array[row].size - 1
        while (row < rows && column >=0){
            when {
                array[row][column] == target -> {
                    return true
                }
                array[row][column] < target -> {
                    row++
                    if(row >= array.size) return false
                    column = array[row].size - 1
                }
                else -> {
                    column--
                }
            }
        }
        return false
    }

    /**
     * 按照之字形顺序打印二叉树
     * 第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印..
     * bfs
     */
    private fun levelOrder(root: TreeNode?):ArrayList<LinkedList<Int>>{
        val result = ArrayList<LinkedList<Int>>()
        val queue = LinkedList<TreeNode>()
        if(root != null) queue.add(root)
        //这样可以按照层来遍历 递归相当于深度优先了
        while (queue.isNotEmpty()){
            val temp = LinkedList<Int>()
            for(i in queue.size downTo 1){
                val node = queue.poll()
                if(result.size % 2== 0) temp.addLast(node.value) else temp.addFirst(node.value)
                if(node.left != null) queue.add(node.left!!)
                if(node.right != null) queue.add(node.right!!)
            }
            result.add(temp)
        }
        return result
    }
}