package uz.pdp.task_2_9_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.task_2_9_1.entity.template.AbsUUIDEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Space extends AbsUUIDEntity {
    private String name;

    private String color;

    private String initialLetter;

    private String icon;

    private String accessType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment avatar;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<WorkspaceUser> owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Workspace workspace;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private List<ClickApps> clickApps;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private List<View> viewList;

    @PrePersist
    @PreUpdate
    public void setInitialLetterMyMethod() {
        this.initialLetter = name.substring(0, 1);
    }

    public Space(String name, String color, Attachment avatar, List<WorkspaceUser> owner, Workspace workspace,List<ClickApps> clickApps, List<View> viewList) {
        this.name = name;
        this.color = color;
        this.avatar = avatar;
        this.workspace = workspace;
        this.owner = owner;
        this.clickApps = clickApps;
        this.viewList = viewList;
    }
}
