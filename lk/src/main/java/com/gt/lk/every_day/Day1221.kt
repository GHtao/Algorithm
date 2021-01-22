package com.gt.lk.every_day

/**
 * time 2020/12/21 0021
 * author GT
 */
object Day1221 {
    @JvmStatic
    fun main(args: Array<String>) {
//        val result = maxAliveYears(intArrayOf(1900,1901,1950), intArrayOf(1948,1951,2000))
//        println("result:$result")
//        val result = multiSearch("mississippi", arrayOf("is","ppi","hi","sis","i","ssippi"))
//        result.forEach {
//            println("result:${it.toArray().contentToString()}")
//        }

        val result = tictactoe(arrayOf("O X"," XO","X O"))
        println("result:$result")
    }

    /**
     * 给定 N 个人的出生年份和死亡年份，第 i 个人的出生年份为 birth[i]，死亡年份为 death[i]，
     * 实现一个方法以计算生存人数最多的年份。你可以假设所有人都出生于 1900 年至 2000 年（含 1900 和 2000 ）之间。
     * 如果一个人在某一年的任意时期处于生存状态，那么他应该被纳入那一年的统计中。
     * 例如，生于 1908 年、死于 1909 年的人应当被列入 1908 年和 1909 年的计数。
     * 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份
     *
     * 输入：
     * birth = {1900, 1901, 1950}
     * death = {1948, 1951, 2000}
     * 输出： 1901
     */
    private fun maxAliveYears(birth:IntArray,death:IntArray):Int{
        val lives = IntArray(102)
        for (birthyear in birth) lives[birthyear - 1900]++
        for (deathyear in death) lives[deathyear - 1900 + 1]--//死亡的这一年算是存活的 所以+1

        val preSum = IntArray(102)
        preSum[0] = lives[0]
        var maxlives = -1
        var minyear = 0
        for (i in  1 until  preSum.size) {
            preSum[i] = lives[i] + preSum[i - 1]
            if (preSum[i] > maxlives) {
                minyear = i + 1900
                maxlives = preSum[i]
            }
        }
        return minyear
    }

    /**
     * 给定一个较长字符串big和一个包含较短字符串的数组smalls，
     * 设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。
     * 输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置
     * 输入：big = "mississippi"   smalls = ["is","ppi","hi","sis","i","ssippi"]
     * 输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
     */
    private fun multiSearch(big:String,smalls:Array<String>):ArrayList<ArrayList<Int>>{
        val result = ArrayList<ArrayList<Int>>()
        val bigs = big.toCharArray()
        smalls.forEach {
            var i = 0
            var j = 0
            val list = ArrayList<Int>()
            val small = it.toCharArray()
            while (i<bigs.size){
                if(bigs[i] != small[j]){
                    i++
                }else{
                    i++
                    j++
                    if(j == small.size){
                        j=0
                        list.add(i-small.size)
                    }
                }
            }
            result.add(list)
        }
        return result
    }

    /**
     * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
     * 以下是井字游戏的规则：
     * 玩家轮流将字符放入空位（" "）中
     * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
     * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
     * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
     * 当所有位置非空时，也算为游戏结束。
     * 如果游戏结束，玩家不允许再放置字符。
     * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；
     * 如果仍会有行动（游戏未结束），则返回 "Pending"。
     */
    private fun tictactoe(board:Array<String>):String {

        val length = board.size
        var heng = 0 //横的和
        var zong = 0 //纵的和
        var left = 0 //左斜线
        var right = 0 //右斜线
        var flag = false //记录有没有空格

        for (i in  0 until  length) {
            heng = 0
            zong = 0
            for (j in 0 until length) {
                heng += board[i].toCharArray()[j].toInt()
                zong += board[j].toCharArray()[i].toInt()
                if(board[i].toCharArray()[j] == ' ') flag = true
            }
            //横纵检查
            if (heng == 'X'.toInt() * length || zong == 'X'.toInt() * length) return "X"
            if (heng == 'O'.toInt() * length || zong == 'O'.toInt() * length) return "O"
            //两条斜线上的相加
            left += board[i].toCharArray()[i].toInt()
            right += board[i].toCharArray()[length - i - 1].toInt()
        }
        //两条斜线检查
        if (left == 'X'.toInt() * length || right == 'X'.toInt() * length) return "X"
        if (left == 'O'.toInt() * length || right == 'O'.toInt() * length) return "O"

        if (flag) return "Pending"
        return "Draw"

    }

}