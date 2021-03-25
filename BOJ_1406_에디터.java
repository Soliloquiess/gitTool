package BOJ;

//public class BOJ_1406_에디터 {
//
//}

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

public class BOJ_1406_에디터 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		int M = Integer.parseInt(br.readLine());

		LinkedList<Character> list = new LinkedList<Character>();

		for(int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}

		//index 변수를 이용해 cursor의 위치를 다루고자 함
		int index = list.size(); //처음 시작시 커서는 문장의 맨 뒤에서 시작해야함 (ex. abc|)

		for(int i = 0; i < M; i++) {
			String command = br.readLine();
			char c = command.charAt(0);
            
			switch(c) {
			case 'L':
				if(index != 0) {
					index = index - 1;
				}

				break;
			case 'D':
				if(index != list.size()) {
					index = index + 1;
				}

				break;
			case 'B':
				if(index != 0) {
					list.remove(index-1);
					index--;
				}
				break;
			case 'P':
				char t = command.charAt(2);
				list.add(index, t);
				index++;

				break;
			default:
				break;
			}
		}
		for(Character chr : list) {
			bw.write(chr);
		}
		
		bw.flush();
		bw.close(); 
	}
}

/*
 import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		int M = Integer.parseInt(br.readLine());

		LinkedList<Character> list = new LinkedList<Character>();

		for(int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}

		//index 변수를 이용해 cursor의 위치를 다루고자 함
		int index = list.size(); //처음 시작시 커서는 문장의 맨 뒤에서 시작해야함 (ex. abc|)

		for(int i = 0; i < M; i++) {
			String command = br.readLine();
			char c = command.charAt(0);
            
			switch(c) {
			case 'L':
				if(index != 0) {
					index = index - 1;
				}

				break;
			case 'D':
				if(index != list.size()) {
					index = index + 1;
				}

				break;
			case 'B':
				if(index != 0) {
					list.remove(index-1);
					index--;
				}
				break;
			case 'P':
				char t = command.charAt(2);
				list.add(index, t);
				index++;

				break;
			default:
				break;
			}
		}
		for(Character chr : list) {
			bw.write(chr);
		}
		
		bw.flush();
		bw.close(); 
	}
}

*/

/*
 3. ListIterator 사용
ListIterator는 Iterator를 상속한 인터페이스다.

Iterator의 단방향 탐색과 달리 양방향 탐색이 가능하다.

그렇기에 이 문제처럼 양방향으로 이동하면서 수정하기에 효율적이다.

Iterator / ListIterator
 
[Java] Iterator / ListIterator

Interface Iterator java.util Type Parameters : E - the type of elements returned by this iterator All Known Subinterfaces : ListIterator, XMLEventReader All Known Implementing Classes : BeanCo..

minhamina.tistory.com
3 코드
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		int M = Integer.parseInt(br.readLine());

		LinkedList<Character> list = new LinkedList<Character>();

		for(int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}

		//iterator 메소드 호출 
		ListIterator<Character> iter = list.listIterator();
		//처음 커서는 문장의 맨 뒤에 있어야하기 때문에 커서를 맨뒤로 이동시켜줌 (ex. abc|)
		while(iter.hasNext()) {
			iter.next();
		}
	
		for(int i = 0; i < M; i++) {
			String command = br.readLine();
			char c = command.charAt(0);
			switch(c) {
			case 'L':
				if(iter.hasPrevious())
					iter.previous();

				break;
			case 'D':
				if(iter.hasNext())
					iter.next();

				break;
			case 'B':
				//remove()는 next()나 previous()에 의해 반환된 가장 마지막 요소를 리스트에서 제거함
				if(iter.hasPrevious()) {
					iter.previous();
					iter.remove();
				}
				break;
			case 'P':
				char t = command.charAt(2);
				iter.add(t);

				break;
			default:
				break;
			}
		}
		for(Character chr : list) {
			bw.write(chr);
		}
		
		bw.flush();
		bw.close(); 
	}
}
결과 - 성공
 */



/*
 4. Stack을 이용한 구현
LinkedList를 이용해 구현했지만 Stack을 이용한다면 초장부터 시간 초과의 늪에 빠지지 않을 수 있다.

 

스택은 모든 연산이 O(1)의 시간 복잡도를 가지기 때문에 시간 초과에 걸리지 않는다. (*시간 복잡도에 대한 내용 정리 필요)

커서의 왼쪽 오른쪽 구분을 위해 두 개의 스택을 사용한다.

예제 1의 경우

 

abcd
3
P x
L
P y
 


맨 처음 커서는 입력 문자열의 맨 뒤에 위치하기 때문에 문자열을 모두 왼쪽 스택에 넣어준다.

이후 차례로 명령어를 처리하면서 커서가 왼쪽으로 이동하면 왼쪽 스택의 가장 상단 요소를 오른쪽 스택에 pop() 시켜준다.

그리고 커서가 오른쪽으로 이동하면 오른쪽 스택의 가장 상단 요소를 왼쪽 스택에 pop() 시켜주며 마치 커서가 이동하는 것처럼 구현한다.

 


마지막 모든 명령어를 처리한 후에는 스택이 LIFO 구조이기 때문에 왼쪽 스택의 모든 요소들을 오른쪽 스택에 옮긴 후 오른쪽 스택을 모두 pop() 시키며 결과를 출력한다.

 

4 코드
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		int M = Integer.parseInt(br.readLine());

		Stack<String> leftSt = new Stack<String>();
		Stack<String> rightSt = new Stack<String>();
        
		//처음 커서는 문장의 맨 뒤에서 시작하기 때문에 왼쪽 스택에 초기 문자를 모두 넣어줌 (ex. abc|)
		String[] arr = str.split("");
		for(String s : arr) { //Enhanced For Loop 사용 
			leftSt.push(s); 
		}
		
		for(int i = 0; i < M; i++) {
			String command = br.readLine();
			char c = command.charAt(0);
			//StringTokenizer st = new StringTokenizer(reader.readLine());
			//String c = st.nextToken();
			
			switch(c) {
			case 'L':
				if(!leftSt.isEmpty())
					rightSt.push(leftSt.pop());

				break;
			case 'D':
				if(!rightSt.isEmpty())
					leftSt.push(rightSt.pop());

				break;
			case 'B':
				if(!leftSt.isEmpty()) {
					leftSt.pop();
				}
				break;
			case 'P':
				char t = command.charAt(2);
				leftSt.push(String.valueOf(t));
				//leftSt.push(st.nextToken());

				break;
			default:
				break;
			}
		}
        
		//Stack은 LIFO 구조이기 때문에
		//왼쪽 스택에 있는 데이터들을 모두 오른쪽으로 옮긴 뒤에 오른쪽에 있는 모든 내용을 출력한다.
		while(!leftSt.isEmpty())
			rightSt.push(leftSt.pop());
		
		while(!rightSt.isEmpty())
			bw.write(rightSt.pop());
		
		bw.flush();
		bw.close(); 
	}
}

*/
