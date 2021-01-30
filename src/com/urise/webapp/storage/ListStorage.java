package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> resumes = new ArrayList<>();

    @Override
    protected Integer getSearchParameter(String uuid) {
        for (int i = 0; i < resumes.size(); i++) {
            if (resumes.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveElement(Resume resume, Object searchParameter) {
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
    protected void deleteElement(Object searchParameter) {
        int index = (Integer) searchParameter;
        resumes.remove(index);
    }

    @Override
    protected boolean elementExists(Object searchParameter) {
        return ((Integer) searchParameter) >= 0;
    }

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumes.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumes.size();
    }
}
