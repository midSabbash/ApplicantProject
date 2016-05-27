package org.sourceit.db;



import org.sourceit.entities.Profession;
import org.sourceit.entities.SpecialitySubject;
import org.sourceit.entities.Subject;

import java.sql.*;

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

                specialitySubject.setProfession(resultSet.getInt("profession_id"));

                specialitySubject.setSubject(resultSet.getInt("subject_id"));
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

}
