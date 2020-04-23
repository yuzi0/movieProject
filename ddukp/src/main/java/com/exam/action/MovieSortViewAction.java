package com.exam.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.mail.internet.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.exam.model.MovieDAO;
import com.exam.model.MovieSortTO;
import com.exam.model.UserDAO;
import com.exam.model.UserTO;

public class MovieSortViewAction implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) {
      // TODO Auto-generated method stub
      System.out.println("MovieSortViewAction 호출");

      /*
       * document 클래스 : 연결해서 얻어온 html 전체 문서 element 클래스 : document의 html 요소 elements
       * 클래스 : element가 모인 자료형
       */

      MovieDAO dao = new MovieDAO();
      MovieSortTO to = dao.movieInfo((Integer.parseInt(request.getParameter("movie"))));

      String searchMovie = to.getMsubject();
      if (searchMovie.indexOf("(") != -1) {
         searchMovie = searchMovie.substring(0, searchMovie.indexOf("("));
      }
      request.setAttribute("movie", searchMovie);
      request.setAttribute("score", to.getScore());
      request.setAttribute("img", to.getImg());
      request.setAttribute("movieCategory", request.getParameter("movieCategory"));
      request.setAttribute("cpage", request.getParameter("cpage"));
      String director = "";

      System.out.println("검색 영화 : " + searchMovie);

      String url = "";
      Document doc = null;
      Elements els = null;
      
      try {
         // 네이버 영화 정보 검색
         System.out.println("========네이버 검색 결과========");
         url = "https://series.naver.com/search/search.nhn?t=movie&q=" + searchMovie;

         doc = Jsoup.connect(url).get();
         els = doc.select("div.ts");

         for (Element el : els.select("li")) {
            String title_naver = el.select("a").text();
            // 이름 앞에 이벤트 문구 붙어있으면 자르기
            if (title_naver.length() > "Event ".length()) {
               if ("Event ".equals(title_naver.substring(0, "Event ".length())) == true) {
                  title_naver = title_naver.substring("Event ".length());
               }
            }
            // 영화 이름이 같은지 비교
            if(title_naver.length() >= searchMovie.length()) {
               if (searchMovie.equals(title_naver.substring(0, searchMovie.length())) == true) {
                  String[] info = el.select("p.info").text().split("\\|");
                  String director_naver = info[1].trim().substring(3);
                  if (director == "") {
                     director = director_naver;
                  }
                  if (director == director_naver) {
                     String link_naver = "https://series.naver.com" + el.select("a").attr("href");
                     String buyType_naver = el.select("p.price2").select("em").text();
                     String price_naver = el.select("p.price2").select("strong").text();
                     String star_naver = "";
                     String date_naver = "";
                     String level_naver = "";
                     String summary_naver = "";
                     
                     //if (info.length > 2) { star_naver = info[2].trim().substring(3); }
                     if (info.length > 3) { date_naver = info[3]; }
                     
                     Document doc_detail = Jsoup.connect(link_naver).get();
                     Elements els_detail = doc_detail.select("div.end");
                     for (Element el_detail : els_detail.select("div[id=content]")) {
                        //System.out.println(el_detail.select("li.info_lst").text());
                        //System.out.println(el_detail.select("div.end_dsc").text());
                        // 영화 등급, 주연
                        String info2 = el_detail.select("li.info_lst").text();
                        level_naver = info2.substring(info2.indexOf("등급") + 2, info2.indexOf("장르"));
                        star_naver = info2.substring(info2.indexOf("주연") + 2, info2.indexOf("상세정보"));
                        
                        summary_naver = el_detail.select("div.end_dsc").text();
                        if(summary_naver.indexOf("접기") != -1) {
                           summary_naver = summary_naver.substring(0, summary_naver.indexOf("접기"));
                        }
                     }
                     
                     System.out.println("영화 : " + title_naver);
                     System.out.println("링크 : " + link_naver);
                     System.out.println("가격 : " + buyType_naver + " - " + price_naver);
                     System.out.println("감독 : " + director_naver);
                     System.out.println("주연 : " + star_naver);
                     System.out.println("개봉 날짜 : " + date_naver);
                     System.out.println("================");
   
                     request.setAttribute("link_naver", link_naver);
                     request.setAttribute("buyType_naver", buyType_naver);
                     request.setAttribute("price_naver", price_naver);
                     request.setAttribute("director_naver", director_naver);
                     request.setAttribute("star_naver", star_naver);
                     request.setAttribute("level_naver", level_naver);
                     request.setAttribute("date_naver", date_naver);
                     request.setAttribute("summary_naver", summary_naver);
                  }
               }
               
            }
         }

         // 구글 영화 정보 검색
         System.out.println("=======구글 검색 결과=========");
         url = "https://play.google.com/store/search?q=" + URLEncoder.encode(searchMovie) + "&c=movies&hl=ko";
         doc = Jsoup.connect(url).get();
         els = doc.select("div.Ktdaqe");

         // System.out.println("url : " + url);
         for (Element el : els.select("div.ImZGtf")) {
            String title_google = el.select("div.WsMG1c").attr("title");
            // System.out.println("title : " + title);
            if (title_google.indexOf("(") != -1) {
               title_google = title_google.substring(0, title_google.indexOf("(") - 1);
            }
            // System.out.println("title : " + title);

            // 영화 제목 비교
            if (title_google.equals(searchMovie) == true) {
               // System.out.println(el);
               String link_google = "https://play.google.com" + el.select("div.Q9MA7b").select("a").attr("href");
               String summary_google = el.select("div.f5NCO").select("a").text();
               // String genre_google = el.select("div.KoLSrc").text();
               // String date_google = "";
               String price_buy_google = "";
               String price_rent_google = "";

               Document doc_detail = Jsoup.connect(link_google).get();
               Elements els_detail = doc_detail.select("div.T4LgNb");
               for (Element el_detail : els_detail.select("div.UTg3hd")) {
                  // date_google = el_detail.select("span.UAO9ie").text();
                  // genre_google = el_detail.select("a.R8zArc").text();
                  String price_google = el_detail.select("button.IfEcue").text();
                  //price_google = price_google.substring(1);
                  //System.out.println(price_google);
                  String[] price = price_google.split(" ");
                  for(int i=0; i < price.length; i++) {
                     //System.out.println(i + " : " + price[i]);
                     if (price[i].equals("Buy")) {
                        price_buy_google = price[i - 1].substring(1);
                     } else if (price[i].equals("Rent")) {
                        price_rent_google = price[i - 1].substring(1);
                     }
                  }
               }
               System.out.println("영화 : " + title_google);
               System.out.println("링크 : " + link_google);
               System.out.println("요약 : " + summary_google);
               System.out.println("구매가격 : " + price_buy_google);
               System.out.println("대여가격 : " + price_rent_google);
               // System.out.println("개봉 날짜 : " + price_rent_google);
               // System.out.println("장르 : " + genre_google);
               System.out.println("================");

               request.setAttribute("link_google", link_google);
               request.setAttribute("summary_google", summary_google);
               request.setAttribute("price_buy_google", price_buy_google);
               request.setAttribute("price_rent_google", price_rent_google);

               break;
            }
         }
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

}