import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        storage.getAll();
        ArrayList<String> list = new ArrayList<>();
        list.add(UUID_1);
        list.add(UUID_2);
        list.add(UUID_3);
        Assert.assertEquals(3, storage.size());
        Assert.assertTrue(list.contains(UUID_1));
        Assert.assertTrue(list.contains(UUID_2));
        Assert.assertTrue(list.contains(UUID_3));
    }

    @Test
    public void save() {
        storage.save(new Resume("new"));
        ArrayList<String> list = new ArrayList<>();
        list.add("new");
        list.add(UUID_1);
        list.add(UUID_2);
        list.add(UUID_3);
        Assert.assertEquals(4, storage.size());
        Assert.assertTrue(list.contains("new"));
    }

    @Test
    public void delete() {
        storage.save(new Resume("new"));
        storage.delete("new");
        ArrayList<String> list = new ArrayList<>();
        list.add(UUID_1);
        list.add(UUID_2);
        list.add(UUID_3);
        Assert.assertEquals(3, storage.size());
        Assert.assertFalse(list.contains("new"));
    }

    @Test
    public void get() {
        Resume resume = storage.get("uuid1");
        Assert.assertEquals("uuid1", resume.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void getStorageOverflow() {
        int uuidValue = 0;
        try {
            for (int i = storage.size(); i < 10_000; i++) {
                storage.save(new Resume(Integer.toString(uuidValue)));
                uuidValue++;
            }
            uuidValue = 0;
        } catch (StorageException e) {
            Assert.fail("The storage is not overflowed yet");
        }

        for (int i = storage.size(); i <= 10_000; i++) {
            storage.save(new Resume(Integer.toString(uuidValue)));
            uuidValue++;
        }
    }

    @Test(expected = ExistStorageException.class)
    public void getExists() {
        storage.save(new Resume(UUID_1));
    }
}
