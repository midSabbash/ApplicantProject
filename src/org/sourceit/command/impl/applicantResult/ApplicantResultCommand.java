package org.sourceit.command.impl.applicantResult;

import org.sourceit.command.ICommand;
import org.sourceit.db.ApplicantResultDBProvider;
import org.sourceit.entities.ApplicantResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ApplicantResultCommand implements ICommand{

    private ApplicantResultDBProvider provider = ApplicantResultDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {

        List<ApplicantResult> applicantsResult;

        try {
            applicantsResult = provider.getApplicantsResult();
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }
        request.setAttribute("applicantsResult", applicantsResult);
        return "pages/applicantResult/applicantsResult.jsp";
    }
}
