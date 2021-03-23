package mini2;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PhoneBookApp {

	public static void main(String[] args) {
		
    Scanner scanner = new Scanner(System.in);
	System.out.println("**************************************");
	System.out.println("*            전화번호 관리 프로그램               *");
	System.out.println("**************************************");
	System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
	System.out.println("______________________________________");
	System.out.print(">메뉴번호:");
	int num = scanner.nextInt();
	
	if(num==1) {
		System.out.print("<1.리스트>\n");
		listPhoneBook();
	}else if(num==2) {
		System.out.print("<2.등록>\n");
		insertPhoneBook();
	}else if(num==3) {
		System.out.print("<3.삭제>\n");
		deletePhoneBook();
	}else if(num==4) {
		System.out.print("<4.검색>\n");
		searchPhoneBook();
	}else if(num==5) {
		System.out.println("***********************************");
	    System.out.println("*            감사합니다       *");
	    System.out.println("***********************************");
	}else{
	  System.out.println("[다시 입력해주세요]");
	}
	
	}
	private static void listPhoneBook() {
		PhoneBookDao dao=new PhoneBookDaoOracleImpl();
		List<PhoneBookVo> list=dao.getList();
		Iterator<PhoneBookVo> it=list.iterator();
		
		while(it.hasNext()) {
			PhoneBookVo vo=it.next();
			System.out.printf("%d.%s%s     %s%n",vo.getId(),vo.getName(),vo.getHp(),vo.getTel());
			
		}
	}
	private static void insertPhoneBook() {
		Scanner scanner=new Scanner(System.in);
		System.out.print(">이름:");
		String name=scanner.next();
		System.out.print(">휴대전화:");
		String hp=scanner.next();
		System.out.print(">집전화:");
		String tel=scanner.next();
		
		PhoneBookVo vo=new PhoneBookVo(name,hp,tel);
		
		PhoneBookDao dao=new PhoneBookDaoOracleImpl();
		boolean success=dao.insert(vo);
		
		System.out.println("phone_book INSERT:"+(success?"[등록되었습니다.]":"실패"));
		scanner.close();
	}
private static void deletePhoneBook() {
	Scanner scanner=new Scanner(System.in);
	System.out.print(">번호:");
	long PhoneBookId=scanner.nextLong();
	
	PhoneBookDao dao=new PhoneBookDaoOracleImpl();
	boolean success=dao.delete(PhoneBookId);
	
	System.out.println("Phone_book DELETE:"+(success? "[삭제되었습니다.]":"실패"));
	scanner.close();
}

private static void searchPhoneBook() {
	Scanner scanner=new Scanner(System.in);
	System.out.print(">이름:");
	String keyword=scanner.next();
	
	PhoneBookDao dao=new PhoneBookDaoOracleImpl();
	List<PhoneBookVo>list=dao.search(keyword);
	
	Iterator<PhoneBookVo>it=list.iterator();
	
	while(it.hasNext()) {
		PhoneBookVo vo=it.next();
		System.out.println(vo);
	}
	scanner.close();
}
}