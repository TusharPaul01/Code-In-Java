//Java //Q-886 LC
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] color = new int[n + 1];
        Arrays.fill(color, -1);

        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] dislike : dislikes) {
            adj[dislike[0]].add(dislike[1]);
            adj[dislike[1]].add(dislike[0]);
        }

        for (int i = 1; i <= n; i++) {
            if (color[i] == -1 && !dfs(i, adj, color, 0)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int start, List<Integer>[] adj, int[] color, int currentColor) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = currentColor;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : adj[node]) {
                if (color[neighbor] == -1) {
                    color[neighbor] = 1 - color[node];
                    queue.add(neighbor);
                } else if (color[neighbor] == color[node]) {
                    return false;
                }
            }
        }

        return true;
    }
}
