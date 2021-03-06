package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    //    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();
//    static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();
    static final ListStorage ARRAY_STORAGE = new ListStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "name1");
        Resume r2 = new Resume("uuid2", "name2");
        Resume r3 = new Resume("uuid3", "name3");
        Resume r4 = new Resume("uuid4", "name4");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(new Resume("uuid0", "name0"));

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

//        ARRAY_STORAGE.save(r1);
//        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
//        ARRAY_STORAGE.update(r1);
//        printAll();
        ARRAY_STORAGE.save(r4);
        printAll();
        ARRAY_STORAGE.update(r4);
        printAll();
//        ARRAY_STORAGE.delete(r1.getUuid());
//        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r.toString());
        }
    }
}
