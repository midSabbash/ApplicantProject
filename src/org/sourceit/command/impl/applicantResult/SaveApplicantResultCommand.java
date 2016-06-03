package org.sourceit.command.impl.applicantResult;

import org.sourceit.command.ICommand;
import org.sourceit.db.ApplicantResultDBProvider;
import org.sourceit.entities.ApplicantResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveApplicantResultCommand implements ICommand {

    private ApplicantResultDBProvider provider = ApplicantResultDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        ApplicantResult applicantResult = new ApplicantResult();

        applicantResult.setApplicantId(Long.parseLong(request.getParameter("applicants")));
        applicantResult.setSubjectId(Long.parseLong(request.getParameter("subjects")));
        applicantResult.setMark(Integer.parseInt(request.getParameter("MARK")));
        if (request.getParameter("APPLICANT_RESULT_ID") != null) {
            applicantResult.setId(Long.parseLong(request.getParameter("APPLICANT_RESULT_ID")));
        }

        try {
            provider.saveApplicantResult(applicantResult);
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }
        return "controller?command=applicantResults";
    }
}
