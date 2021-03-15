package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getSearchKeyForNotExistedElement(resume.getUuid());
        saveElement(resume, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getSearchKeyForExistedElement(uuid);
        deleteElement(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getSearchKeyForExistedElement(uuid);
        return getElement(searchKey);
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getSearchKeyForExistedElement(resume.getUuid());
        updateElement(resume, searchKey);
    }

    private SK getSearchKeyForExistedElement(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + "does not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getSearchKeyForNotExistedElement(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + "already exists");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("Call getAllSorted");
        List<Resume> list = getAllElements();
        Collections.sort(list);
        return list;
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void saveElement(Resume resume, SK searchKey);

    protected abstract void updateElement(Resume resume, SK searchKey);

    protected abstract Resume getElement(SK searchKey);

    protected abstract void deleteElement(SK searchKey);

    protected abstract List<Resume> getAllElements();

}
