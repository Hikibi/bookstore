package com.lanou.bookstore.user.web.controller;

import com.lanou.vcode.utils.VerifyCode;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Created by dllo on 17/9/8.
 */
@WebServlet("/VerifyServlet")
public class VerifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        VerifyCode.output(image,response.getOutputStream());
        request.getSession().setAttribute("vCode", vc.getText());

//        将文字形式的验证码储存为 verify 属性
//        this.getServletContext().setAttribute("verify",vc.getText());


//        System.out.println(this.getServletContext().getAttribute("vCode"));


    }


}