package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    private String uuid;
    private String fullName;
    private Map<ContactsType, String> contactsMap = new EnumMap<>(ContactsType.class);
    private Map<SectionType, AbstractSection> sectionsMap = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(
            String uuid,
            String fullName,
            Map<ContactsType, String> contactsMap,
            Map<SectionType, AbstractSection> sectionsMap) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.contactsMap = contactsMap;
        this.sectionsMap = sectionsMap;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(uuid).append(':').append(fullName);
        builder.append("\n");

        for (Map.Entry<ContactsType, String> el : contactsMap.entrySet()) {
            builder.append(el.getKey()).append(el.getValue());
            builder.append("\n");
        }

        for (Map.Entry<SectionType, AbstractSection> el : sectionsMap.entrySet()) {
            builder.append(el.getKey()).append(el.getValue().toString());
            builder.append("\n");
        }

        return builder.toString();
    }

    @Override
    public int compareTo(Resume o) {
        int compResult = fullName.compareTo(o.fullName);
        if (compResult != 0) {
            return compResult;
        } else {
            return uuid.compareTo(o.uuid);
        }
    }
}
