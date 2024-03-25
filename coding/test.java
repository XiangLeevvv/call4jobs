import java.text.DecimalFormat;
import java.util.Arrays;

class Test {
    // 希尔排序：在直接插入排序的基础上加入了gap，使得小数组能够有较好的有序性，这样最后一次排序不会花费大量时间
    // 将时间复杂度从直接插入排序的O(n^2)提升到O(nlogn)
    // 稳定性：不稳定
    // 时间复杂度：最佳：O(n),最差：O(n^2),平均：O(nlogn)
    public static void shellSort(int[] arr) {
        int gap = arr.length / 2;
        while(gap > 0) {
            for(int i = gap; i < arr.length; i++) {
                int cur = arr[i];
                int pre = i - gap;
                while(pre >= 0 && arr[pre] > cur) {
                    // 将大于当前值的元素向后移
                    arr[pre + gap] = arr[pre];
                    pre -= gap;
                }
                arr[pre + gap] = cur;
            }
            gap /= 2;
        }
    }

    public static void shell(int[] arr) {
        int gap = arr.length / 2;
        while(gap > 0) {
            for(int i = gap; i < arr.length; i++) {
                int cur = arr[i];
                int pre = i - gap;
                while(pre >= 0 && arr[pre] > cur) {
                    arr[pre + gap] = arr[pre];
                    pre -= gap;
                }
                arr[pre + gap] = cur;
            }
            gap /= 2;
        }
    }

    // 归并排序
    // 稳定性：稳定
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    public static int[] mergeSort(int[] arr) {
        if(arr.length <= 1) return arr;
        int middle = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }
    public static int[] merge(int[] arr1, int[] arr2) {
        int[] ans = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while(i < arr1.length && j < arr2.length) {
            if(arr1[i] <= arr2[j]) {
                ans[k++] = arr1[i++];
            } else {
                ans[k++] = arr2[j++];
            }
        }
        if(i < arr1.length) {
            while(i < arr1.length) {
                ans[k++] = arr1[i++];
            }
        } else {
            while(j < arr2.length) {
                ans[k++] = arr2[j++];
            }
        }
        // for(int x = 0; x < ans.length; x++) System.out.println(ans[x]);
        // System.out.println();
        return ans;
    }

    // 快速排序
    // 稳定性：不稳定
    // 时间复杂度：最好：O(nlogn),平均：O(nlogn),最差：O(n^2)
    public static void quickSort(int[] arr) {
        if(arr.length <= 1) return;
        sort(arr, 0, arr.length - 1);
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void sort(int[] arr, int low, int high) {
        if(low >= high) return;
        int middle = swapAndFind(arr, low, high);
        sort(arr, low, middle - 1);
        sort(arr, middle + 1, high);
    }
    public static int swapAndFind(int[] arr, int low, int high) {
        int base = arr[low];
        int i = low, j = high;
        while(i < j) {
            while(i < j && arr[j] >= base) j--;
            while(i < j && arr[i] <= base) i++;
            swap(arr, i, j);
        }
        swap(arr, low, i);
        return i;
    }
    
    // 堆排序
    // 稳定性：不稳定
    // 时间复杂度：O(nlogn)
    public static void main(String[] args) {
        int[] arr = {2,1,6,3,8,3,4,7,1,0};
        // shellSort(arr);
        quickSort(arr);
        // shell(arr);
        // int[] res = mergeSort(arr);
        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        // for(int i = 0; i < 10; i++)
        //     System.out.println("hello");
        double x = 2;
        double y = 200000;
        double ans = x / (y * (y - 1));
        DecimalFormat decimalFormat = new DecimalFormat("#.##########");
        // System.out.println(ans);
        // System.out.println(decimalFormat.format(ans));
        if(decimalFormat.format(ans).length() > 1) {
            System.out.println(decimalFormat.format(ans));
        } else {
            System.out.println("0.0000000000");
        }
    }
}
