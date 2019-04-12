package xin.liujiajun.servlet.servlet;

import java.util.Scanner;

/**
 * @author LiuJiaJun
 * @date 2018/10/18 14:57
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        System.out.println(isReverse(a));
    }

    /**
     * 判断一个整数是不是回文数
     *
     * @param a 整数a
     * @return 是不是回文数
     */
    public static boolean isReverse(int a){
        if(a < 0) {
            return false;
        }
        char[] start = String.valueOf(a).toCharArray();
        char[] end = start;

        boolean flag = true;
        for (int i = 0,j = start.length - 1; i < start.length; j-- ,i++) {
            if(start[i] != end[j]){
                flag = false;
                break;
            }
        }
        return flag;
    }
}
