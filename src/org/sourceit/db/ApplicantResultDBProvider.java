package org.sourceit.db;

import org.sourceit.entities.Applicant;
import org.sourceit.entities.ApplicantResult;
import org.sourceit.entities.Profession;
import org.sourceit.entities.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum  ApplicantResultDBProvider {

    INSTANCE;

    private Connection connection;

    ApplicantResultDBProvider() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_applicant", "root", "ise11thedead");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Class not found: com.mysql.jdbc.Driver " + e);
            throw new RuntimeException("Class not found: com.mysql.jdbc.Driver");
        }
    }

    public ApplicantResult getApplicantResult(long applicantResultId) throws Exception {
        PreparedStatement preparedStatement = null;
        ApplicantResult applicantResult = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM APPLICANT_RESULT WHERE APPLICANT_RESULT_ID=?");
            preparedStatement.setInt(1, (int) applicantResultId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                applicantResult = new ApplicantResult();
                applicantResult.setId(resultSet.getInt("APPLICANT_RESULT_ID"));
                applicantResult.setApplicantId(resultSet.getInt("APPLICANT_ID"));
                applicantResult.setSubjectId(resultSet.getInt("SUBJECT_ID"));
                applicantResult.setMark(resultSet.getInt("MARK"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return applicantResult;
    }

    public List<ApplicantResult> getApplicantsResult() throws Exception {

        Statement statement = null;
        List<ApplicantResult> applicantsResult = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SPECIALITY_SUBJECT JOIN (APPLICANT, SUBJECT)" +
                    " ON applicant.APPLICANT_ID=applicant.APPLICANT_ID AND" +
                    " SPECIALITY_SUBJECT.SUBJECT_ID=SUBJECT.SUBJECT_ID");

            ApplicantResult applicantResult = null;
            Applicant applicant  = null;
            Subject subject = null;
            while (resultSet.next()) {

                applicantResult = new ApplicantResult();
                applicant = new Applicant();
                subject = new Subject();

                applicant.setId(resultSet.getInt("APPLICANT_ID"));
                subject.setId(resultSet.getInt("SUBJECT_ID"));
                applicantResult.setId(resultSet.getInt("APPLICANT_RESULT_ID"));
                applicantResult.setApplicantId(resultSet.getInt("APPLICANT_ID"));
                applicantResult.setSubjectId(resultSet.getInt("SUBJECT_ID"));
                applicantResult.setMark(resultSet.getInt("MARK"));
                applicantsResult.add(applicantResult);
            }

        } catch (SQLException e) {
            throw new Exception(e);
        }

        return applicantsResult;
    }

    public void saveApplicantResult (ApplicantResult applicantResult) throws Exception {
        PreparedStatement preparedStatement = null;

        try {
            if (applicantResult.getId() == -1) {
                System.out.println("new applicantResult");
                preparedStatement = connection.prepareStatement("INSERT INTO APPLICANT_RESULT (APPLICANT_ID, " +
                        "SUBJECT_ID, MARK) VALUES (?, ?, ?) ");

                preparedStatement.setLong(1, applicantResult.getApplicantId());
                preparedStatement.setLong(2, applicantResult.getSubjectId());
                preparedStatement.setInt(3, applicantResult.getMark());

            } else {
                System.out.println("update applicantResult");
                preparedStatement = connection.prepareStatement("UPDATE APPLICANT_RESULT SET APPLICANT_ID=?, " +
                        "SUBJECT_ID=?, MARK=? WHERE APPLICANT_RESULT_ID=?");

                preparedStatement.setLong(1, applicantResult.getApplicantId());
                preparedStatement.setLong(2, applicantResult.getSubjectId());
                preparedStatement.setInt(3, applicantResult.getMark());
                preparedStatement.setLong(4, applicantResult.getId());
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

    public void deleteApplicantResult(long applicantResultId) throws Exception {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM APPLICANT_RESULT WHERE APPLICANT_RESULT_ID=?");

            preparedStatement.setInt(1, (int) applicantResultId);
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
