package com.example.ResumeDbAppJPASpring.dao.impl;


import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.ResumeDbAppJPASpring.dao.inter.UserDaoInter;
import com.example.ResumeDbAppJPASpring.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.aot.generate.ValueCodeGenerator;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

import static com.example.ResumeDbAppJPASpring.dao.inter.AbstractDAO.connect;

@Repository
public class UserDaoImpl  implements UserDaoInter {
    @PersistenceContext
    EntityManager entityManager;

//    private User getUser(ResultSet resultSet) throws Exception {
//     int id = resultSet.getInt("id");
//     String name= resultSet.getString("name");
//     String surname= resultSet.getString("surname");
//     String phone= resultSet.getString("phone");
//     String email= resultSet.getString("email");
//     int nationalityId=resultSet.getInt("nationality_id");
//     int birthplaceId=resultSet.getInt("birthplace_id");
//     String nationalityStr= resultSet.getString("nationality");
//     String birthPlaceStr= resultSet.getString("birthplace");
//
//     Date birthdate=resultSet.getDate("birthdate");
//     Country nationality= new Country(nationalityId, null,
//             nationalityStr);
//     Country birthplace=new Country(birthplaceId, birthPlaceStr, null);
//
//     return new User(id, name, surname, phone, email, birthdate, nationality, birthplace);
//    }



    public List<User> getAll(String name, String surname, Integer nationalityId) {
      String jpql="select * from  User  where 1=1";
       if(name!=null&&!name.trim().isEmpty()){
           jpql+=" and u.name=:name";
       }
        if(surname!=null&&!surname.trim().isEmpty()){
            jpql+=" and u.surname=:surname";
        }
        if(nationalityId!=null){
            jpql+=" and u.nationality.id=:nid";
        }
        Query query= (Query) entityManager.createQuery(jpql, User.class);
        if(name!=null&&!name.trim().isEmpty()){
           query.setParameter("name", name);
        }
        if(surname!=null&&!surname.trim().isEmpty()){
            query.setParameter("surname", surname);
        }
        if(nationalityId!=null){
            query.setParameter("nid", nationalityId);
        }
        return query.getResultList();

    }



//@Override
//    public void addUser(User u){
//        try(Connection connection=connect()){
//            PreparedStatement statement= connect().prepareStatement("insert into user(name, surname, phone, email, profilDescription, address, birthdate, birthplace_id, nationality_id)");
//            statement.setString(1, u.getName());
//            statement.setString(2, u.getSurname());
//            statement.setString(3, u.getPhone());
//            statement.setString(4, u.getEmail());
//            statement.execute();
//        }catch (Exception exception){
//            exception.printStackTrace();
//        }
//    }


    @Override
    public User getById(int id) {
        User user=entityManager.find(User.class, id);

        return user;
    }

    @Override
    public User findByEmailAndPassword() {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> q1 = criteriaBuilder.createQuery(User.class);
        Root<User> postRoot = q1.from(User.class);
        CriteriaQuery<User> q2 = q1
                .where(criteriaBuilder.equal(postRoot.get("email"), email), criteriaBuilder.equal(postRoot.get("password"), password));
        Query query = entityManager.createQuery(q2);

        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;

    }
    public User findByEmail(String email){
        Query query=entityManager.createNativeQuery("select * from user where email=?", User.class);
        query.setParameter(1, email);
        List<User> list=query.getResultList();
        if(list.size()==1){
            return list.get(0);
        }
        return null;
    }


    @Override
    public boolean updateUser(User user) {
      entityManager.merge(user);
      return true;

    }


    @Override
    public boolean removeUser(int id) {
       User user=entityManager.find(User.class, id);
       entityManager.remove(user);
       return true;
    }

    @Override
    public void addUser(User user) {

    }


  private static BCrypt.Hasher crypt=BCrypt.withDefaults();
}
