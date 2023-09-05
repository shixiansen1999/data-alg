package com.sbx.alg;

import com.sun.corba.se.spi.ior.MakeImmutable;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author : sbx
 * @Classname : Alg
 * @Description : 最长回文子串
 * @Date : 2023/9/4 16:38
 */
public class LongestPalindromicString {
    public static void main(String[] args) {
        String s = "abcdefg";
        int end = 0 - 1/2;
        System.out.println(end);
    }
    @Test
    public void testLongestPalindromicString() {
        System.out.println("请输入目标字符串:");
        Scanner scanner = new Scanner(System.in);
        String sourceStr = scanner.nextLine();
        String targetStr = getLongestPalindromicString(sourceStr);
        System.out.println("最长回文子串：" + targetStr);
    }

    /**
     * 查找最长回文串
     * 以回文中心，从两种边界条件开始判断是否为回文串
     * 两种边界：
     * P(i,i) = true
     * P(i,i+1) = (Si == Si+1)
     * @param sourceStr
     * @return
     */
    public String getLongestPalindromicString(String sourceStr) {
        if (null == sourceStr || sourceStr.length() < 0) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < sourceStr.length(); i++) {
            //获取第一种边界条件的最长回文串的长度
            int len1 = getPalindromicCenterLongest(sourceStr, i, i);
            //获取第二种边界条件的最长回文串的长度
            int len2 = getPalindromicCenterLongest(sourceStr, i, i + 1);
            int max = Math.max(len1, len2);
            if (max > end - start) {
                start = i - (max-1)/2;
                end = i + max/2;
            }
        }
        //subString左边是闭区间，右边是开区间：包含左边不包含右边
        return sourceStr.substring(start, end+1);
    }

    /**
     * 获取以该位置为回文中心时的最长回文串的长度，
     * 状态方程可分为三种情况：字符串长度等于1，长度等于2，长度大于2
     * P(i,i) = true
     * P(i,i+1) = (Si == Si+1)
     * P(i,j) = P(i+1)P(j-1) && (Si == Sj)
     *
     * @param sourceStr
     * @param left
     * @param right
     * @return
     */
    public int getPalindromicCenterLongest(String sourceStr, int left, int right) {
        while (left >= 0 && right < sourceStr.length() && sourceStr.charAt(left) == sourceStr.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }
}
