package org.sourceit.command.impl.profession;

import org.sourceit.command.ICommand;
import org.sourceit.db.ProfessionDBProvider;
import org.sourceit.entities.Profession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProfessionCommand implements ICommand{

    private ProfessionDBProvider provider = ProfessionDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {

        try {
            Long professionId = Long.parseLong(request.getParameter("id"));
            Profession profession = provider.getProfession(professionId);
            request.setAttribute("profession", profession);
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }

        request.setAttribute("title", "Edit profession");
        return "pages/profession/edit_profession.jsp";
    }
}
