package org.sourceit.db;

import org.sourceit.entities.Applicant;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;

public class ApplicantDBProviderTest {

    private ApplicantDBProvider provider = ApplicantDBProvider.INSTANCE;

    @BeforeMethod
    public void beforeDelete() {
        try {
            for (Applicant applicant : provider.getApplicants()) {
                provider.deleteApplicant(applicant.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getApplicants() {
        try {
            List applicants = ApplicantDBProvider.INSTANCE.getApplicants();
            Assert.assertTrue(applicants.size() == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveApplicant() {
        try {
            provider.saveApplicant(new Applicant(1L,"Profession","Ivan","Ivanov",2011));

            Applicant applicant = null;
            Long tempId = null;

            for (Applicant temp : provider.getApplicants()) {
                if (temp.getProfessionName().equalsIgnoreCase("Profession")) {
                    temp = provider.getApplicant(temp.getId());
                    tempId = temp.getId();
                    temp = provider.getApplicant(temp.getId());
                }
            }
            System.out.println(applicant.getId()+" "+tempId+" "+tempId);

            Assert.assertTrue(applicant.getId() == tempId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteApplicant() {
        try {
            provider.deleteApplicant(1L);

            List applicants = provider.getApplicants();

            Assert.assertTrue(applicants.size() == 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicantWithResult() {
        try {
            provider.saveApplicant(new Applicant(1L,"Computer Science","Ivan","Ivanov",1992));
            provider.saveApplicant(new Applicant(1L,"Nuclear Physics","Petr","Petrov",1998));
            provider.saveApplicant(new Applicant(1L,"Engineer","Vasya","Vasev",1991));
            List applicants = provider.getApplicants();

            Assert.assertTrue(applicants.size() == 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateApplicant() {
        try {
            Applicant applicant = new Applicant(3L,"Computer Science","Ivan","Ivanov",1992);
            applicant.setId(3L);
            provider.saveApplicant(applicant);
            Assert.assertEquals(provider.getApplicant(3L).getFirstName(), "Ivan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void AfterDelete() {
        try {
            for (Applicant applicant : provider.getApplicants()) {
                provider.deleteApplicant(applicant.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}