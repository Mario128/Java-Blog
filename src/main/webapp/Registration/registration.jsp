<section>
    <form id="regform" method = "post" action ="/register">
        <p id="regerror"></p>
        <table class ="table">
            <tr>
                <td><label for="regfirstname">Firstname</label> </td>
                <td><input id="regfirstname" class="form-control" type="text" name="Firstname"></td>
            </tr>
            <tr>
                <td><label for="regemail">Email</label></td>
                <td><input id="regemail" class="form-control" type="text" name="Email"></td>
            </tr>
            <tr>
                <td><label for="regusername">Username</label></td>
                <td><input id="regusername" class="form-control" type="text" name="Username"></td>
            </tr>
            <tr>
                <td><label for ="regpassword">Password</label></td>
                <td><input id="regpassword" class="form-control" type="password" name="Password"></td>
            </tr>
            <tr>
                <td><input class="btn btn-primary" type ="submit"></td>
                <td><input class="btn btn-primary" type="reset"></td>
            </tr>
            <p><%String message = (String)request.getAttribute("RegistrationMessage");
                if(message != null) {
                    out.append("<p class='text-danger'>"+message+"</p>");
                }
            %>
            </p>
        </table>
    </form>
</section>
