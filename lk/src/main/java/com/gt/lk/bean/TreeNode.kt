package com.gt.lk.bean


/**
 * time 2020/6/17 0017
 * author GT
 */
class TreeNode {
    var value = Int.MIN_VALUE
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun equals(other: Any?): Boolean {
        if(other !is TreeNode) return false
        return (other as TreeNode).value == value
    }
    override fun hashCode(): Int {
        return value
    }
}

