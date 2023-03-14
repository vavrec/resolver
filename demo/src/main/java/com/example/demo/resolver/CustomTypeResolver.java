package com.example.demo.resolver;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;

import java.util.Collection;


public class CustomTypeResolver extends StdTypeResolverBuilder {
    @Override
    public TypeDeserializer buildTypeDeserializer(final DeserializationConfig config, final JavaType baseType, final Collection<NamedType> subtypes) {
        return new CustomTypeDeserializer(baseType, null, _typeProperty, _typeIdVisible, defineDefaultImpl(config, baseType));
    }
}