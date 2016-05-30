package org.sourceit.command.impl.specialitysubject;

import org.sourceit.command.ICommand;
import org.sourceit.db.SpecialitySubjectDBProvider;
import org.sourceit.entities.Profession;
import org.sourceit.entities.SpecialitySubject;
import org.sourceit.entities.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveSpecialitySubjectsCommand implements ICommand {

    private SpecialitySubjectDBProvider provider = SpecialitySubjectDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        SpecialitySubject specialitySubject = new SpecialitySubject();

        Profession profession = new Profession();
        Subject subject = new Subject();

        specialitySubject.setProfession(profession);
        specialitySubject.setSubject(subject);

        profession.setId(Long.parseLong(request.getParameter("profession_id")));
        subject.setId(Long.parseLong(request.getParameter("subject_id")));

        if (request.getParameter("sp_sb_id") != null) {
            specialitySubject.setId(Long.parseLong(request.getParameter("sp_sb_id")));
        }

        try {
            provider.saveSpecialitySubject(specialitySubject);
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }
        return "controller?command=specSubjects";
    }
}
