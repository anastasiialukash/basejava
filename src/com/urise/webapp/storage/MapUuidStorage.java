package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> resumes = new TreeMap<>();

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public int size() {
        return resumes.size();
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean elementExists(String key) {
        return resumes.containsKey(key);
    }

    @Override
    protected void saveElement(Resume resume, String searchKey) {
        resumes.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume resume, String searchKey) {
        resumes.replace(resume.getUuid(), resume);
    }

    @Override
    protected Resume getElement(String key) {
        return resumes.get(key);
    }

    @Override
    protected void deleteElement(String key) {
        resumes.remove(key);
    }

    @Override
    protected Resume[] getAllElements() {
        Collection<Resume> allResumes = resumes.values();
        return allResumes.toArray(new Resume[0]);
    }
}
