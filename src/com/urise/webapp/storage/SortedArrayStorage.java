package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> SORT_ARR_COMPARATOR = Comparator.comparing(Resume::getUuid);

    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "testName");
        return Arrays.binarySearch(storage, 0, size, searchKey, SORT_ARR_COMPARATOR);
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
