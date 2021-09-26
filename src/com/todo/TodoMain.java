package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		Menu.displaymenu();
		do {
			TodoUtil.loadList(l, "todolist.txt");
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				TodoUtil.saveList(l, "todolist.txt");
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("Ordered by name.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("Reversly ordered by name.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("Ordered by date.");
				isList = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			case "find":
				String input = sc.next();
				TodoUtil.findList(l, input);
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("Reversly ordered by date.");
				isList = true;
				break;
				
			case "find_cate":
				String cate = sc.next();
				TodoUtil.findCategory(l, cate);
				break;
				
			case "ls_cate":
				TodoUtil.listCategory(l);
				break;

			case "exit":
				TodoUtil.saveList(l, "todolist.txt");
				System.out.println("Successfully saved in file.");
				quit = true;
				break;

			default:
				System.out.println("Please enter command from above. (도움말 - help)");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
	}
}
