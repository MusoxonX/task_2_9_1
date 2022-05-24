package uz.pdp.task_2_9_1.service;

import uz.pdp.task_2_9_1.entity.User;
import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.FolderDTO;

import java.util.UUID;

public interface FolderService {

    ApiResponse editService(FolderDTO folderDTO, UUID folderId);

    ApiResponse addFolder(FolderDTO folderDTO, User user);

    ApiResponse deleteFolder(UUID folderId);

    ApiResponse getFolderList();
}
