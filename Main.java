import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String t = br.readLine(), p = br.readLine();
		int[] kmp = new int[p.length()]; 
		ArrayList<Integer> al = new ArrayList<>();
		getKmp(kmp, p);
		for(int e: kmp) print(e);
		println("");
		int l = 0, r = 0, cnt = 0;
		while(l < t.length()) {
			if(t.charAt(l) != p.charAt(r)) {
				// l++;
				if(r > 0) r = kmp[r - 1];
				else r = 0;
			}
			if(t.charAt(l) == p.charAt(r)) {
				if(r == p.length() - 1) {
					cnt++;
					al.add(l + 2 - p.length());
					r = kmp[r];
				}
				else {
					r++;
				}
			} 
			l++;
		}
		println(cnt);
		for(int e: al) sb.append(e).append(" ");
		print(sb);
	}

	static public void getKmp(int[] kmp, String p) {
		int l = 0;
		for(int r = 1; r < p.length(); r++) {
			if(l > 0 && p.charAt(l) != p.charAt(r))
				l = kmp[l - 1];
			if(p.charAt(l) == p.charAt(r)) {
				kmp[r] = ++l;
			}
		}
	}

	static int toi(String s) { return Integer.parseInt(s); }
	static String[] getLine() throws IOException { return br.readLine().split(" "); }	
	static int[] getArr() throws IOException { return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); }
	static <T> void print(T s) { System.out.print(s); }
	static <T> void println(T s) { System.out.println(s); }
}