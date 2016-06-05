package org.sourceit.db;

import org.sourceit.entities.Profession;
import org.sourceit.entities.SpecialitySubject;
import org.sourceit.entities.Subject;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class SpecialitySubjectDBProviderTest {

    SpecialitySubjectDBProvider provider = SpecialitySubjectDBProvider.INSTANCE;

    @BeforeMethod
    public void beforeDelete() {
        try {
            for (SpecialitySubject specialitySubject : provider.getSpecialitySubjects()) {
                provider.deleteSpecialitySubject(specialitySubject.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSpecialitySubject() {
        try {
            List specialitySubject = provider.getSpecialitySubjects();
            Assert.assertTrue(specialitySubject.size() == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveSpecialitySubject() {
        try {
            provider.saveSpecialitySubject(new SpecialitySubject(new Profession("Computer Science"),new Subject("Math")));

            SpecialitySubject specialitySubject = null;
            Long tempId = null;

            for (SpecialitySubject temp : provider.getSpecialitySubjects()) {
                if (temp.profession.getProfessionName().equalsIgnoreCase("Computer Science")) {
                    tempId = temp.getId();
                    temp = provider.getSpecialitySubject(temp.getId());
                }
            }

            Assert.assertTrue(specialitySubject.getId() == tempId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteSpecialitySubject() {
        try {
            provider.deleteSpecialitySubject(1L);

            List professions = provider.getSpecialitySubjects();

            Assert.assertTrue(professions.size() == 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSpecialitySubjectWithResult() {
        try {

            provider.saveSpecialitySubject(new SpecialitySubject(new Profession("Computer Science"),new Subject("Math")));

            provider.saveSpecialitySubject(new SpecialitySubject(new Profession("Computer Science"),new Subject("Math")));

            provider.saveSpecialitySubject(new SpecialitySubject(new Profession("Computer Science"),new Subject("Math")));
            List professions = provider.getSpecialitySubjects();

            Assert.assertTrue(professions.size() == 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateSpecialitySubject() {
        try {
            SpecialitySubject specialitySubject = new SpecialitySubject(new Profession("Nuclear Reactors"),new Subject("Math"));
            specialitySubject.setId(3L);
            provider.saveSpecialitySubject(specialitySubject);

            Assert.assertEquals(provider.getSpecialitySubject(3L).getProfession().getProfessionName(), "Nuclear Reactors");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void afterDelete() {
        try {
            for (SpecialitySubject specialitySubject : provider.getSpecialitySubjects()) {
                provider.deleteSpecialitySubject(specialitySubject.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
