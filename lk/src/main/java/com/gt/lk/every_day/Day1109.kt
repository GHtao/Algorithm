package com.gt.lk.every_day

import com.gt.lk.bean.TreeNode
import com.sun.xml.internal.fastinfoset.util.StringArray
import jdk.nashorn.internal.ir.ContinueNode
import java.lang.StringBuilder

/**
 * time 2020/11/9 0009
 * author GT
 */
object Day1109 {
    @JvmStatic
    fun main(args: Array<String>) {
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
                value = 5
            }
        }
//        val result = treeToDoublyList(root)
//        println("result:$result")

//        val minNum = minNum(intArrayOf(3,30,34,5,9))
        val minNum = fastSortMinNum(intArrayOf(3,30,34,5,9))

        println("min num:$minNum")
    }

    /**
     * 将二叉搜索树 转换成双向链表
     * 要求不能创建任何新的节点，只能调整树中节点指针的指向
     */
    private fun treeToDoublyList(root:TreeNode?):TreeNode{
        val list = ArrayList<TreeNode>()
        treeToDoublyList(root,list)
        list.mapIndexed { index, treeNode ->
            when (index) {
                0 -> {
                    treeNode.left = list[1]
                    treeNode.right = list[list.size-1]
                }
                list.size-1 -> {
                    treeNode.left = list[0]
                    treeNode.right = list[index-1]
                }
                else -> {
                    treeNode.left = list[index+1]
                    treeNode.right = list[index-1]
                }
            }
        }
        return list[0]
    }

    private fun treeToDoublyList(root: TreeNode?,array:ArrayList<TreeNode>){
        if(root == null) return
        treeToDoublyList(root.left,array)
        array.add(root)
        treeToDoublyList(root.right,array)
    }

    /**
     * 数组中的数字 所有可能的排列组合的最小值
     * 数据可能溢出 用string表示
     */
    private var minNum = "0"
    private fun minNum(array:IntArray):String{
        val temp = ArrayList<Int>()
        minNum(array,temp,minNum)
        return minNum
    }
    private fun minNum(array: IntArray,list:ArrayList<Int>,min:String){
        if(list.size == array.size){
            val sb = StringBuilder()
            list.forEach {
                sb.append(it)
            }
            if(minNum == "0"){
                minNum = sb.toString()
            }else{
                if(minNum > sb.toString()){
                    minNum = sb.toString()
                }
            }
            return
        }
        for(i in array.indices){
            if(list.contains(array[i])){
                continue
            }
            list.add(array[i])
            minNum(array,list,minNum)
            list.remove(array[i])
        }
    }

    /**
     * 快速排序法 排列组合最小数字
     * 字符串 x+y > y+x 就需要将y排在x前面
     */
    private fun fastSortMinNum(array: IntArray):String{
        val strArray = Array(array.size){""}
        array.mapIndexed { index, i ->
            strArray[index] = i.toString()
        }
        fastSortMinNum(strArray,0,array.size-1)
        return strArray.contentToString()
    }
    private fun fastSortMinNum(array: Array<String>,l:Int,r:Int){
        if(l >= r) return
        var left = l
        var right = r
        val value = array[l]
        while (left < right){
            while ((array[right]+value >= value+array[right]) && left < right) right--
//            while (value <= array[right] && left < right) right--
            while ((array[left]+value <= value+array[left]) && left < right) left++
//            while (value >= array[left] && left < right) left++
            if(left <right){
                val temp = array[left]
                array[left] = array[right]
                array[right] = temp
            }
        }
        array[l] = array[left]
        array[left] = value
        fastSortMinNum(array,l,left - 1)
        fastSortMinNum(array,left+1,r)
    }
}