package raf.dsw.classycraft.app.serializer;

import com.google.gson.*;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;

import java.lang.reflect.Type;

public class ProjectDeserializer implements JsonDeserializer<ClassyNode>, JsonSerializer<ClassyNode> {


    @Override
    public ClassyNode deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject member = (JsonObject) jsonElement;
        final JsonElement typeS = get(member, "type");
        final JsonElement data = get(member, "data");
        final Type realType = typeName(typeS);

        return jsonDeserializationContext.deserialize(data, realType);    }

    @Override
    public JsonElement serialize(ClassyNode classyNode, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonObject member = new JsonObject();
        member.addProperty("type",classyNode.getName());
        try{
            member.add("data", jsonSerializationContext.serialize(classyNode));
        } catch(JsonIOException e){
            System.out.println("ERROR " + classyNode);
            throw e;
        }
        return member;    }

    private Type typeName (final JsonElement typeElement){
        try {
            return Class.forName(typeElement.getAsString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonElement get (final JsonObject wrapper, final String memeberName){
        final JsonElement elem = wrapper.get(memeberName);
        return elem;
    }

}
