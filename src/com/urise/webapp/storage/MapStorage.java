package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Collection;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final TreeMap<String, Resume> resumes = new TreeMap<>();

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public Resume[] getAll() {
        Collection<Resume> allResumes = resumes.values();
        return allResumes.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumes.size();
    }

    @Override
    protected Object getSearchParameter(String uuid) {
        return uuid;
    }

    @Override
    protected boolean elementExists(Object key) {
        return resumes.containsKey(key);
    }

    @Override
    protected void saveElement(Resume resume, Object searchParameter) {
        resumes.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume resume, Object searchParameter) {
        resumes.replace(resume.getUuid(), resume);
    }

    @Override
    protected Resume getElement(Object key) {
        return resumes.get(key);
    }

    @Override
    protected void deleteElement(Object key) {
        resumes.remove(key);
    }
}
