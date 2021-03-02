package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> resumes = new TreeMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return resumes.get(uuid);
    }

    @Override
    protected boolean elementExists(Resume searchKey) {
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
    protected Resume[] getAllElements() {
        Collection<Resume> allResumes = resumes.values();
        return allResumes.toArray(new Resume[0]);
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
