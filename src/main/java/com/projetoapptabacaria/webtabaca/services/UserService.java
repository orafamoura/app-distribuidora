package com.projetoapptabacaria.webtabaca.services;

import com.projetoapptabacaria.webtabaca.mapper.UserMapper;
import com.projetoapptabacaria.webtabaca.model.User;
import com.projetoapptabacaria.webtabaca.model.exception.ResourceNotFoundException;
import com.projetoapptabacaria.webtabaca.repositories.UserRepository;
import com.projetoapptabacaria.webtabaca.shared.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    /**
     * Metodo para retornar lista de usuario.
     *
     * @return Lista de usuario.
     */
    public List<UserDTO> findAll() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> userMapper.userToUserDto(user))
                .collect(Collectors.toList());
    }

    /**
     * Metodo para retornar o usuario com seu id.
     *
     * @param id do usuario que sera localizado.
     * @return usuario caso seja encontrado.
     */
    public Optional<UserDTO> findById(Long id){
        Optional<User> users = userRepository.findById(id);
        //exception
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("User not found!");
        }
        //Convertendo optional usuario em UserDTO
        UserDTO dto = userMapper.userToUserDto(users.get()); // coloca o .get por ser um optional
        //Criando e retornando um optional de UserDTO
        return Optional.of(dto);
    }

    /**
     * Metodo para adicionar usuario na lista.
     *
     * @param UserDTO que sera adicionado.
     * @return usuario que foi adicionado na lista.
     */
    public UserDTO addUser(UserDTO UserDTO) {
        //converter o Dto em usuario
        User users = userMapper.toUser(UserDTO);

        //salvar no banco
        users = userRepository.save(users);

        return UserDTO;
    }

    /**
     * Metodo para deletar um produto na lista.
     *
     * @param id que sera deletado.
     */
    public void deleteUser(Long id) {
        //verificar se o produto existe
        Optional<User> users = userRepository.findById(id);
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Delete is not possible users with id: " + id + " users not exist");
        }
        userRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar um produto na lista.
     *
     * @param UserDTO que sera atualizado.
     * @return produto atualizado.
     */

    public UserDTO updateUser(Long id, UserDTO UserDTO) {
        // Converter o DTO em usuario
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            userMapper.updateUserFromDto(UserDTO, existingUser);

            // Salvar o usuario atualizado no banco de dados
            User updatedUser = userRepository.save(existingUser);

            // Converter o usuario atualizado de volta para DTO e retornar
            return userMapper.userToUserDto(updatedUser);
        } else {
            throw new ResourceNotFoundException("User not exist!");
        }
    }

}

