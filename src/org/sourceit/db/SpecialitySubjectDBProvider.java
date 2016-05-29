package org.sourceit.db;



import org.sourceit.entities.Profession;
import org.sourceit.entities.SpecialitySubject;
import org.sourceit.entities.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum SpecialitySubjectDBProvider {

    INSTANCE;

    private Connection connection;

    SpecialitySubjectDBProvider() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_applicant", "root", "ise11thedead");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Class not found: com.mysql.jdbc.Driver " + e);
            throw new RuntimeException("Class not found: com.mysql.jdbc.Driver");
        }
    }

    public SpecialitySubject getSpecialitySubject(long specialityId) throws Exception {
        PreparedStatement preparedStatement = null;
        SpecialitySubject specialitySubject = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM speciality_subject WHERE sp_sb_id=?");
            preparedStatement.setInt(1, (int) specialityId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                specialitySubject = new SpecialitySubject();
                Profession profession = new Profession();
                Subject subject = new Subject();

                specialitySubject.setId(resultSet.getInt("sp_sb_id"));
                profession.setId(resultSet.getInt("profession_id"));
                subject.setId(resultSet.getInt("subject_id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return specialitySubject;
    }

    public List<SpecialitySubject> getSpecialitySubjects() throws Exception {

        Statement statement = null;
        List<SpecialitySubject> specialitySubjects = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM speciality_subject");
            SpecialitySubject specialitySubject = null;

            while (resultSet.next()) {
                specialitySubject = new SpecialitySubject();
                Profession profession = new Profession();
                Subject subject = new Subject();


                specialitySubject.setId(resultSet.getInt("sp_sb_id"));
                profession.setId(resultSet.getInt("profession_id"));
                subject.setId(resultSet.getInt("subject_id"));
                specialitySubject.setSubject(subject);
                specialitySubject.setProfession(profession);
                specialitySubjects.add(specialitySubject);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return specialitySubjects;
    }

    public void saveSpecialitySubject(SpecialitySubject specialitySubject) throws Exception {
        PreparedStatement preparedStatement = null;

        try {
            if (specialitySubject.getId() == -1) {
                preparedStatement = connection.prepareStatement("INSERT INTO speciality_subject (profession_id," +
                        " subject_id) VALUES (?, ?) ");

                preparedStatement.setLong(1, specialitySubject.getProfession().getId());
                preparedStatement.setLong(2, specialitySubject.getSubject().getId());


            } else {
                preparedStatement = connection.prepareStatement("UPDATE speciality_subject SET profession_id=?," +
                        " subject_id=? WHERE sp_sb_id=?");

                preparedStatement.setLong(1, specialitySubject.getProfession().getId());
                preparedStatement.setLong(2, specialitySubject.getSubject().getId());
                preparedStatement.setLong(3, specialitySubject.getId());

            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void deleteSpecialitySubject(long specialitySubjectId) throws Exception {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM SPECIALITY_SUBJECT WHERE SP_SB_ID=?");

            preparedStatement.setInt(1, (int) specialitySubjectId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

}
