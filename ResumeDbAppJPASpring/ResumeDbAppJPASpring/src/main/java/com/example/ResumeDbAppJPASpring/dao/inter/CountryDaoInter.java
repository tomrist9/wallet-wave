package com.example.ResumeDbAppJPASpring.dao.inter;



import com.example.ResumeDbAppJPASpring.entity.Country;

import java.util.List;

public interface CountryDaoInter {
    public List<Country> getAllCountriesByUserId(int userId);
}
