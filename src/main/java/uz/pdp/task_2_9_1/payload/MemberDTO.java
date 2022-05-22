package uz.pdp.task_2_9_1.payload;

import lombok.Data;
import uz.pdp.task_2_9_1.entity.enums.AddType;

import java.util.UUID;

@Data
public class MemberDTO {
    private UUID id;

    private UUID roleId;

    private AddType addType;//ADD, EDIT, REMOVE
}
