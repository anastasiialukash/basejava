package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> resumes = new ArrayList<>();

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public void save(Resume r) {
        int index = getElementIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        resumes.add(r);
    }

    @Override
    public Resume[] getAll() {
        return resumes.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumes.size();
    }

    @Override
    protected int getElementIndex(String uuid) {
        for (int i = 0; i < resumes.size(); i++) {
            if (resumes.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void deleteElement(int index) {
        resumes.remove(index);
    }

    @Override
    protected Resume getResume(int index) {
        return resumes.get(index);
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        resumes.set(index, resume);
    }
}
