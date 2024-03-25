// import java.util.LinkedList;
// import java.util.Queue;
import java.util.Stack;

// import javax.swing.tree.TreeNode;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class bfs {
    
    public static int BFS(TreeNode root) {
        if(root == null) return 0;
        Stack<TreeNode> current = new Stack<>();
        int ans = 0;
        current.push(root);
        while(true) {
            Stack<TreeNode> next = new Stack<>();
            int cur = current.size();
            ans += cur;
            while(current.size() > 0) {
                TreeNode top = current.pop();
                if(top.left != null) next.push(top.left);
                if(top.right != null) next.push(top.right);
            }
            if(next.size() == 2 * cur) {
                current = next;
            } else {
                break;
            }
        }
        return ans;
    }
    public static int countNodes(TreeNode root) {
        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;
        System.out.println(countNodes(root) - BFS(root));
    }
}
