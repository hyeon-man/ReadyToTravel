package kr.ac.kopo.ReadyToTravel.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import org.springframework.web.servlet.HandlerInterceptor;

public class MemberInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        if(memberDTO != null) {
            System.out.println("MemberInterceptor: TRUE");
            return true;
        }

        String query = request.getQueryString();
        session.setAttribute("target_url", request.getRequestURI() + (query != null ? "?" + query : "") );
        System.out.println("INTERCEPTOR: " + session.getAttribute("target_url"));

        response.sendRedirect("/member/login");

        System.out.println("UserInterceptor: FALSE");
        return false;
    }
}