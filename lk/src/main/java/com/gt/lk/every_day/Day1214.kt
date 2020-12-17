package com.gt.lk.every_day

import com.gt.lk.bean.TreeNode

/**
 * time 2020/12/14 0014
 * author GT
 */
object Day1214 {
    @JvmStatic
    fun main(args: Array<String>) {
        val root = TreeNode().apply {
            value = 5
            left = TreeNode().apply {
                value = 3
                left = TreeNode().apply {
                    value = 2
                    left = TreeNode().apply {
                        value = 1
                    }
                }
                right = TreeNode().apply {
                    value = 4
                }
            }
            right = TreeNode().apply {
                value = 6
            }
        }
//        val result = inorderSuccessor(root,TreeNode().apply { value = 6 })
//        println("result:${result?.value}")

        val result = findWhetherExistsPath(3,
            arrayOf(intArrayOf(0,1),intArrayOf(0,2),intArrayOf(1,2),intArrayOf(1,2)),
                0,
                2)
        println("result:$result")
    }

    /**
     * 从二叉搜索树中找到 指定节点的下一个节点 没有下一个就返回null
     * 也就是中序遍历的后续节点
     */
    private fun inorderSuccessor(root:TreeNode?,p:TreeNode):TreeNode?{
        if(root == null) return null
        return when{
            root.value > p.value ->{
                inorderSuccessor(root.left,p)
            }
            root.value < p.value ->{
                inorderSuccessor(root.right,p)
            }
            else -> {
                root.left
            }
        }
    }

    /**
     * 给定有向图，设计一个算法，找出两个节点之间是否存在一条路径
     * 输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
     * 输出：true
     */
    private fun findWhetherExistsPath(n:Int,array:Array<IntArray>,start:Int,target:Int):Boolean{
        // step1: 建立有向图 (directed graph)，过滤自环边(self edge)和平行边 (parallel edge)
        val graph = HashMap<Int, HashSet<Int>>()
        for (e in array) {
            if (e[0] == e[1]) continue //（自环边） self edge
            if(graph[e[0]] == null) {
                val hashSet = HashSet<Int>()
                hashSet.add(e[1])
                graph[e[0]] = hashSet
            }else {
                graph[e[0]]!!.add(e[1])
            }
        }
        return dfs(graph, start, target)
    }

    /**
     *  从目标开始遍历 寻找是否有终点
     **/
    private fun dfs(g:Map<Int, Set<Int>>, cur:Int, goal:Int):Boolean {
        if (cur == goal) return true // 找到终点
        for (nei in g.getOrDefault(cur, HashSet())){
            // nei == neighbor == next
            if (dfs(g, nei, goal)) return true
        }
        return false
    }
}