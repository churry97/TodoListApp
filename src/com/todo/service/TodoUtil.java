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
				String title = st.nextToken();
				String desc = st.nextToken();
				String current_date = st.nextToken();
				
				TodoItem t = new TodoItem(title, desc);
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
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[Add Item]\n"
				+ "Enter title of list to add > ");
		
		title = sc.nextLine();
		
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		
		System.out.print("Description > ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[Delete Item]\n"
				+ "Enter title of list to delete > ");
		
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("Item deleted.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[Edit Item]\n"
				+ "Enter the title of the item to edit > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("Title do not exist.");
			return;
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
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("Item updated.");
			}
		}
	}

	public static void listAll(TodoList l) {
		System.out.println("[Whole List]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
}
