package org.sourceit.command.impl.specialitysubject;

import org.sourceit.command.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddSpecialitySubjectsCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        request.setAttribute("title", "Add SpecialitySubject");

        return "pages/specialitySubject/edit_specialitySubject.jsp";
    }
}
