package com.example.ResumeDbAppJPASpring;


import com.example.ResumeDbAppJPASpring.dao.impl.UserDaoImpl;
import com.example.ResumeDbAppJPASpring.dao.impl.UserSkillDaoImpl;
import com.example.ResumeDbAppJPASpring.dao.inter.UserDaoInter;
import com.example.ResumeDbAppJPASpring.dao.inter.UserSkillDaoInter;

public class Context {
    public static UserDaoInter instanceUserDao(){
        return new UserDaoImpl();
    }
    public static UserSkillDaoInter instanceUserSkillDao(){
        return new UserSkillDaoImpl();
    }
}
