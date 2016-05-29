package org.sourceit.command.impl.specialitysubject;

import org.sourceit.command.ICommand;
import org.sourceit.db.SpecialitySubjectDBProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteSpecialitySubjectsCommand implements ICommand{

    private SpecialitySubjectDBProvider provider = SpecialitySubjectDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        try {
            Long specialitySubjectId = Long.parseLong(request.getParameter("id"));
            provider.deleteSpecialitySubject(specialitySubjectId);
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }

        return "controller?command=specSubjects";
    }

}
