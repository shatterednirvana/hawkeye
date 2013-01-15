package com.appscale.hawkeye.taskqueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaskCounterWorkerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String retry = request.getParameter("retry");
        String timesTaskHasFailed = request.getHeader("X-AppEngine-TaskRetryCount");
        if ("true".equals(retry) && "0".equals(timesTaskHasFailed)) {
                throw new ServletException();
        }

        TaskUtils.process(request.getParameter("key"));
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        TaskUtils.process(request.getParameter("key"));
    }


}
