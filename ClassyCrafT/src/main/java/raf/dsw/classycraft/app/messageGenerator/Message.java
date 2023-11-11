package raf.dsw.classycraft.app.messageGenerator;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Message {
    public Message( MessageType tip,String poruka, LocalDateTime vreme) {
        this.poruka = poruka;
        this.tip = tip;
        this.vreme = vreme;
    }

    private String poruka;
    private MessageType tip;
    private LocalDateTime vreme;

}
