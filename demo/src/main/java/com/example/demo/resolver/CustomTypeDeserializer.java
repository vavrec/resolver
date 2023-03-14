package com.example.demo.resolver;

import com.example.demo.model.CarResponse;
import com.example.demo.model.ColomboResponse;
import com.example.demo.model.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.AsPropertyTypeDeserializer;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;

public class CustomTypeDeserializer extends AsPropertyTypeDeserializer {


    public CustomTypeDeserializer(JavaType bt, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl) {
        super(bt, idRes, typePropertyName, typeIdVisible, defaultImpl);
    }

    public CustomTypeDeserializer(AsPropertyTypeDeserializer src, BeanProperty property) {
        super(src, property);
    }

    @Override
    public TypeDeserializer forProperty(final BeanProperty prop) {
        return (prop == _property) ? this : new CustomTypeDeserializer(this, prop);
    }

    @Override
    public Object deserializeTypedFromObject(JsonParser parser, DeserializationContext context) throws IOException {
//        parser.readValueAsTree().fieldNames().forEachRemaining(v -> System.out.println(v));

        JsonNode node = parser.readValueAsTree();
        String value = node.get("header").get("type").textValue();


        Class<?> recognizedType = switch (value) {
            case "car" -> CarResponse.class;
            case "colombo" -> ColomboResponse.class;
            case "simpleCar", "simpleColombo" -> Response.class;
            default -> throw new JsonParseException(parser, "Unknown response type : " + value);
        };

        JavaType type = TypeFactory.defaultInstance().constructType(recognizedType);

        JsonParser jsonParser = new TreeTraversingParser(node, parser.getCodec());
        if (jsonParser.getCurrentToken() == null) {
            jsonParser.nextToken();
        }

        JsonDeserializer<Object> deserializer = context.findContextualValueDeserializer(type, _property);
        return deserializer.deserialize(jsonParser, context);
    }
}
