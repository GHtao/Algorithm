package com.gt.lk.every_day

import com.gt.lk.bean.ListNode
import com.gt.lk.bean.TreeNode
import com.gt.lk.utils.ListNodeUtil
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


/**
 * time 2020/12/1 0001
 * author GT
 */
object Day1201 {
    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(intArrayOf(0,1,0),intArrayOf(1,1,1),intArrayOf(1,1,1))
//        setZeros(matrix)
//        matrix.forEach {
//            println("result:${it.contentToString()}")
//        }

//        val result = multiply(10,18)
//        println("result:$result")

        val root = TreeNode().apply {
            value = 1
            left = TreeNode().apply {
                value = 2
                left = TreeNode().apply {
                    value = 4
                    left = TreeNode().apply {
                        value = 8
                    }
                }
                right = TreeNode().apply {
                    value = 5
                }
            }
            right = TreeNode().apply {
                value = 3
                right = TreeNode().apply {
                    value = 7
                }
            }
        }
//        val result = listOfDepth(root)
//        result.forEach {
//            ListNodeUtil.printLinkNode(it)
//        }

        val result = patternMatching("abba","dogcatcatdog")
        println("result:$result")
    }

    /**
     * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零
     */
    private fun setZeros(matrix:Array<IntArray>){
        val row = HashSet<Int>()
        val line = HashSet<Int>()
        for(i in matrix.indices){
            for(j in matrix[i].indices){
                if(matrix[i][j] == 0) {
                    row.add(i)
                    line.add(j)
                }
            }
        }
        row.forEach {
            for(i in matrix[it].indices){
                matrix[it][i] = 0
            }
        }
        line.forEach {
            for(i in matrix.indices){
                matrix[i][it] = 0
            }
        }
    }

    /**
     * 递归乘法。 写一个递归函数，不使用 * 运算符，
     * 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些
     *
     * 不用递归 直接右移相加
     */
    private fun multiply(a:Int,b:Int):Int{
        var min = Math.min(a,b)
        val max = Math.max(a,b)
        var count = 0
        var result = 0
        while (min != 0){
            if(min and 1 == 1) result += max.shl(count)
            count++
            min = min.shr(1)
        }
        return result
    }

    /**
     * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。
     * 返回一个包含所有深度的链表的数组
     */
    private fun listOfDepth(root: TreeNode):ArrayList<ListNode>{
        val array = ArrayList<ListNode>()
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        while (queue.isNotEmpty()){
            val size = queue.size
            var node = ListNode()
            val list = node
            for(i in 0 until size){
                val tree = queue.poll()
                val temp = ListNode().apply { value = tree.value }
                node.next = temp
                node = temp

                if(tree.left != null) queue.add(tree.left!!)
                if(tree.right != null) queue.add(tree.right!!)
            }
            array.add(list.next!!)
        }
        return array
    }

    /**
     * 你有两个字符串，即pattern和value。
     * pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
     * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），
     * 该字符串也匹配像"a"、"ab"和"b"这样的模式。
     * 但需注意"a"和"b"不能同时表示相同的字符串。
     *
     * 编写一个方法判断value字符串是否匹配pattern字符串
     */
    private fun patternMatching(pattern:String,value:String):Boolean{
        var tPattern = pattern
        var count_a = 0
        var count_b = 0
        for (ch in  tPattern.toCharArray()) {
            if (ch == 'a') {
                ++count_a
            } else {
                ++count_b
            }
        }
        if (count_a < count_b) {
            val temp = count_a
            count_a = count_b
            count_b = temp
            val array = tPattern.toCharArray()
            for (i in array.indices ) {
                array[i] = if(array[i] == 'a')  'b' else 'a'
            }
            tPattern = String(array)
        }
        if (value.isEmpty()) {
            return count_b == 0
        }
        if (tPattern.isEmpty()) {
            return false
        }
        var len_a = 0
        while (count_a * len_a <= value.length){
            val rest = value.length - count_a * len_a
            if ((count_b == 0 && rest == 0) || (count_b != 0 && rest % count_b == 0)) {
                val len_b = (if(count_b == 0)  0 else rest / count_b)
                var pos = 0
                var correct = true
                var value_a = ""
                var value_b = ""
                for ( ch in  tPattern.toCharArray()) {
                    if (ch == 'a') {
                        val sub = value.substring(pos, pos + len_a)
                        if (value_a.isEmpty()) {
                            value_a = sub
                        } else if (value_a != sub) {
                            correct = false
                            break
                        }
                        pos += len_a
                    } else {
                        val sub = value.substring(pos, pos + len_b)
                        if (value_b.isEmpty()) {
                            value_b = sub
                        } else if (value_b != sub) {
                            correct = false
                            break
                        }
                        pos += len_b
                    }
                }
                if (correct && value_a != value_b) {
                    return true
                }
            }
            len_a++
        }
        return false
    }
}