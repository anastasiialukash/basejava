package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;
import java.util.stream.Collectors;

public class MapUuidStorage extends AbstractStorage {
    private final Map<String, Resume> resumes = new TreeMap<>();

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        Collection<Resume> allResumes = resumes.values();
        return allResumes.stream()
                .sorted(RESUME_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public int size() {
        return resumes.size();
    }

    @Override
    protected Object getSearchKey(String uuid, String fullName) {
        return uuid;
    }

    @Override
    protected boolean elementExists(Object key) {
        return resumes.containsKey(key);
    }

    @Override
    protected void saveElement(Resume resume, Object searchKey) {
        resumes.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume resume, Object searchKey) {
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
