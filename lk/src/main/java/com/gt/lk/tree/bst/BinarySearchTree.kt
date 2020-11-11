package com.gt.lk.tree.bst

import com.gt.lk.bean.TreeNode

/**
 * time 2020/6/17 0017
 * author GT
 * bst 二叉搜索树
 * 左子树的都比当前节点小，右子树都比当前节点大
 */
class BinarySearchTree {

    /**
     * 节点的数值加上value
     */
    fun increaseVal(root: TreeNode?, value:Int ){
        if(root == null) return
        root.value += value

        increaseVal(root.left,value)
        increaseVal(root.right,value)
    }

    /**
     * 判断两个树是否一致
     */
    fun isSameTree(tree1: TreeNode?, tree2: TreeNode?):Boolean{
        if(tree1 == null && tree2 == null) return true
        if(tree1 == null || tree2 == null) return false
        return if(tree1.value == tree2.value) {
            isSameTree(tree1.left,tree2.left) && isSameTree(tree1.right,tree2.right)
        }else{
            false
        }
    }

    /**
     * 判断一个bst树是否合法
     * 当前节点需要大于左子树的最大值
     * 当前节点需要小于右子树的最小值
     */
    fun isValidBst(root: TreeNode?, min: TreeNode?=null, max: TreeNode?=null):Boolean{
        if(root == null) return true
        if(min != null && root.value <= min.value) return false
        if(max != null && root.value >= max.value) return false
        return  isValidBst(root.left,min,root) && isValidBst(root.right,root,max)
    }

    /**
     * 查找一个数是否存在
     */
    fun isInBst(root: TreeNode?, value:Int):Boolean{
        if(root == null) return false
        if(root.value == value) return true
        return isInBst(root.left,value) || isInBst(root.right,value)
    }
    /**
     * 插入一个节点
     */
    fun insertTree(root: TreeNode?, value:Int): TreeNode {
        if(root == null) return TreeNode().apply { this.value = value }
        //先要找到插入的位置
        if(root.value == value) return root //值相等就直接返回
        if(root.value < value){
            root.right = insertTree(root.right,value)
        }else{
            root.left = insertTree(root.left,value)
        }
        return root
    }

    /**
     * 删除一个节点 删除之后要保证结构不被破坏
     * 1 没有左右节点 直接删除
     * 2 只有左 或者是只有右 删除之后将左右节点接上
     * 3 需要找到 必须找到左子树中最大的那个节点，或者右子树中最小的那个节点来接替自己
     */
    fun delete(root: TreeNode?, value:Int): TreeNode?{
        if(root == null) return null
        if(root.value == value){
            if(root.left == null) return root.right
            if(root.right == null) return root.left

            val minNode = min(root.right!!)
            root.value = minNode.value
            root.right = delete(root.right,minNode.value)
        }else if(root.value > value){
            delete(root.left,value)
        }else{
            delete(root.right,value)
        }
        return root
    }

    /**
     * 获取最小的节点
     */
    fun min(root: TreeNode): TreeNode {
        var temp = root
        while(temp.left != null) temp = temp.left!!
        return temp
    }

    /**
     * 遍历
     */
    fun traverse(root: TreeNode?, type:Int, list:ArrayList<Int>){
        when(type){
            0->{
                //先序遍历 先root 然后左子树 然后右子树
                if(root != null) list.add(root.value)
                if(root?.left != null) traverse(root.left,type,list)
                if(root?.right != null) traverse(root.right,type,list)
            }
            1->{ //二叉搜索树中序遍历直接就是有序数组
                //中序遍历 先左 再中 在右
                if(root?.left != null) traverse(root.left,type,list)
                if(root != null) list.add(root.value)
                if(root?.right != null) traverse(root.right,type,list)
            }
            2->{
                //后序遍历 先左 再右 再根
                if(root?.left != null) traverse(root.left,type,list)
                if(root?.right != null) traverse(root.right,type,list)
                if(root != null) list.add(root.value)
            }
        }
    }
}