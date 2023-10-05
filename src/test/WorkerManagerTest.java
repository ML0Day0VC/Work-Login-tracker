import main.API.WorkerManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerManagerTest {

    private List<List<String>> worker;

    @BeforeEach
    public void setUp() {
        worker = new ArrayList<>();
    }

    @Test
    public void testSaveWorker() {
        WorkerManager.saveWorker("1", "John Doe", "30", "john@example.com", "Engineering");
        assertEquals(1, worker.size());
        //save random data
        List<String> savedWorker = worker.get(0);
        assertEquals("1", savedWorker.get(0));
        assertEquals("John Doe", savedWorker.get(1));
        assertEquals("30", savedWorker.get(2));
        assertEquals("john@example.com", savedWorker.get(3));
        assertEquals("Engineering", savedWorker.get(4));
    }

    @Test
    public void testSearchWorker() {
        // Add some workers to the list
        worker.add(Arrays.asList("1", "John Doe", "30", "john@example.com", "Engineering"));
        worker.add(Arrays.asList("2", "Jane Smith", "25", "jane@example.com", "Marketing"));

        // search by ID
        List<String> result = WorkerManager.searchWorker("1");
        assertEquals(5, result.size());
        assertEquals("1", result.get(0));
        assertEquals("John Doe", result.get(1));
        assertEquals("30", result.get(2));
        assertEquals("john@example.com", result.get(3));
        assertEquals("Engineering", result.get(4));

        //testing lists in this Junit version is much better but is outdated
        List<String> notFoundResult = WorkerManager.searchWorker("3");
        assertTrue(notFoundResult.isEmpty());
    }

    @Test
    public void testDeleteWorker() {
        //random test workers
        worker.add(Arrays.asList("1", "John Doe", "30", "john@example.com", "Engineering"));
        worker.add(Arrays.asList("2", "Jane Smith", "25", "jane@example.com", "Marketing"));

        // Delete a worker by ID
        WorkerManager.deleteWorker(Arrays.asList("1", "John Doe", "30", "john@example.com", "Engineering"));
        assertEquals(1, worker.size());

        // Verify that the worker has been deleted
        List<String> deletedWorker = worker.get(0);
        assertEquals("2", deletedWorker.get(0));
        assertEquals("Jane Smith", deletedWorker.get(1));
    }


}
