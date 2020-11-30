package com.gt.lk.every_day

/**
 * time 2020/11/30 0030
 * author GT
 */
object Day1130 {
    @JvmStatic
    fun main(args: Array<String>) {
        val matrix = arrayOf(intArrayOf( 5, 1, 9,11),
            intArrayOf(2, 4, 8,10),
            intArrayOf(13, 3, 6, 7),
            intArrayOf(15,14,12,16))
//        rotate(matrix)
        rotateNoSpace(matrix)
        matrix.forEach {
            println("result:${it.contentToString()}")
        }
    }

    /**
     * 旋转矩阵
     */
    private fun rotate(matrix:Array<IntArray>):Array<IntArray>{
        val array = Array(matrix.size){IntArray(matrix[0].size)}
        for(i in matrix.indices){
            for(j in matrix[i].indices){
                array[j][matrix.size-i-1] = matrix[i][j]
            }
        }
        return array
    }

    /**
     * 不用额外的空间
     */
    private fun rotateNoSpace(matrix:Array<IntArray>){
        for(i in 0 until  matrix.size/2){
            for(j in matrix[i].indices){//上下翻转
                val temp = matrix[i][j]
                matrix[i][j] = matrix[matrix.size-i-1][j]
                matrix[matrix.size-i-1][j] = temp
            }
        }
        //对角线翻转
        for(i in matrix.indices){
            for(j in 0 until i){
                val temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j] [i] = temp
            }
        }
    }

}