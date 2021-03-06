package uz.pdp.task_2_9_1.payload;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SpaceDto {
    private String name;

    private String color;

    private UUID avatarId;

    private Long workspaceId;

    private List<UUID> clickAppIdList;

    private List<UUID> viewList;
}
