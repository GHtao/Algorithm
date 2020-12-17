package com.gt.lk.every_day

import com.gt.lk.bean.ListNode
import com.gt.lk.bean.TreeNode
import com.gt.lk.utils.ListNodeUtil

/**
 * time 2020/12/10 0010
 * author GT
 */
object Day1210 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = generateParenthesis(3)
//        result.forEach {
//            println("result:$it")
//        }
//        val node = ListNode().apply { value = 3 }
//        val node1 = ListNode().apply { value = 2 }
//        val node2 = ListNode().apply { value = 0 }
//        val node3 = ListNode().apply { value = 4 }
//        node.next = node1
//        node1.next = node2
//        node2.next = node3
//        node3.next = node1
//        val result = detectCycle(node)
//        result?.let {
//            println("result:${it.value}")
//        }
        val tree1 = TreeNode().apply {
            value = 1
            left = TreeNode().apply { value = 2 }
            right = TreeNode().apply { value = 3 }
        }
        val tree2 = TreeNode().apply {
            value = 8
        }
        val result = checkSubTree(tree1,tree2)
        println("result:$result")
    }

    /**
     * 打印n对括号的所有合法的（例如，开闭一一对应）组合
     * n=3
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     */
    private fun generateParenthesis(n:Int):List<String> {
        val res= ArrayList<String>()
        dfs(0,0,"",res,n)
        return res;
    }
    private fun dfs(left:Int,right:Int,str:String,res:ArrayList<String>,n:Int){
        if(left==n && right==left) {//一个匹配结果
            res.add(str)
            return
        }
        if(left+1<=n) dfs(left+1,right, "$str(",res,n)
        if(right+1<=left) dfs(left,right+1, "$str)",res,n)
    }

    /**
     * 判断一个链表是不是有环  有的话返回环的头结点
     */
    private fun detectCycle(node:ListNode?):ListNode?{
        var next = node
        val hashSet = HashSet<ListNode>()
        while(next != null){
            if(hashSet.contains(next)) return next
            hashSet.add(next)
            next = next.next
        }
        return null
    }

    /**
     * 检查t2 是不是t1的子树
     */
    private fun checkSubTree(t1:TreeNode?,t2:TreeNode?):Boolean{
        if(t1 == null) return false
        if(t2 == null) return false
        return check(t1,t2) || checkSubTree(t1.left,t2) || checkSubTree(t1.right,t2)
    }
    private fun check(t1: TreeNode?,t2: TreeNode?):Boolean{
        if(t2 == null) return true
        if(t1 == null || t2.value != t1.value) return false
        return check(t1.left,t2.left) && check(t1.right,t2.right)
    }
}