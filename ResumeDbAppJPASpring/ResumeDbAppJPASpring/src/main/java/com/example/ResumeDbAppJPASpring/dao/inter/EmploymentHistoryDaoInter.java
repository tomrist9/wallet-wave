package com.example.ResumeDbAppJPASpring.dao.inter;



import com.example.ResumeDbAppJPASpring.entity.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryDaoInter {
    public List<EmploymentHistory> getAllEmploymentHistoryById(int userId);

}
