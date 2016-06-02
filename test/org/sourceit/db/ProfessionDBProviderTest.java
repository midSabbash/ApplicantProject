package org.sourceit.db;

import org.sourceit.entities.Profession;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ProfessionDBProviderTest {

    private ProfessionDBProvider provider = ProfessionDBProvider.INSTANCE;

    @BeforeMethod
    public void beforeDelete() {
        try {
            for (Profession profession : provider.getProfession()) {
                provider.deleteProfession(profession.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getProfession() {
        try {
            List professions = provider.getProfession();
            Assert.assertTrue(professions.size() == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveProfession() {
        try {
            provider.saveProfession(new Profession("Computer Science"));

            Profession profession = null;
            Long tempId = null;

            for (Profession temp : provider.getProfession()) {
                if (temp.getProfessionName().equalsIgnoreCase("Computer Science")) {
                    tempId = temp.getId();
                    temp = provider.getProfession(temp.getId());
                }
            }

            Assert.assertTrue(profession.getId() == tempId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteProfession() {
        try {
            provider.deleteProfession(1L);

            List professions = provider.getProfession();

            Assert.assertTrue(professions.size() == 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getProfessionsWithResult() {
        try {

            provider.saveProfession(new Profession("Computer Science"));

            provider.saveProfession(new Profession("Nuclear Physics"));

            provider.saveProfession(new Profession("System administration"));
            List professions = provider.getProfession();

            Assert.assertTrue(professions.size() == 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateProfession() {
        try {
            Profession profession = new Profession("Nuclear Reactors");
            profession.setId(3L);
            provider.saveProfession(profession);

            Assert.assertEquals(provider.getProfession(3L).getProfessionName(), "Nuclear Reactors");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void afterDelete() {
        try {
            for (Profession profession : provider.getProfession()) {
                provider.deleteProfession(profession.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
