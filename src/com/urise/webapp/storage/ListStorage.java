package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListStorage extends AbstractStorage {
    private final List<Resume> resumes = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid, String fullName) {
        for (int i = 0; i < resumes.size(); i++) {
            if (resumes.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Resume> getAllSorted() {
        return resumes.stream().sorted(RESUME_COMPARATOR).collect(Collectors.toList());
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
