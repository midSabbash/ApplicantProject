package org.sourceit.db;

import org.sourceit.entities.ApplicantResult;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ApplicantResultDBProviderTest {

    ApplicantResultDBProvider provider = ApplicantResultDBProvider.INSTANCE;

    @BeforeMethod
    public void beforeDelete() {
        try {
            for (ApplicantResult applicantResult : provider.getApplicantsResult()) {
                provider.deleteApplicantResult(applicantResult.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getApplicantResult() {
        try {
            List applicantResult = provider.getApplicantsResult();
            Assert.assertTrue(applicantResult.size() == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveApplicantResult() {
        try {
            provider.saveApplicantResult(new ApplicantResult(1, 1, "Math", 135));

            ApplicantResult applicantResult = null;
            Long tempId = null;

            for (ApplicantResult temp : provider.getApplicantsResult()) {
                if (temp.getSubjectName().equalsIgnoreCase("Math")) {
                    tempId = temp.getId();
                    temp = provider.getApplicantResult(temp.getId());
                }
            }

            Assert.assertTrue(applicantResult.getId() == tempId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteApplicantResult() {
        try {
            provider.deleteApplicantResult(1L);

            List professions = provider.getApplicantsResult();

            Assert.assertTrue(professions.size() == 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getApplicantResultsWithResult() {
        try {

            provider.saveApplicantResult(new ApplicantResult(1, 1, "Math", 135));

            provider.saveApplicantResult(new ApplicantResult(2, 2, "Astronomy", 144));

            provider.saveApplicantResult(new ApplicantResult(3, 3, "Physics", 77));
            List applicantsResult = provider.getApplicantsResult();

            Assert.assertTrue(applicantsResult.size() == 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateApplicantResults() {
        try {
            ApplicantResult applicantResult = new ApplicantResult(1, 1, "Math", 135);
            applicantResult.setId(3L);
            provider.saveApplicantResult(applicantResult);

            Assert.assertEquals(provider.getApplicantResult(3L).getSubjectName(), "Math");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
