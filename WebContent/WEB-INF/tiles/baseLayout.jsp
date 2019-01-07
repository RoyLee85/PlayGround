<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Do+Hyeon" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cute+Font|Do+Hyeon" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cute+Font|Do+Hyeon|Nanum+Gothic" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <meta charset="UTF-8">
    <title>제목</title>
    
   
    <style>
        #header{            
            width:100%;
            height:80px;
            text-align: center;
            background-color: white;

        }
        
        #header p{
        	display: inline;
        	font-family:  'Do Hyeon', sans-serif;
        	font-size: 50px;
        }
        
        #header ul {
        	position : relative;
     		top : 28px;
        }
         
        #header ul li{
        	display: inline; 
			float : right;
			list-style : none;
        	font : bold 12px;
        	padding : 0 20px;
        	
        }
         
        #header a{
        	text-decoration: none;
        	font : bold 12px;
        	color: black;
        }
        
        #category{            
            text-align: center;
            background-color: white;
            border-top: 1px solid;
            border-top-color: #DCD8D8;
            border-bottom: 1px solid;
            border-bottom-color: #DCD8D8;

        }  
        
       	#category ul li{
        	display: inline; 
			list-style : none;
        	padding : 0 20px;
			font-size: 28px;
        	
        }
        
        #category a{
        	text-decoration: none;
        	font-family: 'Cute Font', cursive;
        	
        	color: black;
        }
        
        #footer1{
            width: 100%;
            height: 200px;
            border-top: 1px solid;
            border-top-color: #DCD8D8;
            border-bottom: 1px solid;
            border-bottom-color: #DCD8D8;   
        }
        
        #footer1 p{
        	display: inline;
        	font-size : 18px;
        	font-family: 'Nanum Gothic', sans-serif;
        	font-weight: bold;
        	text-align: right;
        }
		
		#footer1 ul{
			padding-left: 250px;
			padding-right: 250px;
			display : inline-table;
			text-align: center;
			padding-left: 0px;
			text-align: right;
		}

        #footer1 ul li{
			display : table;
			list-style : none;
			padding-bottom: 10px;
        	font : bold 12px;
        	text-align: right;
        }
        
        #footer1 a{
        	text-decoration: none;
        	font : bold 12px;
        	color: black;
        	
        }
        
        .flex-container {
			display: flex;
			justify-content: center;
		}
		  
    </style>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
 <div style="width:100%; height:100%;">
    <div id="header"><tiles:insertAttribute name="header" /></div>
    <div id="category"><tiles:insertAttribute name="category" /></div>
    <div id="content"><tiles:insertAttribute name="content" /></div>
    <div id="footer1"><tiles:insertAttribute name="footer1" /></div>
    <div id="footer2"><tiles:insertAttribute name="footer2" /></div>
    </div>
 
    <script type="text/javascript">
        $(function() {
 
        });    
    </script>    
</body>
</html>