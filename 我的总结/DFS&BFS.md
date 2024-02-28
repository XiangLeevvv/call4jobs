```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
```



// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left, right;

```java
public TreeNode(int val) {
    this.val = val;
    this.left = this.right = null;
}
```
}

public class BinaryTreeTraversal {

```java
// 深度优先遍历（DFS）- 递归方式
public static void dfsRecursive(TreeNode root) {
    if (root == null) {
        return;
    }

    System.out.print(root.val + " ");
    dfsRecursive(root.left);
    dfsRecursive(root.right);
}

// 深度优先遍历（DFS）- 非递归方式（使用栈）
public static void dfsNonRecursive(TreeNode root) {
    if (root == null) {
        return;
    }

    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
        TreeNode current = stack.pop();
        System.out.print(current.val + " ");

        // 先压入右子节点，再压入左子节点，确保左子节点先出栈
        if (current.right != null) {
            stack.push(current.right);
        }
        if (current.left != null) {
            stack.push(current.left);
        }
    }
}

// 广度优先遍历（BFS）
public static void bfs(TreeNode root) {
    if (root == null) {
        return;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        TreeNode current = queue.poll();
        System.out.print(current.val + " ");

        if (current.left != null) {
            queue.offer(current.left);
        }
        if (current.right != null) {
            queue.offer(current.right);
        }
    }
}

public static void main(String[] args) {
    // 构建二叉树示例
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(7);

    System.out.println("DFS Recursive:");
    dfsRecursive(root);
    System.out.println();

    System.out.println("DFS Non-Recursive:");
    dfsNonRecursive(root);
    System.out.println();

    System.out.println("BFS:");
    bfs(root);
}
```
}