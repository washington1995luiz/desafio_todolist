package br.com.washington.desafio_todolist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.washington.desafio_todolist.data.vo.TaskVO;
import br.com.washington.desafio_todolist.services.TaskService;
import br.com.washington.desafio_todolist.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tarefas", description = "Rotas para TODO List")
public class TaskController {

    @Autowired
    TaskService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Procura uma tarefa por id", description = "Procura uma tarefa por id", tags = "Tarefas",
            responses = {
                @ApiResponse(description = "success", responseCode = "200", content = {
            @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    array = @ArraySchema(schema = @Schema(implementation = TaskVO.class))
            )}),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public TaskVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista todas tarefas em ordem",
            description = "Ordenado por:\n1) Realizado: falso \n2) Prioridade: ordem ascendente de 1 a 5\n3) Maior prioridade: 1 \n4) Menor prioridade: 5",
            tags = "Tarefas",
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TaskVO.class))),
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public List<TaskVO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Criar uma nova tarefa", description = "Criar uma nova tarefa", tags = "Tarefas",
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TaskVO.class))),
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public TaskVO create(@RequestBody TaskVO vo) {
        return service.create(vo);
    }
    @Operation(summary = "Atualizar uma tarefa já existente", description = "Atualizar uma tarefa já existente", tags = "Tarefas",
    responses = {
        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TaskVO.class))),
        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    }
)
    @PutMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public TaskVO update(@RequestBody TaskVO vo) {
        return service.update(vo);
    }
    @Operation(summary = "Excluir uma tarefa", description = "Criando uma nova tarefa", tags = "Tarefas",
            responses = {
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
