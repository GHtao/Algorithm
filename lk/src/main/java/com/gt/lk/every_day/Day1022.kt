package com.gt.lk.every_day

/**
 * time 2020/10/22 0022
 * author GT
 */
object Day1022 {

    @JvmStatic
    fun main(args:Array<String>){
        val board = arrayOf(arrayOf('A','B','C','E'),
            arrayOf('S','F','C','S'), arrayOf('A','D','E','F'))
        val exist = exist(board,"ABCCED")
        println("exist:$exist")
    }

    private fun exist(board:Array<Array<Char>>,word:String):Boolean{
        for(i in board.indices){
            for(j in board[i].indices){
                //每一个元素都认为是起始搜索点
                if(dfs(board,word,i,j,0)) return true
            }
        }
        return false
    }

    /**
     * 判断字符串在二维数组中是否存在路径
     * i 数组的缩影
     * j i数组内的索引
     * k work的第几个字符
     */
    private fun dfs(board: Array<Array<Char>>, word: String, i:Int, j:Int, k:Int):Boolean{
        if(i>board.size-1 || i<0
            ||j>board[i].size-1 || j<0
            ||board[i][j] != word.toCharArray()[k]) return false//判断越界和不相等
        if(k == word.toCharArray().size-1) return true//判断是否满足搜索条件
        val temp = board[i][j]
        board[i][j] = '/'//防止一次搜索的过程中重复访问  做选择
        val res = dfs(board,word,i,j+1,k+1) ||
                            dfs(board,word,i,j-1,k+1) ||
                            dfs(board,word,i-1,j,k+1) ||
                            dfs(board,word,i+1,j,k+1)
        board[i][j] = temp//撤销选择

        return res
    }
}