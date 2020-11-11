package com.gt.lk.tree.bst

import com.gt.lk.bean.TreeNode

/**
 * time 2020/6/17 0017
 * author GT
 */
object BinarySearchTest {
    private val binarySearchTree = BinarySearchTree()
    @JvmStatic
    fun main(args: Array<String>) {
        var root: TreeNode? = null
        root =  binarySearchTree.insertTree(root,5)
        root =  binarySearchTree.insertTree(root,3)
        root =  binarySearchTree.insertTree(root,7)
        root =  binarySearchTree.insertTree(root,1)
        root =  binarySearchTree.insertTree(root,4)
        root =  binarySearchTree.insertTree(root,6)
        root =  binarySearchTree.insertTree(root,8)
        root =  binarySearchTree.insertTree(root,2)
        val arrayList = ArrayList<Int>()
        binarySearchTree.traverse(root, 2,arrayList)
        println(arrayList.toString())
//        val isValid = binarySearchTree.isValidBst(root)
//        root = binarySearchTree.delete(root,7)
//        val isIn = binarySearchTree.isInBst(root,10)
//        println("isIn:$isIn")
//
//        println("isValid:$isValid")
    }
}