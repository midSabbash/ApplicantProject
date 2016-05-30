package org.sourceit.command.impl.profession;

import org.sourceit.command.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddProfessionCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        request.setAttribute("title", "Add Profession");

        return "pages/profession/edit_profession.jsp";
    }
}
