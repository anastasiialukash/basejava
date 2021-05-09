package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void writeData(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeCollection(dos, resume.getSections().entrySet(), entry -> {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection) section).getList(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((OrganisationSection) section).getOrgList(), org -> {
                            Link homePage = org.getHomePage();
                            dos.writeUTF(homePage.getName());
                            dos.writeUTF(homePage.getUrl());
                            writeCollection(dos, org.getExperience(), experience -> {
                                writeDate(dos, experience.getStartDate());
                                writeDate(dos, experience.getEndDate());
                                dos.writeUTF(experience.getTitle());
                                dos.writeUTF(experience.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume readData(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readElements(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readElements(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private <E> void writeCollection(DataOutputStream dos, Collection<E> collection, WriteElement<E> writer) throws IOException {
        dos.writeInt(collection.size());
        for (E element : collection) {
            writer.write(element);
        }
    }

    private void writeDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void readElements(DataInputStream dis, CollectionElement element) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            element.read();
        }
    }

    private <E> List<E> readList(DataInputStream dis, ReadElement<E> reader) throws IOException {
        int size = dis.readInt();
        List<E> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new OrganisationSection(
                        readList(dis, () -> new Organisation(new Link(dis.readUTF(), dis.readUTF()),
                                readList(dis, () -> new Organisation.Experience(readDate(dis), readDate(dis), dis.readUTF(), dis.readUTF())))));
            default:
                throw new IllegalStateException();
        }
    }

    private interface CollectionElement {
        void read() throws IOException;
    }

    private interface ReadElement<E> {
        E read() throws IOException;
    }

    private interface WriteElement<E> {
        void write(E e) throws IOException;
    }
}
