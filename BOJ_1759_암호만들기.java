package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기 {

	static int L, C;
	static char arr[], result[];
	static boolean isSelected[];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String msg = in.readLine();
		st = new StringTokenizer(msg);
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];
		isSelected = new boolean[C];
		result = new char[L];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		combination(0,0);
	}
	private static void combination(int i, int j) {
		if( i == L) {
			int vowel = 0;
			int cons = 0;
			for (int k = 0; k < L; k++) {
				if(result[k] == 'a' || result[k] == 'e' || result[k] == 'i' || 
						result[k] == 'o' || result[k] == 'u')
					vowel++;
				else
					cons++;
			}
			if(vowel >= 1 && cons >= 2) {
				for (int k = 0; k < L; k++) {
					System.out.print(result[k]);
				}
				System.out.println();
			}
			return;
		}
		for (int k = j; k < C; k++) {
			if(isSelected[k]) continue;
			isSelected[k] = true;
			result[i] = arr[k];
			combination(i+1,k+1);
			isSelected[k] = false;
		}
	}

}