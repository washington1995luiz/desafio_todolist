package br.com.washington.desafio_todolist.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.washington.desafio_todolist.data.vo.TaskVO;
import br.com.washington.desafio_todolist.mapper.Mapper;
import br.com.washington.desafio_todolist.model.Task;
import br.com.washington.desafio_todolist.unittests.mocks.TaskMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ModelMapperConverterTest {

    TaskMock inputObjects;

    @BeforeEach
    public void setUp(){
        inputObjects = new TaskMock();
    }

    @Test
    public void parseEntityToVOTest(){
        TaskVO taskVO = Mapper.parseObject(inputObjects.mockEntity(), TaskVO.class);
        assertEquals(Long.valueOf(0L), taskVO.getKey());
        assertEquals(TaskMock.SET_NAME + 0, taskVO.getName());
        assertEquals(TaskMock.SET_DESCRIPTION + 0, taskVO.getDescription());
        assertEquals(TaskMock.SET_STATUS_TRUE, taskVO.getStatus());// status always be true, because id 0.
        assertEquals(TaskMock.SET_PRIORITY_ONE, taskVO.getPriority());// priority always be 1, because id 0.
    }

    @Test
    public void parseListEntityToVOListTest(){
        List<TaskVO> taskVOList = Mapper.parseListObject(inputObjects.mockEntityList(), TaskVO.class);
        for(int i = 0; i < 20; i++){
            assertEquals(Long.valueOf(i), taskVOList.get(i).getKey());
            assertEquals(TaskMock.SET_NAME + i, taskVOList.get(i).getName());
            assertEquals(TaskMock.SET_DESCRIPTION + i, taskVOList.get(i).getDescription());
            assertEquals(i%2==0 ? TaskMock.SET_STATUS_TRUE : TaskMock.SET_STATUS_FALSE, taskVOList.get(i).getStatus());
            assertEquals(i%2==0 ? TaskMock.SET_PRIORITY_ONE : TaskMock.SET_PRIORITY_TWO, taskVOList.get(i).getPriority());
        }
    }


    @Test
    public void parseVOtoEntityTest(){
        Task task = Mapper.parseObject(inputObjects.mockEntity(), Task.class);
        assertEquals(Long.valueOf(0L), task.getId());
        assertEquals(TaskMock.SET_NAME + 0, task.getName());
        assertEquals(TaskMock.SET_DESCRIPTION + 0, task.getDescription());
        assertEquals(TaskMock.SET_STATUS_TRUE, task.getStatus());// status always be true, because id 0.
        assertEquals(TaskMock.SET_PRIORITY_ONE, task.getPriority());// priority always be 1, because id 0.
    }

    @Test
    public void parseListVOtoEntityList(){
        List<Task> taskList = Mapper.parseListObject(inputObjects.mockEntityList(), Task.class);
        for(int i = 0; i < 20; i++){
            assertEquals(Long.valueOf(i), taskList.get(i).getId());
            assertEquals(TaskMock.SET_NAME + i, taskList.get(i).getName());
            assertEquals(TaskMock.SET_DESCRIPTION + i, taskList.get(i).getDescription());
            assertEquals(i%2==0 ? TaskMock.SET_STATUS_TRUE : TaskMock.SET_STATUS_FALSE, taskList.get(i).getStatus());
            assertEquals(i%2==0 ? TaskMock.SET_PRIORITY_ONE : TaskMock.SET_PRIORITY_TWO, taskList.get(i).getPriority());
        }
    }
}
