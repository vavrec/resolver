package com.example.demo.model;


import com.example.demo.resolver.CustomTypeResolver;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@JsonTypeResolver(CustomTypeResolver.class)
public class Response {

    private Header header;

}
