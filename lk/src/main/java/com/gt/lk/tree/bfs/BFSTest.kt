package com.gt.lk.tree.bfs

import com.gt.lk.bean.TreeNode
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import kotlin.collections.HashSet

/**
 * time 2020/6/23 0023
 * author GT
 * 广度优先搜索  最短路径问题
 *
 * // 计算从起点 start 到终点 target 的最近距离
    int BFS(Node start, Node target) {
        Queue<Node> q; // 核心数据结构
        Set<Node> visited; // 避免走回头路

        q.offer(start); // 将起点加入队列
        visited.add(start);
        int step = 0; // 记录扩散的步数

        while (q not empty) {
            int sz = q.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                /* 划重点：这里判断是否到达终点 */
                if (cur is target)
                    return step;
                /* 将 cur 的相邻节点加入队列 */
            for (Node x : cur.adj())
                if (x not in visited) {
                    q.offer(x);
                    visited.add(x);
                }
            }
            /* 划重点：更新步数在这里 */
            step++;
        }
    }
 */
object BFSTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(3,9,20,Int.MIN_VALUE,Int.MIN_VALUE,15,7)
        val root = arr2Tree(arr,0)
//        val deep = treeDeep(root)
        val deep = secretLock("9999",Array(2){""})
        println("deep:$deep")
    }
    /**
     *  数的深度
     */
    private fun treeDeep(root: TreeNode?):Int{
        if(root == null) return 0
        var deep = 1
        val queue = LinkedBlockingQueue<TreeNode>()//关键的队列结构
        queue.offer(root)
        while(queue.isNotEmpty()){
            val n = queue.size
            for(i in 0 until n){//向当前节点的周围扩散
                val curr = queue.poll()
                if(curr.left == null && curr.right == null){//判断是否到达终点
                    return deep
                }
                if(curr.left != null){
                    queue.offer(curr.left!!)
                }
                if(curr.right != null){
                    queue.offer(curr.right!!)
                }
            }
            deep++
        }
        return deep
    }

    /**
     * 将一个数组转化成树
     */
    private fun arr2Tree(arr:IntArray,index:Int): TreeNode?{
        if(index>arr.size-1) return null
        if(arr[index] == Int.MIN_VALUE){//最小值没有子节点
            return null
        }
        val node = TreeNode().apply { value = arr[index] }
        node.left = arr2Tree(arr,2*index+1)
        node.right = arr2Tree(arr,2*index+2)
        return node
    }

    /**
     * 密码锁 最短的找到密码路径 不能出现dead中的密码  否则无法解开
     * 密码是数字0-9
     */
    private fun secretLock(target:String,dead:Array<String>):Int{
        val queue = LinkedList<String>()
        val visit = HashSet<String>()
        queue.offer("0000")
        visit.add("0000")
        var deep = 0
        while(queue.isNotEmpty()){
            val n = queue.size
            for(i in 0 until n){
                val curr = queue.poll()
                if(dead.contains(curr)) continue
                if(curr == target) return deep
                for(j in 0 until 4){
                    val currP = plus(curr,j)
                    if(!visit.contains(currP)){
                        queue.offer(currP)
                        visit.add(currP)
                    }

                    val currM = minus(curr,j)
                    if(!visit.contains(currM)){
                        queue.offer(currM)
                        visit.add(currM)
                    }
                }
            }
            deep++
        }
        //没有找打匹配的就返回-1
        return -1
    }

    /**
     * 对应位上数字加一
     */
    private fun plus(str:String,index:Int):String{
        return String(str.toCharArray().apply {
            val get = get(index)
            if(get == '9'){
                set(index,'0')
            }else{
                set(index,get+1)
            }
        })
    }

    /**
     * 对应位上数字减一
     */
    private fun minus(str:String,index:Int):String{
        return String(str.toCharArray().apply {
            val get = get(index)
            if(get == '0'){
                set(index,'9')
            }else{
                set(index,get-1)
            }
        })
    }
}