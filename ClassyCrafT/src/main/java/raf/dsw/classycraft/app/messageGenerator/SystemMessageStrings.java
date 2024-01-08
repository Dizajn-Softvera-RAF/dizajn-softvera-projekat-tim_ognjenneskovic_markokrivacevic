package raf.dsw.classycraft.app.messageGenerator;

import java.util.EnumMap;
import java.util.Map;

public class SystemMessageStrings
{
    public static final EnumMap<SystemMessageType, String> msgMap = new EnumMap<>(Map.of(
            SystemMessageType.NODE_CANNOT_BE_DELETED, "ProjectExplorer ne može biti obrisan",
            SystemMessageType.NAME_CANNOT_BE_EMPTY, "Ime ne može biti prazno",
            SystemMessageType.NODE_CANNOT_HAVE_CHILDREN, "Diagram ne može imati decu",
            SystemMessageType.NODE_CANNOT_HAVE_PACKAGE, "Nije moguće dodati paket u ovaj čvor",
            SystemMessageType.CREATE_CHILD_ON_DIAGRAM, "Koristite alatke sa desne strane za dodavanje elemenata na dijagram",
            SystemMessageType.DELETE_CHILD_ON_DIAGRAM_ELEMENT, "Koristite alatke sa desne strane za brisanje elemenata sa dijagrama",
            SystemMessageType.INVALID_JSON, "Neispravan JSON fajl"
    ));
}
