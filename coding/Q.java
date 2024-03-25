public class Q {
    import java.util.Scanner;
    
    import java.util.*;
    // 注意类名必须为 Main, 不要有任何 package xxx 信息
    public class Main {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            // 注意 hasNext 和 hasNextLine 的区别
            String str = in.next();
            // System.out.println(str);
            // int[] countA = new int[str.length()];
            // int[] countB = new int[str.length()];
            // if(str.charAt(0) == 'A') countA[0] = 1;
            // else countB[0] = 1;
            // for(int i = 1; i < str.length(); i++) {
            //     if(str.charAt(i) == 'A') {
            //         countA[i] = countA[i-1] + 1;
            //         countB[i] = countB[i-1];
            //     } else {
            //         countA[i] = countA[i-1];
            //         countB[i] = countB[i-1] + 1;
            //     }
            // }
            // for(int i = 0; i < str.length(); i++) {
            //     System.out.println(countA[i] + " " + countB[i]);
            // }
            // int ans = 0;
            // int isA = 1, isB = 1;
            // for(int i = 0; i < str.length(); i++) {
            //     int j = str.length() - 1;
            //     if(str.charAt(i) == 'A') {
            //         isA = 1;
            //         isB = 0;
            //     } else {
            //         isA = 0;
            //         isB = 1;
            //     }
            //     while(j > i) {
            //         int numA = countA[j] - countA[i] + isA;
            //         int numB = countB[j] - countB[i] + isB;
            //         if(numA == numB) {
            //             int size = j - i + 1;
            //             // System.out.println(j + " " + i);
            //             if(ans < size) ans = size;
            //             System.out.println(ans);
            //             return;
            //             // break;
            //         } else {
            //             j--;
            //         }
            //     }
            // }
            int len = str.length();
            int val = 0;
            int ans = 0;
            Map<Integer, Integer> pos = new HashMap<>();
            pos.put(0, -1);
            for(int i = 0; i < len; i++) {
                if(str.charAt(i) == 'A') val++;
                else val--;
                if(!pos.containsKey(val)) {
                    pos.put(val, i);
                } else if(ans < i - pos.get(val)) {
                    ans = i - pos.get(val);
                }
                // if(pos.containsKey(val - 1)) {
                //     if(ans < i - pos.get(val - 1) + 1) {
                //         ans = i + 1 - pos.get(val - 1);
                //     }
                // }
                // if(pos.containsKey(val + 1)) {
                //     if(ans < i + 1 - pos.get(val + 1)) {
                //         ans = i + 1 - pos.get(val + 1);
                //     }
                // }
            }
    
            System.out.println(ans);
        }
    }