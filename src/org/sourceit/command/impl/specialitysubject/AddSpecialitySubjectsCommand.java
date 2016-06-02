package org.sourceit.command.impl.specialitysubject;

import org.sourceit.command.ICommand;
import org.sourceit.db.ProfessionDBProvider;
import org.sourceit.db.SubjectDBProvider;
import org.sourceit.entities.Profession;
import org.sourceit.entities.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddSpecialitySubjectsCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        request.setAttribute("title", "Add SpecialitySubject");

        List<Profession> professions;
        List<Subject> subjects;

        try {
            subjects = SubjectDBProvider.INSTANCE.getSubjects();
            professions = ProfessionDBProvider.INSTANCE.getProfession();
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }

        request.setAttribute("professions", professions);
        request.setAttribute("subjects", subjects);


        return "pages/specialitySubject/edit_specialitySubject.jsp";
    }
}
