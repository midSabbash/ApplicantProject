package org.sourceit.command.impl.specialitysubject;

import org.sourceit.command.ICommand;
import org.sourceit.db.SpecialitySubjectDBProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddSpecialitySubjectsCommand implements ICommand{

    private SpecialitySubjectDBProvider provider = SpecialitySubjectDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        request.setAttribute("title", "Add SpecialitySubject");

        return "pages/specialitySubject/edit_specialitySubject.jsp";
    }

}
