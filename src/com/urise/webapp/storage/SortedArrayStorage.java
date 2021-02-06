package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected Integer getSearchKey(String uuid, String fullName) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        searchKey.setFullName(fullName);
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        index = Math.abs(index) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    protected void replaceDeletedElement(int index) {
        if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }
}
