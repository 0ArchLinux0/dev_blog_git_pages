import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static int timeNum = 360000;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = toi(br.readLine());
		boolean[] clock1 = new boolean[2 * timeNum];
		boolean[] clock2 = new boolean[timeNum];
		int[] arr = getArr();
		for(int e: arr) clock1[e] = clock1[e + timeNum] = true;
		arr = getArr();
		for(int e: arr) clock2[e] = true;
		print(kmp(clock1, clock2) ? "possible" : "impossible");
	}

	static int[] pi(boolean[] s) {
		int[] pi = new int[s.length];
		int l = 0;
		for(int r = 1; r < s.length; r++) {
			if(l > 0 && s[l] != s[r]) l = pi[l - 1];
			if(s[l] == s[r]) {
				pi[r] = l + 1;
				l++;
			}
		}
		return pi;
	}

	static boolean kmp(boolean[] t, boolean[] s) {
		int[] pi = pi(s);
		int r = 0;
		for(int l = 0; l < t.length; l++) {
			if(r > 0 && t[l] != s[r]) r = pi[r - 1];
			if(t[l] == s[r]) {
				if(r == s.length - 1) return true;
				r++;
			}
		}
		return false;
	}

	static int toi(String s) { return Integer.parseInt(s); }
	static String[] getLine() throws IOException { return br.readLine().split(" "); }	
	static int[] getArr() throws IOException { return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); }
	static <T> void print(T s) { System.out.print(s); }
	static <T> void println(T s) { System.out.println(s); }
}