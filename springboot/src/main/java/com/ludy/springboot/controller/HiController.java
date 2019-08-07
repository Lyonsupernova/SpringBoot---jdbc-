package com.ludy.springboot.controller;

import com.ludy.springboot.Dao.ProjectInfoDao;
import com.ludy.springboot.pojo.ProjectInfo;

import net.sf.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/hi/")
public class HiController {
    @Autowired
    private ProjectInfoDao piDao;
    public static org.apache.log4j.Logger lo = org.apache.log4j.Logger.getLogger(HiController.class);

    @ResponseBody
    @RequestMapping(value = "handleRequest", produces = {"text/plain;charset=UTF-8"})
    public void handleRequest(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        List<ProjectInfo> projectInfoList = piDao.selectTable();
        response.setCharacterEncoding("UTF-8");
        lo.info("Session started successfully, log...1...2...3");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(JSONArray.fromObject(projectInfoList).toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        lo.info("1...2...3...ready all the time");

        // 按时备份文件(定时器每五分钟备份文件一次
        Runnable runnable = new Runnable() {
            public void run() {
                // task to run goes here
                try {
                    File srcFile = new File("D:\\logs\\log.log");
                    File dstDirec = new File("D:\\test");
                    FileUtils.copyFileToDirectory(srcFile, dstDirec, false);
                    System.out.println("File moved successfully!");
                } catch (IOException e) {
                    System.out.println("Failed");
                    e.printStackTrace();
                }
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 0, 300, TimeUnit.SECONDS);

        runnable = new Runnable() {
            public void run() {
                // task to run goes here
                lo.info("1...2...3.....30sec have passed");
            }
        };
        service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 10, 50, TimeUnit.SECONDS);


    }


}
