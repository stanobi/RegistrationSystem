<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Registration form</title>
</head>
<body>
<div align="center" style="margin-top: 50px">
	<h2> Developer Skill Form</h2>
	<br>
	<form action="Registration" method="post">
		<p>
			<label>First Name : </label> 
			<input type="text" name="firsName" required="required">
		</p>
		<p>
			<label>Last Name : </label> 
			<input type="text" name="lastName" required="required">
		</p>
		<p>
		<label>Select skills : </label> 
		<select name="skills" multiple="multiple">
			<option value="CSharp" >C#</option>
			<option value="Java">Java</option>
			<option value="PHP">PHP</option>
			<option value="C">C</option>
			<option value="Objective C">Objective C</option>
		</select>
		</p>
		<input type="submit" value="Submit"><br><br>
	</form>
	<form action="RegistrationSummary" method="post">
		<input type="text" name="report" value="Summary" hidden="">
		<input type="submit" value="Get Summary"><br><br>
	</form>
</div>
</body>
</html>

<script type="text/javascript">

$(document).ready(function() {
	$.ajax({
		url : '/Registration',
		data : {
			operation : "PopulateDropDown"
		},
		success : function(responseText) {
	        for(var i in responseText)
	        {
	            var x = document.getElementById("skills");
	            var option = document.createElement("option");
	            option.text = data[i];
	            option.value = data[i];
	            x.add(option);
	        }
		}
	});
});

</script>
