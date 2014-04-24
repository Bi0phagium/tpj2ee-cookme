<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="myUser" scope="session"
		class="step4.model.UserModelBean"></jsp:useBean>

	<jsp:setProperty name="myUser" property="name" />
	<jsp:setProperty name="myUser" property="surname" />
	<jsp:setProperty name="myUser" property="age" />
	<jsp:setProperty name="myUser" property="mail" />
	<jsp:setProperty name="myUser" property="login" />
	<jsp:setProperty name="myUser" property="pwd" />
</head>

<body>
	<form id="form" method="post" action="inscription.html">

		<fieldset>
			<legend>Registration</legend>

			<div class="inscriptionForm">
				<label class="inscription">First name :</label> <input
					class="inscription" name="name"
					<jsp:getProperty name="myUser" property="name" /> />
			</div>

			<div class="inscriptionForm">
				<label class="inscription">Last name :</label> <input
					class="inscription" name="surname"
					<jsp:getProperty name="myUser" property="surname" /> />
			</div>

			<div class="inscriptionForm">
				<label class="inscription">Age :</label> <input class="inscription"
					name="age" <jsp:getProperty name="myUser" property="age" /> />
			</div>

			<div class="inscriptionForm">
				<label class="inscription">Mail address :</label> <input
					class="inscription" name="mail"
					<jsp:getProperty name="myUser" property="mail" /> />
			</div>

			<div class="inscriptionForm">
				<label class="inscription">Login :</label> <input
					class="inscription" name="login"
					<jsp:getProperty name="myUser" property="login" /> />
			</div>

			<div class="inscriptionForm">
				<label class="inscription">Password :</label> <input
					class="inscription" name="pwd" type="password"
					<jsp:getProperty name="myUser" property="pwd" /> />
			</div>

			<div class="inscriptionForm">
				<label class="inscription">Password :</label> <input
					class="inscription" name="pwd" type="password"
					<jsp:getProperty name="myUser" property="pwd" /> />
			</div>

			<div class="inscriptionForm"></div>

			<input type="reset" value="Reset" /> <input class="inscriptionSub"
				type="submit" value="Register" />

		</fieldset>

	</form>
	<a href="displayUserBean.jsp">Display Data</a>
</body>


</html>