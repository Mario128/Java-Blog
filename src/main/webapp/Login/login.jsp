<section>
<form id="logform" method="post" action="/login" >
    <%
        String msg=(String)request.getAttribute("meldung");
        request.removeAttribute("meldung");
        if(msg!=null){
            out.append("<p class='text-danger'>"+msg+"</p>");
        }
    %>
    <p id="logerror"></p>
    <p name ="Message" id="Message"></p>
    <table class="table">
        <tr>
            <td><label for = "logusername" class="from-control">Username</label></td>
            <td><input class="form-control" name="user" id="logusername" type="text"></td>
        </tr>
        <tr>
            <td><label for ="logpassword">Password</label></td>
            <td><input class="form-control" name="pwd" id="logpassword" type="password" ></td>
        </tr>
        <tr>
            <td><input type="submit" class="btn btn-primary"></td>
            <td><input type="reset" class = "btn btn-primary"></td>
        </tr>
    </table>
</form>
</section>
