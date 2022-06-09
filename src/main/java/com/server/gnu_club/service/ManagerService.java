package com.server.gnu_club.service;

import com.server.gnu_club.S3Service;
import com.server.gnu_club.domain.dto.request.AccountDto;
import com.server.gnu_club.domain.user.Manager;
import com.server.gnu_club.domain.user.Member;
import com.server.gnu_club.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerService implements UserBaseService<Manager> {

    private final ManagerRepository managerRepository;
    private final S3Service s3Service;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean isIdDuplicated(String signInId) {
        try {
            managerRepository.findById(signInId);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Transactional
    @Override
    public Long signup(AccountDto accountDto) {

        String encodedPassword = passwordEncoder.encode(accountDto.getPassword());
        Manager manager = new Manager(accountDto.getSignInId(), encodedPassword);
        managerRepository.save(manager);
        return manager.getId();
    }

    @Override
    public Long signIn(AccountDto accountDto) {
        try {
            Manager manager = managerRepository.findById(accountDto.getSignInId());
            boolean bMatch = passwordEncoder.matches(accountDto.getPassword(), manager.getPassword());

            if (bMatch) {
                return manager.getId();
            }

            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Manager findUser(Long userId) {
        return managerRepository.findOne(userId);
    }

    @Override
    public Boolean isCorrectPassword(Long userId, String inputPassword) {
        Manager manager = managerRepository.findOne(userId);
        return passwordEncoder.matches(inputPassword, manager.getPassword());
    }

    @Override
    public void putPassword(Long userId, String newPassword) {
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        Manager manager = managerRepository.findOne(userId);
        manager.putPassword(encodedNewPassword);
    }

    @Transactional
    public void putProfileImage(Long memberPk, MultipartFile image) {
        Manager manager = managerRepository.findOne(memberPk);

        String newImageKey = memberPk + "manager-image" + System.currentTimeMillis();
        String imageUrl = s3Service.upload(image, newImageKey);

        manager.putProfileImageUrl(imageUrl);
    }
}
