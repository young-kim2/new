package mini;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.xml.transform.Source;

public class monitor {
	private static final Object name = null;
	static List<String> lst = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("**************************************");
		System.out.println("*            전화번호 관리 프로그램               *");
		System.out.println("**************************************");
		System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
		System.out.println("______________________________________");
		System.out.print(">메뉴번호:");
		int num = scanner.nextInt();
		if (num == 1) {
			System.out.println("<1.리스트>");
			loadData();
			printData();

		} else if (num == 2) {
			loadData();
			System.out.println("<2.등록>");
			System.out.print(">이름");
			String name = scanner.next();
//			lst.add(name);
			System.out.print(">휴대전화:");
			String hn = scanner.next();
//			lst.add(pn);
			System.out.print(">집전화");
			String pn = scanner.next();
//			lst.add(hn);
			insertAddress(name, hn, pn);
			System.out.println("[등록되었습니다.]");

		} else if (num == 3) {
			loadData();
			System.out.println("<3.삭제>");
			int rm = scanner.nextInt();
			lst.remove(rm);
			removeEx();
			System.out.println("[삭제되었습니다.]");
		}else if(num==4) {
			System.out.println("<4.검색>");
			System.out.print(">이름:");
			String nm=scanner.next();
			if(list.equals(nm)) {
				searchEx();
			}
		}
	}
	
	private static void loadData() {
		lst.clear();
		try {
			BufferedReader br = new BufferedReader(new FileReader("D:\\list.txt"));
			String list;

			while ((list = br.readLine()) != null) {
				lst.add(list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void printData() {
		Iterator<String> it = lst.iterator();
		
		int count = 0;
		
		while (it.hasNext()) {
			String line = it.next();
			String[] fields = line.split(",");
			System.out.printf("%d %s %s %s%n", count + 1, fields[0], fields[1], fields[2]);
			count++;
		}
	}
	
	private static void insertAddress(String name, String hp, String pn) {
		String newAddress = String.format("%s,%s,%s", name, hp, pn);
		lst.add(newAddress);
		
		//	저장
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\\\list.txt"));
			Iterator<String> it = lst.iterator();
			
			while(it.hasNext()) {
				String line = it.next();
				bw.write(line);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			
		}
		 
		
	}
	private static void removeEx() {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\\\list.txt"));
			Iterator<String> it = lst.iterator();
			
			while(it.hasNext()) {
				String line = it.next();
				bw.write(line);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			
		}
		 
		
	}
		
		private static void searchEx() {
			try {
				BufferedReader br = new BufferedReader(new FileReader("D:\\list.txt"));
				String list;

				while ((list = br.readLine()) != null) {
					lst.add(list);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		   Iterator<String> it = lst.iterator();
		
		int count = 0;
		
		while (it.hasNext()) {
			String line = it.next();
			String[] fields = line.split(",");
			System.out.printf("%d %s %s %s%n", count + 1, fields[0], fields[1], fields[2]);
			count++;
		}
				
			}
		
		}
	 
	
	
	

	


