import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static int max = 0, maxIdx = 0;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] line = getLine();
		int n = toi(line[0]),  k = toi(line[1]), range = 100001;
		int[] dp = new int[range];
		ArrayList<Integer> al = new ArrayList<>();
		int time = 0;
		dp[n] = -1;
		al.add(n);
		loop:
		for(int i = 0; i < range; i++) {
			ArrayList<Integer>temp = new ArrayList<>();
			for(int e: al) {
				if(e == k) {
					time = i;
					break loop;
				}
				if(e > 0 && dp[e - 1] == 0) {
					temp.add(e-1);
					dp[e-1] = i + 1;
				}
				if(e < range - 2 && dp[e + 1] == 0) {
					temp.add(e+1);
					dp[e+1] = i + 1;
				}
				if(2 * e <= range - 1 && dp[2 * e] == 0) {
					temp.add(2*e);
					dp[2*e] = i + 1;
				}
			}
			al.addAll(temp);
		}
		println(time);
		Stack<Integer> stack = new Stack<>();
		stack.push(k);
		int idx = k, val = time;
		while(val != 1) {
			if(idx + 1 < range && dp[idx + 1] == val - 1) {
				idx++;
				val--;
				stack.add(idx);
				continue;
			}
			if(idx > 0 && dp[idx - 1] == val - 1) {
				idx--;
				val--;
				stack.add(idx);
				continue;
			}
			if((idx&1) == 0 &&  dp[idx/2] == val - 1) {
				idx/=2;
				val--;
				stack.add(idx);
			}
		}
		stack.add(n);
		while(!stack.isEmpty()) sb.append(stack.pop()).append(" ");
		sb.append("\n");
		// for(int e: stack) sb.append(dp[e]).append(" ");
		print(sb);
	}

	static int toi(String s) { return Integer.parseInt(s); }
	static String[] getLine() throws IOException { return br.readLine().split(" "); }	
	static <T> void print(T s) { System.out.print(s); }
	static <T> void println(T s) { System.out.println(s); }
}