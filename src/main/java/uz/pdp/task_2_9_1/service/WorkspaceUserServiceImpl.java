package uz.pdp.task_2_9_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_9_1.entity.User;
import uz.pdp.task_2_9_1.entity.Workspace;
import uz.pdp.task_2_9_1.entity.WorkspaceUser;
import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.MemberDTO;
import uz.pdp.task_2_9_1.repository.WorkspaceRepository;
import uz.pdp.task_2_9_1.repository.WorkspaceUserRepository;

import java.util.List;

@Service
public class WorkspaceUserServiceImpl implements WorkspaceUserService{
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;

    /**
     * WORKSPACE OWNER EDIT QILISH
     *
     * @param workspaceId
     * @param userDTO
     * @param user
     * @return
     */
    @Override
    public ApiResponse editOwner(Long workspaceId, MemberDTO userDTO, User user) {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new ResourceNotFoundException("workspace"));
        if (user.getId() != workspace.getOwner().getId())
            return new ApiResponse("workspace not found",false);
        WorkspaceUser owner = workspaceUserRepository.findById(userDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Owner"));
        workspace.setOwner(owner.getUser());
        workspaceRepository.save(workspace);
        return new ApiResponse("Workspace Owner edited",true);
    }

    /**
     * WORKSPACE MEMBERLARINI OLISH
     *
     * @param workspaceId
     * @param user
     * @return
     */
    @Override
    public ApiResponse getMembers(Long workspaceId, User user) {
        List<WorkspaceUser> userList = workspaceUserRepository.findByWorkspaceId(workspaceId);
        return new ApiResponse("user list",true,userList);
    }
}
