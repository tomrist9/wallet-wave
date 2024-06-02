package com.example.ResumeDbAppJPASpring.dao.impl;



import com.example.ResumeDbAppJPASpring.dao.inter.AbstractDAO;
import com.example.ResumeDbAppJPASpring.dao.inter.UserSkillDaoInter;
import com.example.ResumeDbAppJPASpring.entity.Skill;
import com.example.ResumeDbAppJPASpring.entity.User;
import com.example.ResumeDbAppJPASpring.entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {
    private UserSkill getUserSkill(ResultSet resultSet) throws Exception{
        int userId=resultSet.getInt("user_id");
        int skillId=resultSet.getInt("skill_id");

        String skillName=resultSet.getString("skill_name");
        int power=resultSet.getInt("power");

        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power );
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();

        try (Connection connection = connect()) {

            PreparedStatement stmt = connection.prepareStatement("select"
                    + "u.*,"
                    + "us.skill_id,"
                    + "s.name as skill_name"
                    + "us.power"
                    + "from"
                    + "user_skill us"
                    + "left join user on us.user_id=u.id"
                    + "left join skill s on us.skill_id=s.id"
                    + "where"
                    + "us.user_id=?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();

            while (resultSet.next()) {
                UserSkill userSkill = getUserSkill(resultSet);
                result.add(userSkill);

            }
            resultSet.close();
            stmt.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;


    }
}
