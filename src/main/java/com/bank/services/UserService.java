package com.bank.services;


import com.bank.controller.AuthentificateInfo;
import com.bank.controller.UserRequest;
import com.bank.entities.Session;
import com.bank.entities.User;
import com.bank.repositories.SessionRepository;
import com.bank.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final MessageDigest md5;
    private final ApplicationSession applicationSession;

    public UserService(UserRepository userRepository, SessionRepository sessionRepository, ApplicationSession session) throws NoSuchAlgorithmException {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.applicationSession = session;
        this.md5 = MessageDigest.getInstance("MD5");
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void save(UserRequest request) {
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(digest(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void deleteAllById(List<Integer> ids) {
        userRepository.deleteAllById(ids);
    }

    public Session auth(AuthentificateInfo info) {
        User user = userRepository.getByLogin(info.getLogin()).orElseThrow(RuntimeException::new);
        if (user.getPassword().equals(digest(info.getPassword()))) {
            Session session = new Session(UUID.randomUUID().toString());
            return sessionRepository.save(session);
        }
        throw new RuntimeException("Password not right");
    }

    public BigInteger digest(String password) {
        try {
            final MessageDigest md = (MessageDigest) md5.clone();
            md.update(password.getBytes());
            return new BigInteger(md5.digest());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void authenticate(String sessionId){
        Session session = sessionRepository.findById(sessionId)
                .orElse(null);
        applicationSession.init(session);
        UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(
                applicationSession,
                session.getUser() != null ? session.getUser().getPassword() : "",
                applicationSession.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }

}
