package com.gt.test

import com.gt.lk.bean.TreeNode

/**
 * time 2020/7/8 0008
 * author GT
 */
object TreeUtil {
    enum class TreeTraverse{
        FIRST,//先序
        MID,//中序
        LAST//后序
    }
    /**
     * 根据数组生成一个树
     */
    fun createTree(array:IntArray,index:Int=0): TreeNode {
//        if(array[index] == Int.MIN_VALUE){
//            return null
//        }
        val root = TreeNode().apply { value = array[index] }
        if(array.size > index*2+1) root.left = createTree(array,index*2+1)
        if(array.size > index*2+2) root.right = createTree(array,index*2+2)
        return root
    }

    /**
     * 遍历
     */
    fun traverse(root: TreeNode?, type:TreeTraverse, list:ArrayList<Int>){
        when(type){
            TreeTraverse.FIRST->{
                //先序遍历 先root 然后左子树 然后右子树
                if(root != null) list.add(root.value)
                if(root?.left != null) traverse(root.left,type,list)
                if(root?.right != null) traverse(root.right,type,list)
            }
            TreeTraverse.MID->{ //二叉搜索树中序遍历直接就是有序数组
                //中序遍历 先左 再中 在右
                if(root?.left != null) traverse(root.left,type,list)
                if(root != null) list.add(root.value)
                if(root?.right != null) traverse(root.right,type,list)
            }
            TreeTraverse.LAST->{
                //后序遍历 先左 再右 再根
                if(root?.left != null) traverse(root.left,type,list)
                if(root?.right != null) traverse(root.right,type,list)
                if(root != null) list.add(root.value)
            }
        }
    }
}