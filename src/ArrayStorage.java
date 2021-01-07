import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int resumeCount = size();
        for (int i = 0; i <= resumeCount; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        int resumeCount = size();
        storage[resumeCount] = r;
    }

    Resume get(String uuid) {
        int count = size();
        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int count = size();
        int index = 0;

        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                index = i;
            }
        }

        if (storage.length - 1 - index >= 0)
            System.arraycopy(storage, index + 1, storage, index, storage.length - 1 - index);
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    int size() {
        return getAll().length;
    }
}
