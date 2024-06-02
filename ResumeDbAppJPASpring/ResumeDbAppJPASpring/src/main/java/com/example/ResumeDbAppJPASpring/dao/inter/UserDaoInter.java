package com.example.ResumeDbAppJPASpring.dao.inter;



import com.example.ResumeDbAppJPASpring.entity.User;

import java.util.List;

public interface UserDaoInter {
    public List<User> getAll();

    User findByEmailAndPassword(String email, String password);

    public boolean updateUser(User user);
    public boolean removeUser(int id);
    public void addUser(User user);

    public User getById(int id);
    public User findByEmailAndPassword();
}
