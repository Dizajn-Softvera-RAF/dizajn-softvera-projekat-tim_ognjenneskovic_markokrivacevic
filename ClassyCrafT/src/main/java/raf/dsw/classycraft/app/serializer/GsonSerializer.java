package raf.dsw.classycraft.app.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GsonSerializer implements Serializer{

    private final Gson gson = new GsonBuilder().registerTypeAdapter(ClassyNode.class, new ProjectDeserializer()).create();

    @Override
    public Project loadProject(File file) {
        return null;
    }

    @Override
    public void saveProject(Project project) {
        try (FileWriter writer = new FileWriter(project.getFilePath())) {
            gson.toJson(project, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadTemplate(File file) {

    }

    @Override
    public void saveTemplate(Diagram diagram, String templateName) {

    }
}
