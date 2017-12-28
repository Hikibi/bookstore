package com.lanou.bookstore.admin.web.controller;

import com.lanou.bookstore.admin.domain.Admin;
import com.lanou.bookstore.admin.service.AdminJudge;
import com.lanou.bookstore.admin.service.AdminService;
import com.lanou.bookstore.admin.service.ChoosePicture;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.order.domain.CountAndBooks;
import com.lanou.bookstore.order.domain.Orders;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends BaseServlet {

    private AdminJudge judge = new AdminJudge();

    private AdminService adminService = new AdminService();

    //    管理员登录
    public String login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Admin admin = CommonUtils.toBean(request.getParameterMap(), Admin.class);


        if ((judge.name(admin.getUsername()) == 1 || judge.password(admin.getPassword()) == 1 ||
                judge.name(admin.getUsername()) == 2)) {
            request.getSession().setAttribute("msg", "用户名密码输入不符合规范");
            return "f:/adminjsps/login.jsp";
        } else if (judge.usernameAndPassword(admin.getUsername(), admin.getPassword()) == 1) {
            request.getSession().setAttribute("msg", "用户名或密码错误");
            return "f:/adminjsps/login.jsp";
        } else {
            request.getSession().setAttribute("username", admin.getUsername());
            request.getSession().setAttribute("password", admin.getPassword());
            request.getSession().removeAttribute("msg");
            return "r:/adminjsps/admin/index.jsp";

        }

    }


    //    图书分类管理    遍历查询全部图书分类
    public String findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("adminCategoryAll", adminService.findAll());

        return "f:/adminjsps/admin/category/list.jsp";
    }

    //    修改图书分类
    public String revise(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        adminService.revise(request.getParameter("cname"), request.getParameter("cid"));
        request.setAttribute("adminCategoryAll", adminService.findAll());

        return "f:/adminjsps/admin/category/list.jsp";
    }

    //    删除图书分类    (给 del 变成 0)
    public String del(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        adminService.del(request.getParameter("cid"));
        request.setAttribute("adminCategoryAll", adminService.findAll());

        return "f:/adminjsps/admin/category/list.jsp";
    }

    //    添加图书分类
    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        adminService.add(request.getParameter("cname"), CommonUtils.uuid());
        request.setAttribute("adminCategoryAll", adminService.findAll());

        return "f:/adminjsps/admin/category/list.jsp";
    }


    private BookService bookService = new BookServiceImpl();

    //    查看所有图书
    public String findBookAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("all", bookService.findAll());

        return "f:/adminjsps/admin/book/list.jsp";

    }

    //    修改图书信息
    public String chooseBook(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Book book = bookService.load(request.getParameter("bid"));
        request.setAttribute("adminCategoryAll", adminService.findAll());
        request.setAttribute("chooseBook", book);

        return "f:/adminjsps/admin/book/desc.jsp";

    }

    //    删除该本图书信息
    public String delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        bookService.delete(request.getParameter("bid"));


        request.setAttribute("all", bookService.findAll());

        return "f:/adminjsps/admin/book/list.jsp";

    }

    //    修改这本信息图书
    public String mod(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        bookService.mod(CommonUtils.toBean(request.getParameterMap(), Book.class));


        request.setAttribute("all", bookService.findAll());

        return "f:/adminjsps/admin/book/list.jsp";

    }


    //    添加新图书
    public String addBook(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        上传图片文件
        ChoosePicture picture = new ChoosePicture();
//
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

        ServletFileUpload sfu = new ServletFileUpload(diskFileItemFactory);

        try {

            List<FileItem> fileItems = sfu.parseRequest(request);

            FileItem fi = fileItems.get(1);

//            System.out.println("文件表单项演示: ");
//            System.out.println("content-Type: " + fi2.getContentType());
//            System.out.println("fileName: " + fi2.getName());
//            System.out.println("size: " + fi2.getSize());

//            获取文件名字
            String filename = fi.getName();

            if (!filename.equals("")) {

//            保存文件
//                File destFile = new File("/Users/dllo/Desktop/" + filename);
//                fi.write(destFile);

                String rootName = this.getServletContext().getRealPath("/book_img/");

                String imageFile = rootName + filename;

                System.out.println(imageFile);

                File destFile = new File(imageFile);
                fi.write(destFile);

//            存入 book_img 中
                picture.resizeImage(imageFile,
                        imageFile, 108, 150);

//                InputStream resourceAsStream = this.getServletContext().getResourceAsStream("/book_img/");
//
//                String s = resourceAsStream.toString();
//
//                System.out.println(s);

                String oldFile = imageFile.replace("out/artifacts/JD0703_BookStore_war_exploded", "web");

//                System.out.println(OldFile);

                FileUtils.copyFile(destFile, new File(oldFile));

//                picture.resizeImage(imageFile,
//                        "/Users/dllo/Desktop/java/JD0703_BookStore/web/book_img/" + filename, 108, 150);

//            同时 存入 out 文件夹 给当前运行的程序获取
//                picture.resizeImage("/Users/dllo/Desktop/" + filename,
//                        "/Users/dllo/Desktop/java/JD0703_BookStore/out/artifacts/JD0703_BookStore_war_exploded/book_img/" + filename, 108, 150);

//                destFile.delete();
            }

//            System.out.println(Objects.equals(fileItems.get(0).getString("UTF-8"), ""));
//            System.out.println(Objects.equals(fileItems.get(1).getString("UTF-8"), ""));
//            System.out.println(Objects.equals(fileItems.get(2).getString("UTF-8"), ""));
//            System.out.println(Objects.equals(fileItems.get(3).getString("UTF-8"), ""));
//            System.out.println(Objects.equals(fileItems.get(4).getString("UTF-8"), ""));

//            判断信息是否填写完整
            if (fileItems.get(0).getString("UTF-8").equals("") || fileItems.get(4).getString("UTF-8").equals("") ||
                    fileItems.get(2).getString("UTF-8").equals("") || fileItems.get(3).getString("UTF-8").equals("")) {

                request.setAttribute("msg", "信息没有填写完整");
                return "f:/adminjsps/admin/book/add.jsp";


//            System.out.println("普通表单项演示: " + fi1.getFieldName() + ": " + fi1.getString("UTF-8"));
//            FileItem fi3 = fileItems.get(2);
//            System.out.println(fi3.getString("UTF-8"));


            } else {

                Book book = new Book();

//            给 book 类的所有 set属性 赋值
                book.setBid(CommonUtils.uuid());
                book.setBname(fileItems.get(0).getString("UTF-8"));
                book.setImage("book_img/" + filename);
                book.setPrice(Double.parseDouble(fileItems.get(2).getString("UTF-8")));
                book.setAuthor(fileItems.get(3).getString("UTF-8"));
                book.setCid(fileItems.get(4).getString("UTF-8"));
                adminService.addBook(book);

            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        request.setAttribute("all", bookService.findAll());

        return "f:/adminjsps/admin/book/list.jsp";

    }

    //    订单查询模块
//    查询全部订单
    public String findAllOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderService orderService = new OrderService();

        List<Orders> orders = adminService.findAllOrder();

        List<CountAndBooks> bookShow = new LinkedList();

        for (Orders o : orders) {

            List<CountAndBooks> list = orderService.toOid(o.getOid());

            for (CountAndBooks l : list) {

                bookShow.add(l);

            }

        }

//        System.out.println(adminService.findAllOrder());

        request.setAttribute("orders", orders);

        request.setAttribute("bookShow", bookShow);


//        request.setAttribute("all", bookService.findAll());
//
        return "f:/adminjsps/admin/order/list.jsp";

    }

    //    查询不同状态的订单
    public String findAnyOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderService orderService = new OrderService();

        List<Orders> orders = adminService.findAnyOrder(request.getParameter("state"));

        List<CountAndBooks> bookShow = new LinkedList();

        for (Orders o : orders) {

            List<CountAndBooks> list = orderService.toOid(o.getOid());

            for (CountAndBooks l : list) {

                bookShow.add(l);

            }

        }

//        System.out.println(adminService.findAllOrder());

        request.setAttribute("orders", orders);

        request.setAttribute("bookShow", bookShow);


//        request.setAttribute("all", bookService.findAll());
//
        return "f:/adminjsps/admin/order/list.jsp";

    }


//    修改订单状态为已发货
    public String sendAny(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String oid = request.getParameter("oid");

        adminService.sendAny(oid);

        Orders orders = new OrderService().oidToThis(oid);

        String uid = orders.getUid();

        request.setAttribute("address", orders.getAddress());

        request.setAttribute("user", new UserServiceImpl().uidToAll(uid));

        return "f:/adminjsps/admin/order/userAll.jsp";

    }


}
