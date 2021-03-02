package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator
            .comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

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
        if (!elementExists(searchKey)) {
            LOG.warning("Resume " + uuid + "does not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getSearchKeyForNotExistedElement(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (elementExists(searchKey)) {
            LOG.warning("Resume " + uuid + "already exists");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("Call getAllSorted");
        return Arrays.stream(getAllElements())
                .sorted(RESUME_COMPARATOR)
                .collect(Collectors.toList());
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean elementExists(SK searchKey);

    protected abstract void saveElement(Resume resume, SK searchKey);

    protected abstract void updateElement(Resume resume, SK searchKey);

    protected abstract Resume getElement(SK searchKey);

    protected abstract void deleteElement(SK searchKey);

    protected abstract Resume[] getAllElements();

}
