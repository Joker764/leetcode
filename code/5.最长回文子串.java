/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 * 
 * manacher 算法解决方案
 */
class Solution {
    public String longestPalindrome(String s) {
        String T = preProcess(s);
        int len = T.length();
        int[] P = new int[len]; 
        int C = 0;
        int R = 0;

        for (int i = 1; i < len - 1; i++) {
            int i_mirror = 2 * C - i;
            if (R > i) {
                // 防止超出 R
                P[i] = Math.min(R - i, P[i_mirror]);
            } else {
                // 等于 R 的情况
                P[i] = 0;
            }

            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }

        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < len - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }

        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    public String preProcess(String s) {
        int len = s.length();
        if (len == 0) {
            return "^$";
        }

        String ret = "^";
        for (int i = 0; i < len; i++) {
            ret += "#" + s.charAt(i);
        }
        ret += "#$";

        return ret;
    }
}
