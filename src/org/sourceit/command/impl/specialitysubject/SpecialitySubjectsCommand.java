package org.sourceit.command.impl.specialitysubject;

import org.sourceit.command.ICommand;
import org.sourceit.db.SpecialitySubjectDBProvider;
import org.sourceit.entities.SpecialitySubject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SpecialitySubjectsCommand implements ICommand {

    private SpecialitySubjectDBProvider provider = SpecialitySubjectDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        List<SpecialitySubject> specialitySubjects = null;

        try {
            specialitySubjects = provider.getSpecialitySubjects();
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }

        request.setAttribute("specialitySubjects", specialitySubjects);

        return "pages/specialitySubject/specialitySubjects.jsp";
    }

}
