package br.com.washington.desafio_todolist.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.washington.desafio_todolist.data.vo.TaskVO;
import br.com.washington.desafio_todolist.model.Task;

public class Mapper {
    private static final ModelMapper mapper = new ModelMapper();

    static{
        mapper
            .createTypeMap(Task.class, TaskVO.class)
            .addMapping(Task::getId, TaskVO::setKey);
        mapper
            .createTypeMap(TaskVO.class, Task.class)
            .addMapping(TaskVO::getKey, Task::setId);
        
    }

    public static <O, D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }
    public static <O, D> List<D> parseListObject(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<D>();
        for(O o : origin){
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
}
