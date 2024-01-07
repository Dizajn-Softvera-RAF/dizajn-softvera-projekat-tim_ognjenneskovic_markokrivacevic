package raf.dsw.classycraft.app.serializer;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.module.Configuration;

public class ConfigurationTypeAdapter extends TypeAdapter<Configuration> {


    @Override
    public void write(JsonWriter jsonWriter, Configuration configuration) throws IOException {

    }

    @Override
    public Configuration read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
