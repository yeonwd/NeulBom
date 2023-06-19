<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
   <header id="header">
       <div class="container" style="margin-right:0px; margin-left:0px; max-width:100%; padding-right:100px; padding-left:50px; margin-bottom: 20px; border-bottom: 1px solid #A6AEB7; padding-bottom:20px;">
        <div class="userinfo_wj">
            <div class="userinfo_item">
               <c:if test="${sessionScope.id == null}">
                      <button type="button" onclick="location.href='http://localhost:8090/neulbom/client/account/login.do'" class="btn btn-primary btn-sm" style="width:100px; height:40px; margin-right:10px; font-size: 20px;">로그인</button>
                      <button type="button" class="btn btn-secondary btn-sm btn-light" style="width:100px; height:40px; font-size: 20px;" onclick="location.href='/neulbom/client/register/register.do'">회원가입</button>
                   </c:if>
                   <c:if test="${sessionScope.id != null}">
                      ${sessionScope.name} (${sessionScope.id})님 환영합니다.
                      <button type="button" onclick="location.href='http://localhost:8090/neulbom/client/mypage/mypage_payment_details.do'" class="btn btn-primary btn-sm" style="width:120px; height:40px; font-size: 20px;">마이 페이지</button>
                      <button type="button" onclick="location.href='http://localhost:8090/neulbom/client/account/logout.do'" class="btn btn-secondary btn-sm btn-light" style="width:100px; height:40px; font-size: 20px;">로그아웃</button>
                   </c:if>
               
            </div>
        </div>
        <header class="d-flex flex-wrap justify-content-center py-3 mb-4 
        " style="margin-top: 15px; font-size: 20px; margin-bottom:0px !important; padding-bottom:0px !important; ">
            <a href='/neulbom/client/index.do'
                class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                <svg class="bi me-2" width="40" height="32">
                    <use xlink:href="#bootstrap"></use>
                </svg>
                <div style="width:300px; display: flex;">
                    <span style="display: flex; align-items: center;">
                        <img src="/neulbom/asset/logo/clover.png" style="width:40px; height:40px; margin-right:10px;">
                        <h1 style="display: inline; margin: 0;">늘봄</h1>
                    </span>
                </div>
            </a>
            <ul class="nav nav-pills">
               <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                        aria-expanded="false">늘봄소개</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/neulbom/client/board/greetings.do">인사말</a></li>
                        <li><a class="dropdown-item" href="/neulbom/client/board/neulbomintroduce.do">시설 소개</a></li>
                        <li><a class="dropdown-item" href="/neulbom/client/board/directions.do">오시는 길</a></li>
                    </ul>
                </li>
                <!-- 가는 곳 없음 -->
                <li class="nav-item"><a href="/neulbom/client/introduce.do"
                        class="nav-link">입주안내</a></li>
                 <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="/neulbom/client/board/notice.do" role="button"
                        aria-expanded="false">알림게시판</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/neulbom/client/board/notice.do">공지사항</a></li>
                        <li><a class="dropdown-item" href="/neulbom/client/board/food.do">식단표</a></li>
                        <li><a class="dropdown-item" href="/neulbom/client/board/life.do">생활</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="/neulbom/client/board/resiconsult.do" role="button"
                        aria-expanded="false">커뮤니티</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/neulbom/client/board/resiconsult.do">입주상담게시판</a></li>
                        <li><a class="dropdown-item" href="/neulbom/client/board/qna.do">문의게시판</a></li>
                        <li><a class="dropdown-item" href="/neulbom/client/community/free.do">자유게시판</a></li>
                    </ul>
                </li>
               

            </ul>
        </header>
    </div>
   </header>
