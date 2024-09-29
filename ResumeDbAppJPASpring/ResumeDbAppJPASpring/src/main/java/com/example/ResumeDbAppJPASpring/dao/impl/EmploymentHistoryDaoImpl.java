package com.example.ResumeDbAppJPASpring.dao.impl;



import com.example.ResumeDbAppJPASpring.dao.inter.AbstractDAO;
import com.example.ResumeDbAppJPASpring.dao.inter.EmploymentHistoryDaoInter;
import com.example.ResumeDbAppJPASpring.entity.EmploymentHistory;
import com.example.ResumeDbAppJPASpring.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private EmploymentHistory getAllEmploymentHistory(ResultSet resultSet) throws Exception {

        int userId = resultSet.getInt("userId");
        String header= resultSet.getString("header");
        Date beginDate = resultSet.getDate("beginDate");
        Date endDate= resultSet.getDate("endDate");
        String jobDescription = resultSet.getString("jobDescription");
        EmploymentHistory emp = new EmploymentHistory(null, header, beginDate, endDate,jobDescription, new User(userId));
        return emp;
    }
    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryById(int userId) {

        List<EmploymentHistory> result = new ArrayList<>();

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
                EmploymentHistory emp = getAllEmploymentHistory(resultSet);
                result.add(emp);

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;

    }



}

