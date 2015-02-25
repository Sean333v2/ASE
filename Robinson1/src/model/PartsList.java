package model;

import java.util.ArrayList;

public class PartsList {
	public ArrayList<Part> list;

	public PartsList() {

		list = new ArrayList<Part>();
	}

	public Part getPartById(int id){
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getPersonalId().equals(""+id))
				return list.get(i);
			
		return null;
	}
	
	public boolean findPartById(int id){
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getPersonalId().equals(""+id))
				return true;
			
		return false;
	}
	
	public Part findPart(String s) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPartName().equals(s))
				return list.get(i);
		}
		return null;
	}

	public boolean addPart(Part p) {
		list.add(p);

		return true;
	}

	public boolean removePart(Part p) {
		list.remove(p);

		return true;
	}

	public ArrayList<Part> getList() {
		return list;
	}

	public void display() {
		System.out.println(list.toString());
	}

	public int getAmount() {
		return list.size();
	}

}
