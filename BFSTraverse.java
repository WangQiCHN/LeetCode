import java.util.Queue;
import java.util.LinkedList;

public class BFSTraverse {
    String SEP = ",";
    String NULL = "#";

    public static void main() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node2.right = node4;

        BFSTraverse m = new BFSTraverse();
        String data = m.serialize(node1);
        TreeNode node5 = m.deserialize(data);
        System.out.println(node5);
    }

    // /将二叉树序列化为字符串/
    String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        // 初始化队列，将root加入队列
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                // 层级遍历代码位置/
                if (cur == null) {
                    sb.append(NULL).append(SEP);
                    continue;
                }
                sb.append(cur.val).append(SEP);
                q.offer(cur.left);
                q.offer(cur.right);
            }
        }
        return sb.toString();
    }

    TreeNode deserialize(String data) {
        if(data.isEmpty())return null;
        String[] nodes = data.split(SEP);
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int index = 1;
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i = 0; i < sz; i++){
                TreeNode parent = q.poll();
                String left = nodes[index++];
                if(!left.equals("#")){
                    parent.left = new TreeNode(Integer.parseInt(left));
                    q.offer(parent.left);
                }
                String right = nodes[index++];
                if(!right.equals("#")){
                    parent.right = new TreeNode(Integer.parseInt(right));
                    q.offer(parent.right);
                }
            }
        }
        return root;
    }
}
