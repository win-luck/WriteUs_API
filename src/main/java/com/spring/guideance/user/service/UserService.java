package com.spring.guideance.user.service;

import com.spring.guideance.user.domain.User;
import com.spring.guideance.user.dto.request.CreateUserDto;
import com.spring.guideance.user.dto.request.UpdateUserDto;
import com.spring.guideance.user.dto.response.ResponseUserDto;
import com.spring.guideance.user.repository.UserRepository;
import com.spring.guideance.util.exception.ResponseCode;
import com.spring.guideance.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public Long createUser(CreateUserDto createUserDto) {
        ValidateDuplicateUser(createUserDto);
        return userRepository.save(User.createUser(createUserDto)).getId();
    }

    // 중복 이메일 체크
    private void ValidateDuplicateUser(CreateUserDto createUserDto) {
        userRepository.findByEmail(createUserDto.getEmail())
                .ifPresent(user -> {
                    throw new UserException(ResponseCode.USER_ALREADY_EXISTS);
                });
    }

    // 로그인
    public ResponseUserDto login(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));
        return new ResponseUserDto(user);
    }

    // 회원정보 조회
    public ResponseUserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));
        return new ResponseUserDto(user);
    }

    // 회원정보 수정
    public void updateUser(UpdateUserDto updateUserDto) {
        User user = userRepository.findById(updateUserDto.getUserId()).orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));
        user.updateUser(updateUserDto.getUserName());
    }

    // 회원정보 삭제
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException(ResponseCode.USER_NOT_FOUND));
        userRepository.deleteById(user.getId());
    }
}