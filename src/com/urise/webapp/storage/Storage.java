package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void clear();

    void update(Resume resume);

    void save(Resume r);

    // TODO: 07.02.2021 return the initial signature (???)
    Resume get(String uuid, String fullName);

    void delete(String uuid);

    List<Resume> getAllSorted();

    int size();
}
