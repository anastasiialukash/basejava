package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> resumes = new ArrayList<>();

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

    @Override
    protected void saveResume(Resume resume, int index) {
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        resumes.add(resume);
    }

    @Override
    protected int getSize() {
        return resumes.size();
    }

    @Override
    protected Resume[] getAllResume() {
        return resumes.toArray(new Resume[0]);
    }

    @Override
    protected void clearStorage() {
        resumes.clear();
    }
}
