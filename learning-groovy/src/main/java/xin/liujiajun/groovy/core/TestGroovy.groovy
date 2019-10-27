package xin.liujiajun.groovy.core

/**
 * @author LiuJiaJun
 * @date 2019/10/26 9:58
 */
class TestGroovy {

    static void main(String[] args) {
        println("hello world")
        def x = 0
        x = "李四"
        println(x)
        def file = new File("./data/nio/data.txt")
        println(file.text)
        file.eachLine {
            line -> println("line $line")
        }
        def arr = [1,'adfads',true]
        arr.
        println(arr)
    }
}
