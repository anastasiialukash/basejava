package com.urise.webapp.storage;

public abstract class AbstractStorage implements Storage {

    protected abstract int getElementIndex(String uuid);

}
