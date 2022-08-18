package net.proselyte.springbootdemo.service;

import lombok.RequiredArgsConstructor;
import net.proselyte.springbootdemo.entity.UserEntity;
import net.proselyte.springbootdemo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService
{
    private UserRepository userRepository;

    @Transactional
    public UserEntity save(UserEntity userEntity)
    {
        return userRepository.saveAndFlush(userEntity);
    }

    @Transactional
    public void delete(Integer id)
    {
        userRepository.deleteById(id);
    }

    public List<UserEntity> getAll()
    {
        return userRepository.findAll();
    }

    public UserEntity getById(Integer id)
    {
        return userRepository.getById(id);
    }
}
