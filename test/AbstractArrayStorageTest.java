import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_NEW = "new";
    private static final String UUID_DUMMY = "dummy";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_NEW = new Resume(UUID_NEW);
    private static final Resume RESUME_DUMMY = new Resume(UUID_DUMMY);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
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
        Resume [] resumes = storage.getAll();
        Assert.assertEquals(3, resumes.length);
        Assert.assertEquals(resumes[0], RESUME_1);
        Assert.assertEquals(resumes[1], RESUME_2);
        Assert.assertEquals(resumes[2], RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_NEW);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(storage.get(UUID_NEW), RESUME_NEW);
    }

    @Test
    public void delete() {
        storage.save(RESUME_NEW);
        storage.delete(UUID_NEW);
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void update() {
        Resume updatedResume = new Resume(UUID_3);
        storage.update(RESUME_3);
        Assert.assertEquals(updatedResume, storage.get(UUID_3));
    }

    @Test
    public void get() {
        Resume resume = storage.get(UUID_1);
        Assert.assertEquals(RESUME_1, resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_DUMMY);
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
    public void saveExists() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_DUMMY);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_DUMMY);
    }
}
