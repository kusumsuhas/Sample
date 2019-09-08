package com.intellect.auto.bean;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Event> m1 = new HashMap<>();
		
		Event e = new Event();
		Event e1 = new Event();
		Event e2 = new Event();
		e.setAction("A");
		e1.setAction("B");
		e2.setAction("C");
		
		m1.put("A1", e);
		m1.put("A2", e);
		m1.put("A3", e);
		

	}

}
