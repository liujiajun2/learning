package xin.liujiajun.groovy.closure

/**
 * @author LiuJiaJun* @date 2019/10/27 8:43
 */
class TestClosure {

    static void main(String[] args) {
        def clos = {params -> println "hello ${params}"}

        clos.call("world ")

        map()
    }

    static void map(){
        def map = [
            'hello':'world'
        ]
        map.each {println it}

        map.each {println "${it.key} maps to : ${it.value}"}
    }
}
