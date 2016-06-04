package org.sourceit.command.impl.specialitysubject;

import org.sourceit.command.ICommand;
import org.sourceit.db.ProfessionDBProvider;
import org.sourceit.db.SpecialitySubjectDBProvider;
import org.sourceit.db.SubjectDBProvider;
import org.sourceit.entities.Profession;
import org.sourceit.entities.SpecialitySubject;
import org.sourceit.entities.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditSpecialitySubjectsCommand implements ICommand{

    private SpecialitySubjectDBProvider provider = SpecialitySubjectDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {

        List<Profession> professions;
        List<Subject> subjects;

        try {
            professions = ProfessionDBProvider.INSTANCE.getProfession();
            subjects = SubjectDBProvider.INSTANCE.getSubjects();
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }
        request.setAttribute("professions", professions);
        request.setAttribute("subjects", subjects);

        try {
            Long specialitySubjectId = Long.parseLong(request.getParameter("id"));
            SpecialitySubject specialitySubject = provider.getSpecialitySubject(specialitySubjectId);
            request.setAttribute("specialitySubject", specialitySubject);
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }
        request.setAttribute("title", "Edit specialitySubject");
        return "pages/specialitySubject/edit_specialitySubject.jsp";
    }
}
