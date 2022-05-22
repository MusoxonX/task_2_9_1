package uz.pdp.task_2_9_1.service;

import uz.pdp.task_2_9_1.entity.User;
import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.MemberDTO;

public interface WorkspaceUserService {
    ApiResponse editOwner(Long workspaceId, MemberDTO userDTO, User user);

    ApiResponse getMembers(Long workspaceId, User user);
}
