package raf.dsw.classycraft.app.serializer;

import com.google.gson.*;
import javafx.util.Pair;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.composite.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.*;
import raf.dsw.classycraft.app.classyRepository.implementation.Package;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.*;
import raf.dsw.classycraft.app.classyRepository.implementation.diagramElements.Enum;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ProjectSerializer implements JsonDeserializer<ClassyNode>, JsonSerializer<ClassyNode> {
    private Interclass deserializeInterclass(final JsonObject jsonObject, ClassyNode parent)
    {
        var nodeClass = typeName(jsonObject.get("nodeType"));
        var name = get(jsonObject,"name").getAsString();
        var strokeWidth = jsonObject.get("strokeWidth").getAsInt();
        var x = jsonObject.get("x").getAsInt();
        var y = jsonObject.get("y").getAsInt();
        var contentArray = jsonObject.get("classContent").getAsJsonArray();

        Interclass parsedClass = null;
        if(nodeClass == Klasa.class)
        {
            var color = new Color(42,157,143);
            parsedClass = new Klasa(name, parent, color, strokeWidth, x, y);
        }
        else if(nodeClass == Interfejs.class)
        {
            var color = new Color(233, 196, 106);
            parsedClass = new Interfejs(name, parent, color, strokeWidth, x, y);
        }
        else if(nodeClass == Enum.class)
        {
            var color = new Color(233, 111, 81);
            parsedClass = new Enum(name, parent, color, strokeWidth, x, y);
        }
        if(parsedClass != null)
        {
            for(var contentObject: contentArray)
            {
                var contentName = get(contentObject.getAsJsonObject(),"name").getAsString();
                var contentType = typeName(contentObject.getAsJsonObject().get("contentType"));
                if(contentType == Atribut.class)
                    parsedClass.addContent(new Atribut(contentName));

                else if(contentType == EnumElement.class)
                    parsedClass.addContent(new EnumElement(contentName));

                else if(contentType == Metod.class)
                    parsedClass.addContent(new Metod(contentName));
            }
        }
        return parsedClass;
    }
    private Connection deserializeConnection(final JsonObject jsonObject, final HashMap<Integer, ClassyNode> nodeIdMap,
                                             ClassyNode parent)
    {
        var nodeClass = typeName(jsonObject.get("nodeType"));
        var nodeFromId = jsonObject.get("nodeFromId").getAsInt();
        var nodeToId = jsonObject.get("nodeToId").getAsInt();
        var nodeFrom = (Interclass) nodeIdMap.get(nodeFromId);
        var nodeTo = (Interclass) nodeIdMap.get(nodeToId);
        int endX = jsonObject.get("endX").getAsInt();
        int endY = jsonObject.get("endY").getAsInt();
        int startX = jsonObject.get("x").getAsInt();
        int startY = jsonObject.get("y").getAsInt();

        Connection connection = null;
        if (nodeClass == Agregacija.class) {
            connection =  new Agregacija(parent, Color.BLACK, 5, nodeFrom, nodeTo, startX, startY, endX,endY);

        } else if (nodeClass == Kompozicija.class) {
            connection = new Kompozicija (parent, Color.BLACK, 5, nodeFrom, nodeTo, startX, startY, endX,endY);

        } else if (nodeClass == Generalizacija.class) {
            connection =  new Generalizacija (parent, Color.BLACK, 5, nodeFrom, nodeTo, startX, startY, endX,endY);

        } else if (nodeClass == Zavisnost.class) {
            connection = new Zavisnost (parent, Color.BLACK, 5, nodeFrom, nodeTo, startX, startY, endX,endY);
        }
        return connection;
    }
    private ClassyNode deserializeNode(final JsonObject jsonObject, final HashMap<Integer, ClassyNode> nodeIdMap,
                                       ClassyNode parent)
    {
        var nodeClass = typeName(jsonObject.get("nodeType"));
        var name = get(jsonObject,"name").getAsString();
        if(nodeClass == Project.class)
        {
            var filePath = get(jsonObject,"filePath").getAsString();
            var author = get(jsonObject,"author").getAsString();
            return new Project(name,parent,filePath,author);
        }
        else if(nodeClass == Package.class)
        {
            return new Package(name, parent);
        }
        else if(nodeClass == Diagram.class)
        {
            return new Diagram(name, parent);
        }
        else if(Connection.class.isAssignableFrom((Class<?>) nodeClass))
            return deserializeConnection(jsonObject, nodeIdMap, parent);
        else if(Interclass.class.isAssignableFrom((Class<?>) nodeClass))
            return deserializeInterclass(jsonObject, parent);
        return null;
    }
    @Override
    public ClassyNode deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
    {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();
        ClassyNode node = null;
        if(type == Project.class)
        {
            // DFS to deserialize
            var dfsStack = new Stack<Pair<ClassyTreeItem,JsonObject>>();
            var root = ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).getRoot();
            dfsStack.push(new Pair<>(root,jsonObject));
            var nodeIdMap = new HashMap<Integer, ClassyNode>();
            while(!dfsStack.isEmpty())
            {
                var current = dfsStack.pop();
                var currJson = current.getValue();
                var parentTreeItem = current.getKey();
                var parent = (ClassyNodeComposite)parentTreeItem.getClassyNode();
                var currentNode = deserializeNode(currJson, nodeIdMap, parent);
                var newTreeItem = new ClassyTreeItem(currentNode);
                parentTreeItem.add(newTreeItem);
                parent.addChild(currentNode);

                var id = currJson.get("id").getAsInt();
                nodeIdMap.put(id, currentNode);
                if(node == null)
                    node = currentNode;
                if(currJson.has("children"))
                {
                    var children = currJson.get("children").getAsJsonArray();
                    for(var child: children)
                        dfsStack.push(new Pair<>(newTreeItem,child.getAsJsonObject()));
                }
            }
        }
        return node;
    }

    @Override
    public JsonElement serialize(ClassyNode classyNode, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("nodeType",classyNode.getClass().getName());
        jsonObject.addProperty("id",classyNode.hashCode());
        jsonObject.addProperty("parentId",classyNode.getParent().hashCode());
        jsonObject.addProperty("name",classyNode.getName());
        if(classyNode instanceof Project)
        {
            var project = (Project) classyNode;
            jsonObject.addProperty("filePath",project.getFilePath());
            jsonObject.addProperty("author",project.getAuthor());
        }
        if(classyNode instanceof ClassyNodeComposite)
        {
            var childrenArray = new JsonArray();
            // If node is a diagram order children: connections first then interclasses
            var children = ((ClassyNodeComposite) classyNode).getChildren();
            if(classyNode instanceof Diagram)
            {
                // Sort children
                var interclassChildren = children.stream().filter(x -> x instanceof Interclass).toArray();
                var connectionChildren = children.stream().filter(x -> x instanceof Connection).toArray();
                children = new ArrayList<>();
                for(var child: connectionChildren)
                    children.add((ClassyNode) child);
                for(var child: interclassChildren)
                    children.add((ClassyNode) child);
            }
            for(var child: children)
                childrenArray.add(serialize(child,child.getClass(),jsonSerializationContext));
            jsonObject.add("children",childrenArray);
        }
        else if(classyNode instanceof DiagramElement)
        {
            var diagramElement = (DiagramElement) classyNode;
            jsonObject.addProperty("strokeWidth",diagramElement.getStrokeWidth());
            jsonObject.addProperty("x", diagramElement.getX());
            jsonObject.addProperty("y", diagramElement.getY());
            if (classyNode instanceof Connection)
            {
                var connection = (Connection) classyNode;
                jsonObject.addProperty("nodeFromId",connection.getNodeFrom().hashCode());
                jsonObject.addProperty("nodeToId",connection.getNodeTo().hashCode());
                jsonObject.addProperty("endX",connection.getEndX());
                jsonObject.addProperty("endY",connection.getEndY());
            }
            if(classyNode instanceof Interclass)
            {
                var interclass = (Interclass) classyNode;
                var contentArray = new JsonArray();
                for(var content: interclass.getContent())
                {
                    var contentObject = new JsonObject();
                    contentObject.addProperty("name",content.getName());
                    contentObject.addProperty("contentType",content.getClass().getName());
                    contentArray.add(contentObject);
                }
                jsonObject.add("classContent",contentArray);
            }
        }
        return jsonObject;
    }

    private Type typeName (final JsonElement typeElement){
        try {
            return Class.forName(typeElement.getAsString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonElement get (final JsonObject wrapper, final String memeberName){
        return wrapper.get(memeberName);
    }

}