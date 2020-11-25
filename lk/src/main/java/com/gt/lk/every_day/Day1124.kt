package com.gt.lk.every_day

import com.gt.lk.bean.TreeNode
import com.sun.org.apache.xpath.internal.operations.Bool
import java.lang.StringBuilder

/**
 * time 2020/11/24 0024
 * author GT
 */
object Day1124 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = firstUniqueChar("abbaccdeff")
//        println("result:$result")

//        val result = lastRemaining(4,3)
//        println("result:$result")

        val root = TreeNode().apply {
            value = 3
            left = TreeNode().apply {
                value = 9

            }
            right = TreeNode().apply {
                value = 20
                left = TreeNode().apply {
                    value = 15
                }
                right = TreeNode().apply {
                    value = 7
                    left = TreeNode().apply {
                        value = 8
                        left = TreeNode().apply {
                            value = 10
                        }
                    }
                }
            }
        }
//        val result = isBalanced(root)
//        println("result:$result")

//        val result = reversWords("a good   example")
//        println("result:$result")

        val result = isStraight(intArrayOf(0,0,1,2,6))
        println("result:$result")
    }

    /**
     * 在字符串 s 中找出第一个只出现一次的字符。
     * 如果没有，返回一个单空格。 s 只包含小写字母
     */
    private fun firstUniqueChar(s:String):Char{
        val hashMap = HashMap<Char,Boolean>()
        s.toCharArray().forEach {
            hashMap[it] = !hashMap.containsKey(it)
        }
        hashMap.entries.forEach {
            if(it.value) return it.key
        }
        return ' '
    }

    /**
     * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。
     * 求出这个圆圈里剩下的最后一个数字
     *
     * 0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，
     * 则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3
     */
    private fun lastRemaining(n:Int,m:Int):Int{
        var f = 0
        for (i in 2 .. n) {
            f = (m + f) % i
        }
        return f
    }

    /**
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
     * 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树
     */
    private fun isBalanced(treeNode: TreeNode?):Boolean{
        if(treeNode == null) return false
        val left = deep(treeNode.left,1)
        val right = deep(treeNode.right,1)
        return Math.abs(left - right)<=1
    }
    private fun deep(treeNode: TreeNode?,deep:Int):Int{
        if(treeNode == null) return deep
        var left = deep
        var right = deep
        if(treeNode.left != null){
            left = deep(treeNode.left,deep+1)
        }
        if(treeNode.right != null){
            right = deep(treeNode.right,deep+1)
        }
        return Math.max(left,right)
    }

    /**
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
     * 为简单起见，标点符号和普通字母一样处理。
     * 例如输入字符串"I am a student. "，则输出"student. a am I"
     * 字母中间最多剩余一个空格
     *
     */
    private fun reversWords(word:String):String{
        val s = word.trim(); // 删除首尾空格
        var j = s.length - 1
        var i = j
        val res = StringBuilder()
        val array = s.toCharArray()
        while(i >= 0) {
            while(i >= 0 && array[i] != ' ') i-- // 搜索首个空格
            res.append(s.substring(i + 1, j + 1) + " ") // 添加单词
            while(i >= 0 && array[i] == ' ') i-- // 跳过单词间空格
            j = i // j 指向下个单词的尾字符
        }
        return res.toString().trim() // 转化为字符串并返回
    }

    /**
     * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2～10为数字本身，A为1，J为11，Q为12，K为13，
     * 而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     */
    private fun isStraight(array:IntArray):Boolean{
        var min = 14
        var max = 0
        val set = HashSet<Int>()
        for(i in array.indices){
            if(array[i] == 0) continue
            //有重复的就不是顺子
            if(set.contains(array[i])) return false
            min = Math.min(min,array[i])
            max = Math.max(max,array[i])
            set.add(array[i])
        }
        //最大值和最小值 不大于5 就能成顺子
        return (max-min) <5
    }
}