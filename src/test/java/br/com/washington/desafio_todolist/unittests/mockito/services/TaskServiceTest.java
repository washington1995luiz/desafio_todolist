package br.com.washington.desafio_todolist.unittests.mockito.services;

import br.com.washington.desafio_todolist.data.vo.TaskVO;
import br.com.washington.desafio_todolist.exceptions.MessagesException;
import br.com.washington.desafio_todolist.exceptions.RequiredObjectIsNullException;
import br.com.washington.desafio_todolist.model.Task;
import br.com.washington.desafio_todolist.repository.TaskRepository;
import br.com.washington.desafio_todolist.services.TaskService;
import br.com.washington.desafio_todolist.unittests.mocks.TaskMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    TaskMock input;

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository repository;

    @BeforeEach
    void setUp() {
        input = new TaskMock();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Task task = input.mockEntity(1);
        task.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/tasks/1>;rel=\"self\"]"));
        assertEquals(TaskMock.SET_NAME + 1, result.getName());
        assertEquals(TaskMock.SET_DESCRIPTION + 1, result.getDescription());
        assertEquals(TaskMock.SET_STATUS_FALSE, result.getStatus());
        assertEquals(TaskMock.SET_PRIORITY_TWO, result.getPriority());
    }

    @Test
    void findAll() {
        List<Task> list = input.mockEntityList();
        Sort sort = Sort.by(Direction.ASC, "status").and(Sort.by(Direction.ASC, "priority"));
        when(repository.findAll(sort)).thenReturn(list);
        var task = service.findAll();

        assertEquals(20, task.size());
        for(int i = 0; i < task.size(); i++){
            assertNotNull(task.get(i));
            assertNotNull(task.get(i).getKey());
            assertNotNull(task.get(i).getLinks());
            assertTrue(task.get(i).toString().contains("[</api/tasks/"+i+">;rel=\"self\"]"));
            assertEquals(TaskMock.SET_NAME + i, task.get(i).getName());
            assertEquals(TaskMock.SET_DESCRIPTION + i, task.get(i).getDescription());
            assertEquals(i%2==0 ? TaskMock.SET_STATUS_TRUE : TaskMock.SET_STATUS_FALSE, task.get(i).getStatus());
            assertEquals(i%2==0 ? TaskMock.SET_PRIORITY_ONE : TaskMock.SET_PRIORITY_TWO, task.get(i).getPriority());
        }
    }

    @Test
    void create() {
        Task task = input.mockEntity(1);
        task.setId(1L);
        TaskVO taskVO = input.mockVO(1);
        taskVO.setKey(1L);
        when(repository.save(task)).thenReturn(task);

        var result = service.create(taskVO);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/tasks/1>;rel=\"self\"]"));
        assertEquals(TaskMock.SET_NAME + 1, result.getName());
        assertEquals(TaskMock.SET_DESCRIPTION + 1, result.getDescription());
        assertEquals(TaskMock.SET_STATUS_FALSE, result.getStatus());
        assertEquals(TaskMock.SET_PRIORITY_TWO, result.getPriority());

    }

    @Test
    void createWithNullTask() {
      Exception exception =  assertThrows(RequiredObjectIsNullException.class, () -> service.create(null));
      assertTrue(exception.getMessage().contains(MessagesException.NOT_ALLOWED_TO_PERSIST_A_NULL_OBJECT));
    }

    @Test
    void update() {
        Task task = input.mockEntity(1);
        task.setId(1L);

        TaskVO taskVO = input.mockVO(1);
        taskVO.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(task));
        when(repository.save(task)).thenReturn(task);

        var result = service.update(taskVO);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/tasks/1>;rel=\"self\"]"));
        assertEquals(TaskMock.SET_NAME + 1, result.getName());
        assertEquals(TaskMock.SET_DESCRIPTION + 1, result.getDescription());
        assertEquals(TaskMock.SET_STATUS_FALSE, result.getStatus());
        assertEquals(TaskMock.SET_PRIORITY_TWO, result.getPriority());
    }


    @Test
    void updateWithNullTask() {
        Exception exception =  assertThrows(RequiredObjectIsNullException.class, () -> service.update(null));
        assertTrue(exception.getMessage().contains(MessagesException.NOT_ALLOWED_TO_PERSIST_A_NULL_OBJECT));
    }

    @Test
    void delete() {
        Task task = input.mockEntity();
        task.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(task));
        service.delete(1L);

    }
}