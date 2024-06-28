package com.linktic.testlinktic.services;

import com.linktic.testlinktic.dtos.TaskDto;
import com.linktic.testlinktic.interfaces.ITaskService;
import com.linktic.testlinktic.models.Task;
import com.linktic.testlinktic.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void save(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setStatus(taskDto.getStatus());
        taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public TaskDto findById(long id) {
        TaskDto taskDto = new TaskDto();
        Task task = taskRepository.getReferenceById(id);

        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDueDate(task.getDueDate());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }

    @Override
    public void update(long id, TaskDto taskDto) {
        Task task = taskRepository.getReferenceById(id);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setStatus(taskDto.getStatus());
        taskRepository.save(task);
    }

    @Override
    public void deleteById(long id) {
        taskRepository.deleteById(id);
    }
}
