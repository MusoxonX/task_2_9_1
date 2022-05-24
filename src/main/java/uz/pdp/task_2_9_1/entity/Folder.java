package uz.pdp.task_2_9_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.task_2_9_1.entity.template.AbsUUIDEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Folder extends AbsUUIDEntity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Space space;
    private String accessType;
    private boolean archived;
    private String color;

    public Folder(String name, boolean archived, String color) {
        this.name = name;
        this.archived = archived;
        this.color = color;
    }
}
