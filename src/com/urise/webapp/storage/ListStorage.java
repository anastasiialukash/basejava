package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> resumes = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < resumes.size(); i++) {
            if (resumes.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveElement(Resume resume, Object searchKey) {
        resumes.add(resume);
    }

    @Override
    protected void updateElement(Resume resume, Object index) {
        resumes.set((Integer) index, resume);
    }

    @Override
    protected Resume getElement(Object index) {
        return resumes.get((Integer) index);
    }

    @Override
    protected void deleteElement(Object searchKey) {
        int index = (Integer) searchKey;
        resumes.remove(index);
    }

    @Override
    protected Resume[] getAllElements() {
        return resumes.toArray(new Resume[0]);
    }

    @Override
    protected boolean elementExists(Object searchKey) {
        return ((Integer) searchKey) >= 0;
    }

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public int size() {
        return resumes.size();
    }
}
