package org.sourceit.command.impl.applicant;

import org.sourceit.command.ICommand;
import org.sourceit.db.ProfessionDBProvider;
import org.sourceit.entities.Profession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddApplicantCommand implements ICommand {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        request.setAttribute("title", "Add applicant");

        List<Profession> professions;

        try {
            professions = ProfessionDBProvider.INSTANCE.getProfession();
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }
        request.setAttribute("professions", professions);
        return "pages/applicant/edit_applicant.jsp";
    }
}
