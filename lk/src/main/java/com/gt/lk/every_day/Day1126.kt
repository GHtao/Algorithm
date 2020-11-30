package com.gt.lk.every_day

import com.gt.lk.bean.TreeNode

/**
 * time 2020/11/26 0026
 * author GT
 */
object Day1126 {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode().apply {
            value =6
            left = TreeNode().apply {
                value = 2
                left = TreeNode().apply {
                    value = 0
                }
                right = TreeNode().apply {
                    value = 4
                    left = TreeNode().apply {
                        value = 3
                    }
                    right = TreeNode().apply {
                        value = 5
                    }
                }
            }
            right = TreeNode().apply {
                value = 8
                left = TreeNode().apply {
                    value = 7
                }
                right = TreeNode().apply {
                    value = 9
                }
            }
        }
//        val p = TreeNode().apply { value = 3 }
//        val q = TreeNode().apply { value = 5 }
//        val result = lowestCommonAncestor(root, p, q)
//        println("result:${result?.value}")

        val result = add(2,3)
        println("result:$result")
    }

    /**
     * 二叉搜索树的公共祖先
     */
    private fun lowestCommonAncestor(root:TreeNode?,p:TreeNode,q:TreeNode):TreeNode?{
        if(root == null) return root
        if(root.value < p.value && root.value < q.value){
            return lowestCommonAncestor(root.right,p,q)
        }else if(root.value > p.value && root.value > q.value){
            return lowestCommonAncestor(root.left,p,q)
        }
        return root
    }

    /**
     * 求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号
     */
    private fun add(a:Int,b:Int):Int{
        var tempA = a
        var tempB = b
        while(tempB != 0) { // 当进位为 0 时跳出
            val c = (tempA and tempB) shl  1  // c = 进位
            tempA = tempA xor tempB // a = 非进位和
            tempB = c // b = 进位
        }
        return tempA
    }
}