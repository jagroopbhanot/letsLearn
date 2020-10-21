package selfLearning;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Testrules {
	public static void main(String[] args) {
		
		//calcualateTimeForGetCallLinkList();
		//calcualteTimeForGetCallArrayList();
		
		calcualateTimeForAddCallLinkList();
		calcualteTimeForAddCallArrayList();
		
		
	}

	private static void calcualteTimeForAddCallArrayList() {
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("1");
		arrayList.add("9");
		arrayList.add("5");
		arrayList.add("7");
		arrayList.add("3");
	
		long startTime = System.nanoTime();
		arrayList.add("4");
		long stopTime = System.nanoTime();

		System.out.println("------Time taken for ADD Operation ---> ArrayList-->" + (stopTime-startTime));

		
	}

	private static void calcualateTimeForAddCallLinkList() {
		LinkedList<String> linkList = new LinkedList<String>();
		linkList.add("1");
		linkList.add("9");
		linkList.add("5");
		linkList.add("7");
		linkList.add("3");

		long startTime = System.nanoTime();
		linkList.add("4");
		long stopTime = System.nanoTime();

		System.out.println("------Time taken for ADD Operation ---> LinkedList-->" + (stopTime-startTime));

		
	}

	private static void calcualteTimeForGetCallArrayList() {
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("1");
		arrayList.add("9");
		arrayList.add("5");
		arrayList.add("7");
		arrayList.add("3");
		
		long startTime = System.nanoTime();

		String a = arrayList.get(3);
		
		long stopTime = System.nanoTime();

		System.out.println("------Time taken for GET Operation --->  ArrayList-->" + (stopTime-startTime));
	}
	private static void calcualateTimeForGetCallLinkList() {
		LinkedList<String> linkList = new LinkedList<String>();
		linkList.add("1");
		linkList.add("9");
		linkList.add("5");
		linkList.add("7");
		linkList.add("3");

		long startTime = System.nanoTime();

		String s = linkList.get(3);
		
		long stopTime = System.nanoTime();

		System.out.println("------Time taken for GET Operation --->  LinkList-->" + (stopTime-startTime));
	}
}
