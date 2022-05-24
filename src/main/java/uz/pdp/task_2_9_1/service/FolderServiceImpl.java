package uz.pdp.task_2_9_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_9_1.entity.Folder;
import uz.pdp.task_2_9_1.entity.FolderUser;
import uz.pdp.task_2_9_1.entity.Space;
import uz.pdp.task_2_9_1.entity.User;
import uz.pdp.task_2_9_1.entity.enums.TaskPermission;
import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.FolderDTO;
import uz.pdp.task_2_9_1.repository.FolderRepository;
import uz.pdp.task_2_9_1.repository.FolderUserRepository;
import uz.pdp.task_2_9_1.repository.SpaceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    FolderRepository folderRepository;
    @Autowired
    SpaceRepository spaceRepository;
    @Autowired
    FolderUserRepository folderUserRepository;

    /**
     * FOLDER QO'SHISH VA FOLDERUSERGA USER QO'SHISH
     * @param folderDTO
     * @param user
     * @return
     */
    @Override
    public ApiResponse addFolder(FolderDTO folderDTO, User user) {
        // FOLDER QO'SHISH
        boolean name = folderRepository.existsByNameAndSpaceId(folderDTO.getName(),folderDTO.getSpaceId());
        if(name){
            return new ApiResponse("folder already exists",false);
        }
        Optional<Space> optionalSpace = spaceRepository.findById(folderDTO.getSpaceId());
        if (!optionalSpace.isPresent()){
            return new ApiResponse("space not found",false);
        }
        Folder folder = new Folder(
                folderDTO.getName(),
                optionalSpace.get(),
                folderDTO.getAccessType(),
                folderDTO.isArchived(),
                folderDTO.getColor());
        folderRepository.save(folder);
        //FOLDERGA USER QO'SHISH

        FolderUser folderUser = new FolderUser(folder,user, TaskPermission.valueOf(""));
        folderUserRepository.save(folderUser);
        return new ApiResponse("folder saved",true);
    }

    /**
     * FOLDER EDITED
     * @param folderDTO
     * @param folderId
     * @return
     */
    @Override
    public ApiResponse editService(FolderDTO folderDTO, UUID folderId) {
        Folder folder = folderRepository.findById(folderId).orElseThrow(() -> new ResourceNotFoundException("folder"));
        folder.setName(folderDTO.getName());
        folder.setArchived(folderDTO.isArchived());
        folder.setColor(folderDTO.getColor());
        folderRepository.save(folder);
        return new ApiResponse("folder edited successfully",true);
    }

    /**
     * FOLDER DELETE QILISH
     * @param folderId
     * @return
     */
    @Override
    public ApiResponse deleteFolder(UUID folderId) {
        Optional<Folder> optionalFolder = folderRepository.findById(folderId);
        if (!optionalFolder.isPresent()){
            return new ApiResponse("Folder not found",false);
        }
        folderRepository.deleteById(folderId);
        return new ApiResponse("folder deleted successfully",true);
    }

    /**
     * GET ALL FOLDERS
     * @return
     */
    @Override
    public ApiResponse getFolderList() {
        List<Folder> all = folderRepository.findAll();
        return new ApiResponse("Folder List: ",true,all);
    }
}
