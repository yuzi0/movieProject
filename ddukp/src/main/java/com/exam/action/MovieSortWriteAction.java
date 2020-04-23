package com.exam.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.exam.model.BoardDAO;
import com.exam.model.ColumnBoardDAO;
import com.exam.model.ColumnBoardTO;
import com.exam.model.MovieDAO;
import com.exam.model.MovieSortTO;
import com.exam.model.latterBoardTO;

public class MovieSortWriteAction implements Action {
   private static ArrayList<MovieSortTO> msortlist = new ArrayList<MovieSortTO>();

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) {
      System.out.println("MovieSortWriteAction 호출");
      // TODO Auto-generated method stub
      int flag = 0;

      String url = "https://series.naver.com/movie/categoryList.nhn?categoryCode=ALL";
      Document doc = null;

      try {
         doc = Jsoup.connect(url).get();

         Elements ele = doc.select("div.total");
         int totalPage = 0;
         if (Integer.parseInt(ele.text().replaceAll("[^0-9]", "")) / 25 == 0) {
            totalPage = Integer.parseInt(ele.text().replaceAll("[^0-9]", "")) / 25;
         } else
            totalPage = Integer.parseInt(ele.text().replaceAll("[^0-9]", "")) / 25 + 1;
         System.out.println("total : " + totalPage);
         // for(int i=1;i<=totalPage;i++) {
         for (int i = 1; i <= 5; i++) {
            System.out.println(i + "페이지=============================");
            movieinfo(i);
            System.out.println("====================================");
         }
         MovieDAO dao = new MovieDAO();
         flag = dao.movieSortWrite(msortlist);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      request.setAttribute("flag", flag);
   }

   public static void movieinfo(int pageNum) {
      String url = "https://series.naver.com/movie/categoryList.nhn?categoryCode=ALL&page=" + pageNum;
      Document doc = null;
      int flag = 0;
      try {
         doc = Jsoup.connect(url).get();

         String check_subject = "";

         Elements ele = doc.select("ul.lst_thum.v1");
         for (Element el : ele.select("li")) {
            if (!el.select("a").select("strong").text().contains("19")) {
               String subject = el.select("a").attr("title");
               // 괄호 이후 삭제
               if(subject.indexOf("(") != -1) {
                  subject = subject.substring(0, subject.indexOf("("));
               } 
               // 이전 영화와 이름이 같은지 비교
               if(subject.equals(check_subject) != true) {
                  MovieSortTO msTO = new MovieSortTO();
                  msTO.setMsubject(subject);
                  msTO.setScore(el.select(".score_num").text());
                  msTO.setImg(el.select("img").attr("src"));
                  System.out.println("제목 : " + el.select("a").attr("title"));
                  String durl = "https://series.naver.com" + el.select("a").attr("href");
                  Document d = Jsoup.connect(durl).get();
                  Elements ele2 = d.select("ul.end_info");
                  msTO.setGenre(ele2.select("a").first().text());
                  msortlist.add(msTO);
               } 
               check_subject = subject;
            }
         }

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

}