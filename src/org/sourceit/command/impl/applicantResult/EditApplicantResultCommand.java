package org.sourceit.command.impl.applicantResult;

import org.sourceit.command.ICommand;
import org.sourceit.db.ApplicantResultDBProvider;
import org.sourceit.entities.ApplicantResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditApplicantResultCommand implements ICommand{

    private ApplicantResultDBProvider provider = ApplicantResultDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {

        try {
            Long applicantResultId = Long.parseLong(request.getParameter("id"));
            ApplicantResult applicantResult = provider.getApplicantResult(applicantResultId);
            request.setAttribute("applicantResult", applicantResult);
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }

        request.setAttribute("title", "Edit applicantResult");
        return "pages/applicantResult/edit_applicantResult.jsp";
    }
}
