package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializer.StreamSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private final File directory;
    private final StreamSerializer streamSerializer;

    protected FileStorage(File directory, StreamSerializer streamSerializer) {
        this.directory = directory;
        this.streamSerializer = streamSerializer;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
    }


    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void saveElement(Resume resume, File file) {
        try {
            file.createNewFile();
            streamSerializer.writeData(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void updateElement(Resume resume, File file) {
        try {
            streamSerializer.writeData(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Writing file error", file.getName(), e);
        }
    }

    @Override
    protected Resume getElement(File file) {
        Resume resume;
        try {
            resume = streamSerializer.readData(new BufferedInputStream((new FileInputStream(file))));
        } catch (IOException e) {
            throw new StorageException("Reading file error", file.getName(), e);
        }
        return resume;
    }

    @Override
    protected void deleteElement(File file) {
        File[] allFiles = directory.listFiles();
        if (allFiles != null) {
            for (File entry : allFiles) {
                if (entry.equals(file)) {
                    file.delete();
                }
            }
        }
    }

    @Override
    protected List<Resume> getAllElements() {
        File[] allFiles = directory.listFiles();
        List<Resume> resumes = new ArrayList<>();
        if (allFiles != null) {
            for (File file : allFiles) {
                try {
                    Resume resume = streamSerializer.readData(new BufferedInputStream((new FileInputStream(file))));
                    resumes.add(resume);
                } catch (IOException e) {
                    throw new StorageException("Reading file error", file.getName(), e);
                }

            }
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] allFiles = directory.listFiles();
        if (allFiles != null) {
            for (File file : allFiles) {
                file.delete();
            }
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.listFiles()).length;
    }
}
