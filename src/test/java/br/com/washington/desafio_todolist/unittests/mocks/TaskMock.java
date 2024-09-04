package br.com.washington.desafio_todolist.unittests.mocks;

import br.com.washington.desafio_todolist.data.vo.TaskVO;
import br.com.washington.desafio_todolist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskMock {

    public static final String SET_NAME = "Name task";
    public static final String SET_DESCRIPTION = "Name task";
    public static final int SET_PRIORITY_ONE = 1;
    public static final int SET_PRIORITY_TWO = 2;
    public static final boolean SET_STATUS_TRUE = true;
    public static final boolean SET_STATUS_FALSE = false;

    public Task mockEntity(){
        return mockEntity(0);
    }

    public TaskVO mockVO(){
        return mockVO(0);

    }

    public List<Task> mockEntityList(){
        List<Task> tasks = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            tasks.add(mockEntity(i));
        }
        return tasks;
    }

    public List<TaskVO> mockVOList(){
        List<TaskVO> tasks = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            tasks.add(mockVO(i));
        }
        return tasks;
    }

    public Task mockEntity(Integer number){
        Task task = new Task();
        task.setId(number.longValue());
        task.setName(SET_NAME + number);
        task.setDescription(SET_DESCRIPTION + number);
        task.setPriority(number%2 == 0 ? SET_PRIORITY_ONE : SET_PRIORITY_TWO);
        task.setStatus(number%2 == 0 ? SET_STATUS_TRUE : SET_STATUS_FALSE);
        return task;
    }

    public TaskVO mockVO(Integer number){
        TaskVO task = new TaskVO();
        task.setKey(number.longValue());
        task.setName(SET_NAME + number);
        task.setDescription(SET_DESCRIPTION + number);
        task.setPriority(number%2 == 0 ? SET_PRIORITY_ONE : SET_PRIORITY_TWO);
        task.setStatus(number%2 == 0 ? SET_STATUS_TRUE : SET_STATUS_FALSE);
        return task;
    }
}
