package br.com.washington.desafio_todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.washington.desafio_todolist.controllers.TaskController;
import br.com.washington.desafio_todolist.data.vo.TaskVO;
import br.com.washington.desafio_todolist.exceptions.MessagesException;
import br.com.washington.desafio_todolist.exceptions.RequiredObjectIsNullException;
import br.com.washington.desafio_todolist.exceptions.ResourceNotFoundException;
import br.com.washington.desafio_todolist.mapper.Mapper;
import br.com.washington.desafio_todolist.model.Task;
import br.com.washington.desafio_todolist.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    public TaskVO findById(Long id) {
        if(id == null) throw new RequiredObjectIsNullException();
        Task entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessagesException.NO_RECORDS_FOUND_FOR_THIS_ID));
        var vo = Mapper.parseObject(entity, TaskVO.class);
        vo.add(linkTo(methodOn(TaskController.class).findById(id)).withSelfRel()); 
        return vo;
    }

    public List<TaskVO> findAll(){
        var vo = Mapper.parseListObject(repository.findAll(), TaskVO.class);
        vo.forEach(b -> b.add(linkTo(methodOn(TaskController.class).findById(b.getKey())).withSelfRel()));
        return vo;
    }

    public TaskVO create(TaskVO data){
        if(data == null) throw new RequiredObjectIsNullException();
        
        var entity = Mapper.parseObject(data, Task.class);
        var vo = Mapper.parseObject(repository.save(entity), TaskVO.class);
        vo.add(linkTo(methodOn(TaskController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public TaskVO update(TaskVO data){
        if(data == null) throw new RequiredObjectIsNullException();

        var entity = repository.findById(data.getKey()).orElseThrow(() -> new ResourceNotFoundException(MessagesException.NO_RECORDS_FOUND_FOR_THIS_ID));

        entity.setDescription(data.getDescription());
        entity.setName(data.getName());
        entity.setPriority(data.getPriority());
        entity.setStatus(data.getStatus());

        var vo = Mapper.parseObject(repository.save(entity), TaskVO.class);
        vo.add(linkTo(methodOn(TaskController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id){
        if(id == null) throw new RequiredObjectIsNullException();
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessagesException.NO_RECORDS_FOUND_FOR_THIS_ID));
        repository.delete(entity);
    }
    
}
