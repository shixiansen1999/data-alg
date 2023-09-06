package com.sbx.alg;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author : sbx
 * @Classname : NTypeConvert
 * @Description : N字型转换：
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * @Date : 2023/9/6 9:46
 */
@Slf4j
public class NTypeConvert {

    public static void main(String[] args) {
        char[][] arr = new char[2][2];
        arr[1][1] = 'a';
        for (char[] row : arr) {
            for (char c : row) {
                System.out.println(c);
            }
        }
        System.out.println(arr[0][0] == 0);
    }

    @Test
    public void testConvert() {
        System.out.println("请输入字符串：");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println("请输入行数：");
        int i = sc.nextInt();
        String s = convert(line, i);
        System.out.println(s);
    }

    @Test
    public void testConvert1() {
        System.out.println("请输入字符串：");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println("请输入行数：");
        int i = sc.nextInt();
        String s = convert1(line, i);
        System.out.println(s);
    }

    @Test
    public void testConvert11() {
        System.out.println("请输入字符串：");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println("请输入行数：");
        int i = sc.nextInt();
        String s = convert11(line, i);
        System.out.println(s);
    }


    /**
     * 很秒的解法：巧设flag
     * PAYPALISHIRING
     * 01232101232101
     * 数字为字符串在排列好所在位置的行数，从0开始，然后只需要按行拼接字符就可以得到最终的结果
     *
     * 将字符串s按照指定行数numRows进行N字型转换
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        //行数为1时返回原字符串
        if (numRows < 2) {
            return s;
        }
        //将每行的字符存放在一个单独的StringBuilder中
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            list.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            list.get(i).append(c);
            //i=0或i=numRows-1时需要变向flag，i=0时增，i=numRows-1时减
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder stringBuilder : list) {
            sb.append(stringBuilder);
        }

        return sb.toString();
    }

    /**
     * 使用二维数组模拟
     * @param s
     * @param numRows
     * @return
     */
    public String convert1(String s, int numRows) {
        int len = s.length();
        //只有一行或者字符串长度小于等于行数时直接返回
        if (numRows < 2 || len <= numRows) {
            return s;
        }
        //每个周期的字符个数
        int t = 2*numRows - 2;
        //周期数
        int n = ((len + t) - 1) / t;
        //每个周期的列数等于行数-1
        //计算二维数组的列数
        int numCols = n * (numRows - 1);
        char[][] arr = new char[numRows][numCols];
        //坐标，向下添加时x++,然后向右添加时y++
        for (int i = 0, x = 0, y = 0; i < len; i++) {
            arr[x][y] = s.charAt(i);
            if ((i % t) < (numRows - 1)) {
                x++;
            } else {
                x--;
                y++;
            }
        }
        //每行从左往右遍历添加非空字符
        StringBuilder sb = new StringBuilder();
        for (char[] row : arr) {
            for (char c : row) {
                if (c != 0) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }

    /**
     * 二维数组模拟优化
     * @param s
     * @param numRows
     * @return
     */
    public String convert11(String s, int numRows) {
        int len = s.length();
        if (numRows < 2 || len <= numRows) {
            return s;
        }
        //为每一行创建一个StringBuilder
        StringBuilder[] mat = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            mat[i] = new StringBuilder();
        }
        //在每一行StringBuilder的末尾添加
        for (int i = 0, t = 2*numRows - 2,x = 0; i < len; ++i) {
            mat[x].append(s.charAt(i));
            if (i % t < numRows -1) {
                ++x;
            } else {
                --x;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder stringBuilder : mat) {
            sb.append(stringBuilder);
        }

        return sb.toString();
    }
}
