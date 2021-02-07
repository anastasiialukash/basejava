package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> resumes = new TreeMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return resumes.get(uuid);
    }

    @Override
    protected boolean elementExists(Object searchKey) {
        return resumes.containsValue(searchKey);
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
    protected Resume getElement(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void deleteElement(Object searchKey) {
        Resume resume = (Resume) searchKey;
        resumes.remove(resume.getUuid(), resume);
    }

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
}
