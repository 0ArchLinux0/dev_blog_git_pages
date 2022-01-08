import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static int max = 0, maxIdx = 0;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] line;
		
		int n = toi(br.readLine());
		int[] in = new int[n];
		int[] post = new int[n];
		Node root;
		Node[] nodes = new Node[n];
		for(int i = 0; i < n; i++) nodes[i] = new Node(i);

		line = getLine();
		for(int i = 0; i < n; i++) in[i] = toi(line[i]) - 1;
		line = getLine();
		for(int i = 0; i < n; i++) post[i] = toi(line[i]) - 1;
		root = nodes[post[n-1]];
		spec(in, post, nodes, 0, n - 1, n - 1);
		pre(root, sb);
		print(sb);
	}

	static Node spec(int[] in, int[] post, Node[] nodes, int left, int right, int midIdx) {
		if(left > right) return null;
		if(left == right) return nodes[in[left]];
		int midV = post[midIdx];
		int midIdxIn = 0;
		for(int i = left; i <= right; i++) 
			if(in[i] == midV) {
				nodes[midV].right = spec(in, post, nodes, i + 1, right, midIdx - 1);	
				nodes[midV].left = spec(in, post, nodes, left, i - 1, midIdx + i - right - 1);
			}
		return nodes[midV];
	}

	static class Node {
		int idx;
		Node left;
		Node right;

		public Node(int idx) { this.idx = idx; }
	}

	static void pre(Node node, StringBuilder sb) {
		sb.append(node.idx + 1).append(" ");
		if(node.left != null) pre(node.left, sb);
		if(node.right != null) pre(node.right, sb);
	}

	static int toi(String s) { return Integer.parseInt(s); }
	static String[] getLine() throws IOException { return br.readLine().split(" "); }	
	static <T> void print(T s) { System.out.print(s); }
	static <T> void println(T s) { System.out.println(s); }
}