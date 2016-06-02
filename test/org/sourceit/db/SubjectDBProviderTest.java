package org.sourceit.db;

import org.sourceit.entities.Subject;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SubjectDBProviderTest {

    private SubjectDBProvider provider = SubjectDBProvider.INSTANCE;

    @BeforeMethod
    public void beforeDelete() {
        try {
            for (Subject subject : provider.getSubjects()) {
                provider.deleteSubject(subject.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void AfterDelete() {
        try {
            for (Subject subject : provider.getSubjects()) {
                provider.deleteSubject(subject.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSubjects() {
        try {
            List professions = provider.getSubjects();
            Assert.assertTrue(professions.size() == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveSubject() {
        try {
            provider.saveSubject(new Subject("Astronomy"));

            Subject subject = null;
            Long tempId = null;

            for (Subject temp : provider.getSubjects()) {
                if (temp.getSubjectName().equalsIgnoreCase("Astronomy")) {
                    tempId = temp.getId();
                    temp = provider.getSubject(temp.getId());
                }
            }

            Assert.assertTrue(subject.getId() == tempId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteSubject() {
        try {
            provider.deleteSubject(1L);

            List professions = provider.getSubjects();

            Assert.assertTrue(professions.size() == 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSubjectsWithResult() {
        try {

            provider.saveSubject(new Subject("Astronomy"));

            provider.saveSubject(new Subject("Math"));

            provider.saveSubject(new Subject("Physic"));
            List subjects = provider.getSubjects();

            Assert.assertTrue(subjects.size() == 3);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateSubject() {
        try {

            provider.saveSubject(new Subject("Astronomy"));

            Subject profession = new Subject("Math");
            profession.setId(3L);
            provider.saveSubject(profession);

            Assert.assertEquals(provider.getSubject(3L).getSubjectName(), "Math");

            provider.saveSubject(new Subject("Physic"));
            List professions = provider.getSubjects();

            Assert.assertTrue(professions.size() == 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
