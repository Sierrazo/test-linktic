package com.linktic.testlinktic.controllers;

import com.linktic.testlinktic.dtos.TaskDto;
import com.linktic.testlinktic.dtos.ResponseBodyGeneric;
import com.linktic.testlinktic.interfaces.ITaskService;
import com.linktic.testlinktic.models.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {
    private final ITaskService iTaskService;

    @Operation(summary = "Metodo que permite guardar un registro en TASK")
    @ApiResponse(responseCode = "201", description = "CREATED")
    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyGeneric> createTask(@RequestBody TaskDto taskDto) {
        try {
            iTaskService.save(taskDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ResponseBodyGeneric(HttpStatus.CREATED.toString()), null, HttpStatus.CREATED);
    }

    @Operation(summary = "Metodo que permite obtener todos los registros")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks;
        try {
            tasks = iTaskService.findAll();
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(tasks, null, HttpStatus.OK);
    }

    @Operation(summary = "Metodo que permite obtener un registro por id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: id inválido")
    @ApiResponse(responseCode = "404", description = "NOT_FOUND: id no encontrado")
    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") String id) {
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }

        TaskDto task;
        try {
            task = iTaskService.findById(idLong);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(task, null, HttpStatus.OK);
    }

    @Operation(summary = "Metodo que permite actualizar un registro por id")
    @ApiResponse(responseCode = "202", description = "ACCEPTED")
    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: id inválido")
    @ApiResponse(responseCode = "404", description = "NOT_FOUND: id no encontrado")
    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyGeneric> updateTask(@PathVariable("id") String id,
                                                          @RequestBody TaskDto taskDto) {
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }

        try {
            iTaskService.update(idLong, taskDto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ResponseBodyGeneric(HttpStatus.ACCEPTED.toString()), null, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Metodo que permite borrar un registro por id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: id inválido")
    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseBodyGeneric> deleteTask(@PathVariable("id") String id) {
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }

        try {
            iTaskService.deleteById(idLong);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ResponseBodyGeneric(HttpStatus.OK.toString()), null, HttpStatus.OK);
    }
}
