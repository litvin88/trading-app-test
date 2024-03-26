package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record Security(UUID id, String name){}
