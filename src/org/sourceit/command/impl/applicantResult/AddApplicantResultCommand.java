package org.sourceit.command.impl.applicantResult;

import org.sourceit.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddApplicantResultCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        request.setAttribute("title", "Add applicant");

        return "pages/applicantResult/edit_applicantResult.jsp";
    }
}
