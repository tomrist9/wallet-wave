package com.example.ResumeDbAppJPASpring.dao.impl;


import com.example.ResumeDbAppJPASpring.dao.inter.AbstractDAO;
import com.example.ResumeDbAppJPASpring.dao.inter.CountryDaoInter;
import com.example.ResumeDbAppJPASpring.entity.Country;
import com.example.ResumeDbAppJPASpring.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {
    private Country getAllCountries(ResultSet resultSet) throws Exception{
     int userId=resultSet.getInt("user_id");
     String nationality=resultSet.getString("nationality");
     String birthPlace=resultSet.getString("birthplace");
     Country country=new Country(null, new User(userId), nationality, birthPlace);
     return country;

    }
    @Override
    public List<Country> getAllCountriesByUserId(int userId) {
    List <Country> result=new ArrayList<>();
    try(Connection connection=connect()){
        PreparedStatement preparedStatement=connection.prepareStatement("select"
                +"country.*,"
                +"u.user_id"
                +"from user u"
                +"left join user u on country.user_id=u.id"
                +"where"
                +"country.user_id=?");
        preparedStatement.setInt(1, userId);
        preparedStatement.execute();
        ResultSet resultSet=preparedStatement.getResultSet();

        while(resultSet.next()){
          Country country=getAllCountries(resultSet);
          result.add(country);

        }
    }catch (Exception exception) {
        exception.printStackTrace();
    }
        return result;

    }
}
