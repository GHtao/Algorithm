package com.gt.lk.every_day

import com.gt.lk.bean.TreeNode


/**
 * time 2020/10/28 0028
 * author GT
 */
object Day1028 {
    @JvmStatic
    fun main(args: Array<String>) {
        val tree1 = TreeNode().apply {
            value = 3
            left = TreeNode().apply {
                value = 4
                left = TreeNode().apply {
                    value = 1
                }
                right = TreeNode().apply {
                    value = 2
                }
            }
            right = TreeNode().apply {
                value = 5
            }
        }
        val tree2 = TreeNode().apply {
            value = 4
            left = TreeNode().apply {
                value = 1
            }
            right = TreeNode().apply {
                value = 2
            }
        }
        val result = isSubTree(tree1,tree2)
        println("result:$result")
    }

    /**
     * 判断sub是不是root的子树
     * 递归遍历
     * 注意利用两个方法来处理
     */
    private fun isSubTree(root: TreeNode?, sub: TreeNode?):Boolean{
        if(sub == null) return false
        if(root == null) return false
        return recur(root,sub) || isSubTree(root.left,sub) || isSubTree(root.right,sub)
    }

    private fun recur(tree1:TreeNode?,tree2:TreeNode?):Boolean{
        if(tree2 == null) return true
        if(tree1 == null || tree1.value != tree2.value) return false
        return recur(tree1.left,tree2.left) && recur(tree1.right,tree2.right)
    }
}