package com.gt.lk.tree.dfs

import java.util.*

/**
 * time 2020/6/23 0023
 * author GT
 * 回溯  DFS 深度优先搜索
 *   result = []
    def backtrack(路径, 选择列表):
        if 满足结束条件:
        result.add(路径)
        return

        for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
 */
object BacktrackTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(1,2,3)
        val linkedList = LinkedList<Int>()
        allArray(arr,linkedList)
        println(res.toString())
    }

    /**
     * 全排列 给定一个数组 输出所有可能的排列组合 n!
     */
    private val res = LinkedList<LinkedList<Int>>()//存放结果
    private fun allArray(arr:IntArray,linkedList: LinkedList<Int>){
        if(linkedList.size == arr.size){//满足条件 加入到结果里
            res.add(LinkedList(linkedList))
            return
        }
        for(i in arr.indices){
            //排除不合法的选择
            if(linkedList.contains(arr[i])){
                continue
            }
            linkedList.add(arr[i])//做选择
            allArray(arr,linkedList)//进入下一层决策树
            linkedList.removeLast()//取消选择
        }
    }

    /**
     * n皇后问题  n*n的棋盘  放n个皇后 不在同一行和列 左上左下 右上右下 也没有存在的
     * 可以的位置为Q 不可以的为 .
     */


}