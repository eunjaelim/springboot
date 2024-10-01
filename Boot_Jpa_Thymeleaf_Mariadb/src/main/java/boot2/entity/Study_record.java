package boot2.entity;


import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Study_record {
    private int key_id;

    private String study_day;

    private String contents;

   private LocalDateTime reg_day;

}

