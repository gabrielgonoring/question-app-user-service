package com.gabriel.gonoring.question.app.user.service;

import com.gabriel.gonoring.question.app.user.dto.NewUserDTO;
import com.gabriel.gonoring.question.app.user.dto.UpdatedUserDTO;
import com.gabriel.gonoring.question.app.user.dto.UsersSearchFiltersDTO;
import com.gabriel.gonoring.question.app.user.dto.UserDTO;
import com.gabriel.gonoring.question.app.user.po.UserPO;
import com.gabriel.gonoring.question.app.user.po.enumerator.UserPOStatus;
import com.gabriel.gonoring.question.app.user.repository.UserPORepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private ModelMapper modelMapper;
    private UserPORepository userPORepository;

    @Autowired
    public UserService(ModelMapper modelMapper, UserPORepository userPORepository) {
        this.modelMapper = modelMapper;
        this.userPORepository = userPORepository;
    }

    @Transactional
    public void saveUser(NewUserDTO newUserDTO){

        UserPO userPO = modelMapper.map(newUserDTO, UserPO.class);
        userPO.setCreationDate(LocalDateTime.now());
        userPO.setStatus(UserPOStatus.ACTIVATED);

        userPORepository.save(userPO);
    }

    @Transactional
    public void updateUser(UpdatedUserDTO userDTO){
        UserPO userPO = userPORepository.getById(userDTO.getId());

        userPO.setName(userDTO.getName());


        userPORepository.save(userPO);
    }


    @Transactional
    public void deleteUserById(UUID id){
        UserPO userPO = userPORepository.getById(id);
        userPO.setStatus(UserPOStatus.DELETED);
    }

    public UserDTO getUserById(UUID id){
        return convertToUserDTO(userPORepository.getById(id));
    }

    @Autowired
    private RestTemplate restTemplate;

    public Page<UserDTO> getUsers(UsersSearchFiltersDTO filters, Pageable pageable){

        System.out.println(restTemplate.getForEntity("http://question-service/question-decks", String.class));

        return userPORepository
                .findAll(pageable)
                .map(this::convertToUserDTO);
    }


    private UserDTO convertToUserDTO(UserPO userPO){
        return modelMapper.map(userPO, UserDTO.class);
    }

}
