package ru.geekbrains.finmanager.models;

import java.util.Comparator;

public class RecordDateComparator implements Comparator<Record> {

	@Override
	public int compare(Record o1, Record o2) {
		return o1.getDate().compareTo(o2.getDate());
	}

}
