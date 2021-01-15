package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getElementIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void save(Resume r) {
        int index = getElementIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("The storage is overflowed");
        } else if (getElementIndex(r.getUuid()) >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exists");
        } else {
            index = Math.abs(index) - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
            size++;
        }

    }

    @Override
    public void delete(String uuid) {
        int index = getElementIndex(uuid);
        if (index >= 0) {
            if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + " is not found");
        }
    }
}
