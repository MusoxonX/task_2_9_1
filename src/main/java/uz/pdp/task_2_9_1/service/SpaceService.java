package uz.pdp.task_2_9_1.service;

import uz.pdp.task_2_9_1.entity.User;
import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.SpaceDto;

import java.util.UUID;

public interface SpaceService {

    ApiResponse addSpace(SpaceDto spaceDto, User user);

    ApiResponse editSpace(UUID spaceId, SpaceDto spaceDto);

    ApiResponse deleteSpace(UUID spaceId);

    ApiResponse getSpaceList();

}
