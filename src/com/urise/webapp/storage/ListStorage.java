package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
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
    public void update(Resume resume) {
        int index = getElementIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        resumes.set(index, resume);
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
    public Resume get(String uuid) {
        int index = getElementIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return resumes.get(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getElementIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        resumes.remove(index);
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
}
