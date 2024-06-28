package com.linktic.testlinktic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto {
    @JsonProperty("TITLE")
    private String title;

    @JsonProperty("DESCRIPTION")
    private String description;

    @JsonProperty("DUE_DATE")
    private String dueDate;

    @JsonProperty("STATUS")
    private String status;
}
