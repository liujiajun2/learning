package xin.liujiajun.servlet.test;

import java.util.Scanner;

/**
 * @author LiuJiaJun
 * @date 2018/10/18 14:42
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();

        if(a < 1 || a > 1000000000){
            System.out.println("a输入错误");
            throw new Exception("a输入错误");
        }
        if(b < a || b > 1000000000 ){
            System.out.println("b输入错误");
            throw new Exception("b输入错误");
        }
        System.out.println(count(a,b));
    }

    /**
     * 返回幸运数字个数
     * @param a a
     * @param b b
     * @return 幸运数字个数
     */
    public static int count(long a,long b){
        int count = 0;
        for (long i = a; i <= b ; i++) {
            String s = String.valueOf(i);
            char[] chars = s.toCharArray();
            boolean flag = true;
            for (int j = 0; j < chars.length; j++) {
                if(chars[j] != '6' && chars[j] != '8'){
                    flag = false;
                    break;
                }
            }
            if(flag){
                count ++;
            }
        }
        return count;
    }
}
