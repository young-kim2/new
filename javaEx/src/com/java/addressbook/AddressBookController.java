package com.java.addressbook;

import java.util.Scanner;
public class AddressBookController {
protected void addressBook() {
		
		AddressBookService adressService = new AddressBookService();
		AddressBookView adressView = new AddressBookView();
				
		adressView.addressViewStart();
		Scanner sc = new Scanner(System.in);
		while (true) {
			adressView.addressViesMenu();

			switch (sc.nextInt()) {
			case 1:
				adressView.addressViewList();
				continue;
			case 2:
				adressView.addressViewInsert();
				adressService.addressInsert(adressService.addressInfo(sc));
				System.out.println("[등록되었습니다.]\n");
				continue;
			case 3:
				adressView.addressViewDelete();
				adressService.addressDelete(sc.nextInt());
				System.out.println("[삭제되었습니다.]\n");
				continue;
			case 4:
				adressView.addressViewSearch();
				adressService.addressSearch(sc.next());
				continue;
			case 5:
				adressView.addressViewEnd();
				break;
			default:
				adressView.addressViewEr();
				continue;
			}
			sc.close();
			break;
		}

}
}
