import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume.uuid.equals(uuid)) {
                return resume;
            } else {
                System.out.print("Resume is not found\n");
                return null;
            }
        }
        return null;
    }

    void delete(String uuid) {

        Resume[] copy = new Resume[storage.length];

        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                if (storage.length - 1 - i >= 0) System.arraycopy(storage, i + 1, copy, i, storage.length - 1 - i);
                break;
            }
        }

        storage = copy;
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
