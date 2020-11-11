package com.gt.lk.every_day

import java.util.*

/**
 * time 2020/10/23 0023
 * author GT
 */
object Day1023 {

    @JvmStatic
    fun main(args:Array<String>){
        val m = 100
        val n = 100
        val k = 100
        println("start:${System.currentTimeMillis()}")
//        val count = movingCount(m,n,k)
        val count = bfs(m,n,k)
        println("start:${System.currentTimeMillis()}")
        println("count:$count")
    }

    /**
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
     * 也不能进入行坐标和列坐标的数位之和大于k的格子。
     *
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
     * 但它不能进入方格 [35, 38]，因为3+5+3+8=19
     *
     * m行n列 上面位数之和小于等于k
     */
    private fun movingCount(m:Int,n:Int,k:Int):Int{
        var count = 0
        if(k == 0) return 1
        for(i in 0 until m){
            val iSum = getSum(i)
            for(j in 0 until n){
                val jSum = getSum(j)
                val sum = iSum + jSum
                if(sum <= k){
                    count++
                }
            }
        }
        return count
    }

    /**
     * 比双重循环更加耗时
     */
    private fun bfs(m:Int,n:Int,k:Int):Int{
        if(k == 0) return 1
        var count = 1
        val vis = Array(m){
            Array(n){false}
        }
        val queue = LinkedList<IntArray>()
        queue.offer(intArrayOf(0,0))
        //向下和向右移动
        val dx = intArrayOf(0,1)
        val dy = intArrayOf(1,0)
        vis[0][0] = true//记录已经访问过的数据

        while(!queue.isEmpty()){
            val temp = queue.poll()
            val x = temp[0]
            val y = temp[1]
            for(i in 0 until 2){
                val tx = dx[i] + x
                val ty = dy[i] + y

                if(tx<0 || tx>=m || ty<0 || ty>=n ||
                    vis[tx][ty] || (getSum(tx)+ getSum(ty)>k)) continue

                queue.offer(intArrayOf(tx,ty))
                vis[tx][ty] = true
                count++
            }
        }
        return count
    }
    /**
     * 获取所有位数之和
     */
    private fun getSum(num:Int):Int{
        var sum = 0
        var temp = num
        while(temp != 0){
            sum += temp % 10
            temp /= 10
        }
        return sum
    }
}