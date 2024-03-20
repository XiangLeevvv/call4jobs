public class quicksort {
    // 快速排序入口
    public void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }
    
    // 快速排序递归函数
    private void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(nums, low, high); // 划分区间
        sort(nums, low, pivotIndex - 1); // 递归处理左半部分
        sort(nums, pivotIndex + 1, high); // 递归处理右半部分
    }
    
    // 划分函数，选取基准值并将小于基准值的元素放到左边，大于基准值的元素放到右边
    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low]; // 选取第一个元素作为基准值
        int left = low, right = high;
        while (left < right) {
            while (left < right && nums[right] >= pivot) { // 从右向左找第一个小于基准值的元素
                right--;
            }
            nums[left] = nums[right]; // 将小于基准值的元素移到左边
            while (left < right && nums[left] <= pivot) { // 从左向右找第一个大于基准值的元素
                left++;
            }
            nums[right] = nums[left]; // 将大于基准值的元素移到右边
        }
        nums[left] = pivot; // 将基准值放到正确的位置
        return left; // 返回基准值的位置
    }
    
    // 测试
    public static void main(String[] args) {
        int[] nums = {3, 2, 5, 1, 4};
        quicksort quickSort = new quicksort();
        quickSort.quickSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
