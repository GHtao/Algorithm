package com.gt.lk.every_day

import com.gt.lk.bean.TreeNode

/**
 * time 2020/12/11 0011
 * author GT
 */
object Day1211 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = findClosest(arrayOf("I","am","a","student","from","a","university","in","a","city"),
//            "university","a")
//        println("result:$result")
//        val result = permutation("aabcc ")
//        println("result:${result.toArray().contentToString()}")

        val root = TreeNode().apply {
            value = 5
            left = TreeNode().apply {
                value = 4
                left = TreeNode().apply {
                    value = 11
                    left = TreeNode().apply {
                        value = 7
                    }
                    right = TreeNode().apply {
                        value = 2
                    }
                }
            }
            right = TreeNode().apply {
                value = 8
                left = TreeNode().apply {
                    value = 13
                }
                right = TreeNode().apply {
                    value = 4
                    left = TreeNode().apply {
                        value = 5
                    }
                    right = TreeNode().apply {
                        value = 1
                    }
                }
            }
        }
        val result = pathNum(root,22)
        println("result:$result")
    }

    /**
     * 给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)
     * 双指针法
     */
    private fun findClosest(array:Array<String>,w1:String,w2:String):Int{
        var index1 = 0
        var index2 = 0
        var min = Int.MAX_VALUE
        while (index1 < array.size){
            //先找到一个位置
            while(index1 < array.size && w1 != array[index1] && w2 != array[index1]) index1++
            index2 = index1
            index2++
            while (index2 < array.size){
                if(array[index1] == array[index2]) index1 = index2//与第一个内容一样 更新第一个指针
                if((array[index2] == w1 || array[index2]==w2)
                    && array[index2] != array[index1]){//找到另一个单词的位置
                    min = Math.min(min,index2-index1)
                    break
                }else{
                    index2++
                }
            }
            index1 = index2
        }
        return min
    }

    /**
     * 计算字符串的所有排列组合
     * 输入：S = "qqe"
     * 输出：["eqq","qeq","qqe"]
     */
    private fun permutation(s:String):HashSet<String>{
        val result = HashSet<String>()
        val array = s.toCharArray()
        val hashMap = HashMap<Int,Char>()
        dfs(array,result,"",hashMap)
        return result
    }
    private fun dfs(array:CharArray,list:HashSet<String>,str:String,map:HashMap<Int,Char>){
        if(array.size == str.length){
            list.add(str)
            return
        }
        for(i in array.indices){
            val c = array[i]
            if(map[i] == c) continue//记录之前添加过得位置
            map[i] = c
            dfs(array,list,str+c,map)
            map.remove(i)
        }
    }

    /**
     * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。
     * 设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
     * 注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，
     * 但是其方向必须向下(只能从父节点指向子节点方向
     */
    private var res = 0
    private fun pathNum(root:TreeNode?,num:Int):Int{
        if(root == null) return res
        tree(root,0,num)
        pathNum(root.left,num)
        pathNum(root.right,num)
        return res
    }
    private fun tree(root: TreeNode?,sum:Int,num:Int){
        if(root == null) return
        var temp = sum
        temp += root.value
        if(temp == num) res++
        tree(root.left,temp,num)
        tree(root.right,temp,num)
    }
}