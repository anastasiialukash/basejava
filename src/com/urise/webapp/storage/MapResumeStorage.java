package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> resumes = new TreeMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return resumes.get(uuid);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return resumes.containsValue(searchKey);
    }

    @Override
    protected void saveElement(Resume resume, Resume searchKey) {
        resumes.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume resume, Resume searchKey) {
        resumes.replace(resume.getUuid(), resume);
    }

    @Override
    protected Resume getElement(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected void deleteElement(Resume searchKey) {
        resumes.remove(searchKey.getUuid(), searchKey);
    }

    @Override
    protected List<Resume> getAllElements() {
        return new ArrayList<>(resumes.values());
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
