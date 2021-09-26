package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader in = new BufferedReader(new FileReader("todolist.txt"));
			String oneline;
			while((oneline = in.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				TodoItem t = new TodoItem(title, desc, category, due_date);
				t.setCurrent_date(current_date);
				if (l.isDuplicate(title)) {
					return;
				}
				else l.addItem(t);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter("todolist.txt");
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[Add Item]\n"
				+ "Enter title of list to add > ");
		
		title = sc.nextLine();
		
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		
		System.out.print("Categoty > ");
		category = sc.nextLine();
		
		System.out.print("Description > ");
		desc = sc.nextLine();
		
		System.out.print("Due date > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		String option;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[Delete Item]\n"
				+ "Enter number of list to delete > ");
		int number = sc.nextInt();
		
		for (TodoItem item : l.getList()) {
			if (number == (l.indexOf(item)+1)) {
				System.out.println(number + ". " + item.toString());
				break;
			}
		}
		
		System.out.print("Do you want to delete the selected list? (y/n) > ");
		option = sc.next();

		if(option.charAt(0) == 'y') {
			for (TodoItem item : l.getList()) {
				if (number == (l.indexOf(item)+1)) {
					l.deleteItem(item);
					System.out.println("Item deleted.");
					break;
				}
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[Edit Item]\n"
				+ "Enter the number of the item to edit > ");
//		String title = sc.next().trim();
		int number = sc.nextInt();
		if (!l.isDuplicateNum(number)) {
			System.out.println("Number do not exist.");
			return;
		}
		else {
			for (TodoItem item : l.getList()) {
				if (number == (l.indexOf(item)+1)) {
					System.out.println(number + ". " + item.toString());
					break;
				}
			}
		}

		System.out.print("Enter new title > ");
		String new_title = sc.next();
		
		if (l.isDuplicate(new_title)) {
			System.out.print("Title already exist!");
			return;
		}
		
		System.out.print("Enter new description > ");
		String new_description = sc.nextLine();
		new_description = sc.nextLine();
		
		System.out.print("Enter new category > ");
		String new_category = sc.nextLine();
		
		System.out.print("Enter new due date > ");
		String new_duedate = sc.nextLine();
		
		for (TodoItem item : l.getList()) {
			if (l.indexOf(item)+1 == number) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_duedate);
				l.addItem(t);
				System.out.println("Item updated.");
				break;
			}
		}
	}

	public static void findList(TodoList l, String key) {		
		ArrayList<String> list = new ArrayList<String>();
		int i = 1;
		
		for(TodoItem item: l.getList()) {
			if(item.findString().contains(key)){
				list.add( i +". " + "[" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
			}
			i++;
		}		
		for(String item: list) {
			System.out.println(item);
		}
	}
	
	public static void findCategory(TodoList l, String category) {
		int count = 0, i = 0;
		for(TodoItem item : l.getList() ) {
			i++;
			if(item.getCategory().equals(category)) {
				System.out.println((i+1) + ". " + item.toString());
				count++;
			}
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void listCategory(TodoList l) {
		Set<String> list = new HashSet<String>();
		for(TodoItem item : l.getList()) {
			list.add(item.getCategory());
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			String st = (String)it.next();
			System.out.print(st);
			if (it.hasNext()) {
				System.out.print(" / ");
			}
		}
		System.out.println("\nTotal of " + list.size() + " different categories in the list.");
	}
	
	public static void listAll(TodoList l) {
		int i = 0;
		System.out.println("[Whole List, total of " + l.size() + " lists.]");
		
		for (TodoItem item : l.getList()) {
			i++;
			System.out.println(i + ". " + item.toString());
		}
	}
}

















