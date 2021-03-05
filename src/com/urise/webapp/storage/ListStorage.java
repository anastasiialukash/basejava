package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
    protected void saveElement(Resume resume, Integer searchKey) {
        resumes.add(resume);
    }

    @Override
    protected void updateElement(Resume resume, Integer index) {
        resumes.set(index, resume);
    }

    @Override
    protected Resume getElement(Integer index) {
        return resumes.get(index);
    }

    @Override
    protected void deleteElement(Integer searchKey) {
        int index = searchKey;
        resumes.remove(index);
    }

    @Override
    protected List<Resume> getAllElements() {
        return new ArrayList<>(resumes);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
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
