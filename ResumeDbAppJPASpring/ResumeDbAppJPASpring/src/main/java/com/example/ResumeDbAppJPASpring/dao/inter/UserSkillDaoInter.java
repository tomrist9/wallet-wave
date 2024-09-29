package com.example.ResumeDbAppJPASpring.dao.inter;



import com.example.ResumeDbAppJPASpring.entity.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {
   public List<UserSkill> getAllSkillByUserId(int userId);
}
