package org.sourceit.command.impl.applicant;

import org.sourceit.command.ICommand;
import org.sourceit.db.ApplicantDBProvider;
import org.sourceit.db.ProfessionDBProvider;
import org.sourceit.entities.Applicant;
import org.sourceit.entities.Profession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditApplicantCommand implements ICommand {

    private ApplicantDBProvider provider = ApplicantDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {

        List<Profession> professions;

        try {
            professions = ProfessionDBProvider.INSTANCE.getProfession();
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }
        request.setAttribute("professions", professions);

        try {
            Long applicantId = Long.parseLong(request.getParameter("id"));
            Applicant applicant = provider.getApplicant(applicantId);
            request.setAttribute("applicant", applicant);
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }
        request.setAttribute("title", "Edit applicant");
        return "pages/applicant/edit_applicant.jsp";
    }
}
