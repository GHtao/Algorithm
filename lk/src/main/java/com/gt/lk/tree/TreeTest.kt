package com.gt.test

import com.gt.lk.bean.TreeNode
import java.util.*
import kotlin.collections.ArrayList

/**
 * time 2020/7/8 0008
 * author GT
 */
object TreeTest {
    @JvmStatic
    fun main(args: Array<String>) {
//        generate(4).forEach {
//            println(it)
//        }
//        println(generateNum(4))
        val list = ArrayList<Int>()
        val root =
            TreeUtil.createTree(intArrayOf(2,1,3))
        println(validTree(root))
//        TreeUtil.traverse(root,TreeUtil.TreeTraverse.FIRST,list)
//        println(list.toString())
    }

    /**
     * 验证树是不是一个二叉搜索树
     */
    private fun validTree(root: TreeNode?):Boolean{
        if(root == null) return true
        if(root.left != null && root.left!!.value != Int.MIN_VALUE ){
            if(root.left!!.value > root.value) return false
        }
        if(root.right != null && root.right!!.value != Int.MIN_VALUE){
            if(root.right!!.value < root.value)return false
        }
        return validTree(root.left) &&  validTree(root.right)
    }
    /**
     * 生成所有可能的二叉搜索树
     */
    private fun generate(n:Int):List<TreeNode?>{
        if(n == 0) return LinkedList()
        return generate(1,n)
    }

    private fun generate(start:Int,end:Int):LinkedList<TreeNode?>{
        val linkedList = LinkedList<TreeNode?>()
        if(start > end){
            linkedList.add(null)
            return linkedList
        }
        for(i in start .. end){
            val left = generate(start,i-1)
            val right = generate(i+1,end)
            left.forEach { leftNode->
                right.forEach{ rightNode->
                    //合并树
                    val root = TreeNode().apply { value = i }
                    root.left = leftNode
                    root.right = rightNode
                    linkedList.add(root)
                }
            }
        }
        return linkedList
    }

    /**
     * 返回二叉搜索树的个数
     */
    private fun generateNum(n:Int):Int{
        if(n == 0) return 0
        return generateNum(1,n)
    }

    private fun generateNum(start:Int,end:Int):Int{
        var num = 0
        if(start > end){
            return num
        }
        for(i in start .. end){
            val left = generate(start,i-1)
            val right = generate(i+1,end)
            left.forEach { leftNode->
                right.forEach{ rightNode->
                    //合并树
                  num++
                }
            }
        }
        return num
    }
}