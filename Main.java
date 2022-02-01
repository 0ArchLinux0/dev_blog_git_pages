import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static int[] fdp;
	static int[] edp;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = toi(br.readLine());
		int[] arr;
		Node[] nodes = new Node[n];
		edp = new int[n];
		fdp = getArr();
		
		visit = new boolean[n];
		for(int i = 0; i < n; i++) nodes[i] = new Node(i); 
		for(int i = 0; i < n - 1; i++) {
			arr = getArr();
			int a = arr[0] - 1, b = arr[1] - 1;
			nodes[a].list.add(nodes[b]);
			nodes[b].list.add(nodes[a]);
		}
		nodes[0].parent = -1;
		bfs(nodes[0]);
		int ans = 0;
		println(Math.max(edp[0], fdp[0]));
	}
	
	static void bfs(Node node) {
		if(node.list.size() == 1 && node.parent != -1) {
			return;
		}
		for(Node child: node.list) {
			if(child.idx == node.parent) continue;
			child.parent = node.idx;
			if(!visit[child.idx]) bfs(child);
			fdp[node.idx] += edp[child.idx];
			edp[node.idx] += Math.max(edp[child.idx], fdp[child.idx]);
		}
		visit[node.idx] = true;
	}

	static class Node {
		int idx;
		int parent;
		boolean evenIdx = false;
		ArrayList<Node> list = new ArrayList<>();
		Node(int idx) { this.idx = idx; }
	}


	static int toi(String s) { return Integer.parseInt(s); }
	static String[] getLine() throws IOException { return br.readLine().split(" "); }	
	static int[] getArr() throws IOException { return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); }
	static <T> void print(T s) { System.out.print(s); }
	static <T> void println(T s) { System.out.println(s); }
}