package xin.liujiajun.groovy.io

/**
 * @author LiuJiaJun* @date 2019/10/27 8:34
 */
class TestIO {

    static void main(String[] args) {

        read()

        readToStr()

        write()

        readToStr()

        fileSize()

        copy()
    }
    static void read(){
        File file = new File("./data/groovy/data.txt")

        file.eachLine {
            line -> println("line $line")
        }
    }

    static void readToStr(){
        File file = new File("./data/groovy/data.txt")
        println(file.text)
    }

    static void write(){
        File file = new File("./data/groovy/data.txt")
        file.withWriter('utf-8'){
            write -> write.writeLine("hello world")
        }
    }

    static void fileSize(){
        File file = new File("./data/groovy/data.txt")
        println "The file ${file.getAbsolutePath()} has ${file.length()} bytes"
    }

    static void copy(){
        File source = new File("./data/groovy/data.txt")
        File target = new File("./data/groovy/dataCopy.txt")

        target << source.text
    }
}
