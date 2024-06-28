package com.linktic.testlinktic.interfaces;

import com.linktic.testlinktic.dtos.TaskDto;
import com.linktic.testlinktic.models.Task;

import java.util.List;

public interface ITaskService {
    void save(TaskDto taskDto);

    List<Task> findAll();

    TaskDto findById(long id);

    void update(long id, TaskDto taskDto);

    void deleteById(long id);
}
