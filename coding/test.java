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
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static int sort(int[] nums, int left, int right) {
        int base = nums[left];
        int i = left, j = right;
        while(i < j) {
            while(i < j && nums[j] >= base) j--;
            while(i < j && nums[i] <= base) i++;
            swap(nums, i, j);
        }
        swap(nums, left, i);
        return i;
    }
    public static void quick_sort(int[] nums, int left, int right) {
        if(left >= right) return;
        int mid = sort(nums, left, right);
        quick_sort(nums, left, mid - 1);
        quick_sort(nums, mid + 1, right);;
    }
    public static void quickSort(int[] nums) {
        if(nums.length <= 1) return;
        quick_sort(nums, 0, nums.length - 1);
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
