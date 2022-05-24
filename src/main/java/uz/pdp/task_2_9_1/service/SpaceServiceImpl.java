package uz.pdp.task_2_9_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_9_1.entity.Space;
import uz.pdp.task_2_9_1.entity.SpaceUser;
import uz.pdp.task_2_9_1.entity.User;
import uz.pdp.task_2_9_1.entity.Workspace;
import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.SpaceDto;
import uz.pdp.task_2_9_1.repository.AttachmentRepository;
import uz.pdp.task_2_9_1.repository.SpaceRepository;
import uz.pdp.task_2_9_1.repository.SpaceUserRepository;
import uz.pdp.task_2_9_1.repository.WorkspaceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpaceServiceImpl implements SpaceService{

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    SpaceUserRepository spaceUserRepository;

    /**
     * SPACE QO'SHISH VA SPACEGA USER QO'SHISH
     * @param spaceDto
     * @param user
     * @return
     */
    @Override
    public ApiResponse addSpace(SpaceDto spaceDto, User user) {
        // SPACE QO'SHILDI
        Optional<Space> space1 = spaceRepository.findByOwnerIdAndName(user.getId(), spaceDto.getName());
        if (space1.isPresent()){
            return new ApiResponse("like this space already exists",false);
        }
        Workspace workspace = workspaceRepository.findById(spaceDto.getWorkspaceId()).orElseThrow(() -> new ResourceNotFoundException("workspace"));
        Space space = new Space(
                spaceDto.getName(),
                spaceDto.getColor(),
                spaceDto.getAvatarId() == null ? null:attachmentRepository.findById(spaceDto.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("avatar")),
                user,
                workspace);
        spaceRepository.save(space);

        // SPACEUSERGA SPACE OCHGANNI QO'SHILDI
        SpaceUser spaceUser =new SpaceUser(space,user);
        spaceUserRepository.save(spaceUser);
        return new ApiResponse("Space added",true);
    }

    /**
     * SPACE EDIT QILISH
     * @param spaceId
     * @param spaceDto
     * @return
     */
    @Override
    public ApiResponse editSpace(UUID spaceId, SpaceDto spaceDto) {
        Space space = spaceRepository.findById(spaceId).orElseThrow(() -> new ResourceNotFoundException("space"));
        space.setName(spaceDto.getName());
        space.setColor(spaceDto.getColor());
        space.setAvatar(spaceDto.getAvatarId() == null ? null:attachmentRepository.findById(spaceDto.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("avatar")));
        spaceRepository.save(space);
        return new ApiResponse("Space edited",true);
    }

    /**
     * SPACE DELETE QILISH
     * @param spaceId
     * @return
     */
    @Override
    public ApiResponse deleteSpace(UUID spaceId) {
        Optional<Space> optionalSpace = spaceRepository.findById(spaceId);
        if (!optionalSpace.isPresent())
            return new ApiResponse("space not found",false);
        spaceRepository.deleteById(spaceId);
        return new ApiResponse("space deleted",true);
    }

    @Override
    public ApiResponse getSpaceList() {
        List<Space> all = spaceRepository.findAll();
        return new ApiResponse("Spaces List: " ,true,all);
    }
}
