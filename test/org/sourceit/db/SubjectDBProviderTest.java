package org.sourceit.db;

import org.sourceit.entities.Subject;
import org.testng.Assert;
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
            provider.saveSubject(new Subject("Computer Science"));

            Subject subject = null;
            Long tempId = null;

            for (Subject temp : provider.getSubjects()) {
                if (temp.getSubjectName().equalsIgnoreCase("Computer Science")) {
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

            provider.saveSubject(new Subject("Computer Science"));

            provider.saveSubject(new Subject("Nuclear Physics"));

            provider.saveSubject(new Subject("System administration"));
            List subjects = provider.getSubjects();

            Assert.assertTrue(subjects.size() == 3);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateSubject() {
        try {

            provider.saveSubject(new Subject("Computer Science"));

            Subject profession = new Subject("Nuclear Reactors");
            profession.setId(3L);
            provider.saveSubject(profession);

            Assert.assertEquals(provider.getSubject(3L).getSubjectName(), "Nuclear Reactors");

            provider.saveSubject(new Subject("System administration"));
            List professions = provider.getSubjects();

            Assert.assertTrue(professions.size() == 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
