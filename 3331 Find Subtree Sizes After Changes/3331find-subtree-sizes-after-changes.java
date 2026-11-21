import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // children adjacency via arrays
        int[] head = new int[n], next = new int[n];
        Arrays.fill(head, -1);
        for (int i = 1; i < n; i++) {
            next[i] = head[parent[i]];
            head[parent[i]] = i;
        }

        int[] newParent = new int[n];
        newParent[0] = -1;
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] order = new int[n];     // preorder
        int cnt = 0;

        // iterative DFS
        int[] stack = new int[n];
        int[] edge = new int[n];      // current child pointer per node
        int[] saved = new int[n];     // previous last[c] to restore on exit
        int sp = 0;

        stack[sp++] = 0;
        edge[0] = head[0];
        saved[0] = last[s.charAt(0) - 'a'];
        last[s.charAt(0) - 'a'] = 0;
        order[cnt++] = 0;

        while (sp > 0) {
            int u = stack[sp - 1];
            int v = edge[u];
            if (v == -1) {
                last[s.charAt(u) - 'a'] = saved[u];   // restore on exit
                sp--;
            } else {
                edge[u] = next[v];
                int c = s.charAt(v) - 'a';
                newParent[v] = last[c] != -1 ? last[c] : parent[v];
                saved[v] = last[c];
                last[c] = v;
                edge[v] = head[v];
                stack[sp++] = v;
                order[cnt++] = v;
            }
        }

        int[] size = new int[n];
        Arrays.fill(size, 1);
        for (int i = n - 1; i > 0; i--) {
            int x = order[i];
            size[newParent[x]] += size[x];
        }
        return size;
    }
}