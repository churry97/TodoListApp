package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<TodoList Command List>");
        System.out.println("add - add item");
        System.out.println("del - delete item");
        System.out.println("edit - edit item ");
        System.out.println("ls - list all items");
        System.out.println("find - print the list according to keyword");
        System.out.println("ls_find_cate - find the list category");
        System.out.println("ls_cate - list all the categories");
        System.out.println("ls_name_asc - list name by ascending order");
        System.out.println("ls_name_desc - list name by decending order");
        System.out.println("ls_date - list by date order");
        System.out.println("ls_date_desc - list by date decending order");
        System.out.println("exit - exit program");
    }
    public static void prompt() {
    	System.out.print("\nCommand > ");
    }
}
