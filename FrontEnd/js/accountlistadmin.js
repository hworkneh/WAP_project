/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/ 
"user strict";

$( document ).ready(function() {
    //order list
    function getAccountList(){
        $.get("http://localhost:8080/goldendomemarket/api/account?session="+Cookies.get('accountType')+"&user="+Cookies.get('user'), function(data) {
                let count=1;
                $("#data").empty();
                let value= JSON.parse(data);
                if(value.length>0){
                    value.forEach(d => {
                        $(".loader").hide();
                        $("#data").append("<tr>"+ 
                                "<th scope='row'>"+count+" </td>"+
                                "<td>"+d.fullName+" </td>"+
                                "<td>"+d.email+" </td>"+
                                "<td>"+d.accountType+" </td>"+
                                "<td><button type='button' onclick='deleteModal(\""+d.accountId+"\",\""+d.fullName+"\",\""+d.email+"\")' class='btn btn-outline-danger' >Delete</button></td>"+
                                "</tr>"
                            );
                        count++;
                    });
                }else{
                    $(".loader").hide();
                    $("#data").html("No data available");
                }

            }).fail(function() {
                $("#data").append("<tr colspan='8'>Unable To fetch data</tr>");
            });
    }
    getAccountList();

    $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#data tr").filter(function() {
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
      });


    $("#addAccountForm").submit(function(event) {
        event.preventDefault();
        let datad = {
            "fullName": $("#afullname").val(),
            "email": $("#aemail").val(),
            "accountType": $("#arole").val(),
            "password": $("#apassword").val()
        }
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/goldendomemarket/api/account',
            dataType: 'json',//json
            contentType: 'application/x-www-form-urlencoded',
            data: JSON.stringify(datad),
            success: function () {
                $("#afullname").val("");
                $("#aemail").val("");
                $("#arole").val("");
                $("#apassword").val("");
                $('#addAccountModal').modal('hide');    
                $("#successalert").show();
                $("#failalert").hide();         
                getAccountList();
            }
        }).fail(function () {
            $('#addAccountModal').modal('hide');    
            $("#successalert").hide();
            $("#failalert").show(); 
        });
    });
    $("#deleteAccountForm").submit(function(event) {
        event.preventDefault();
        let datad = {
            "accountId": $("#daccountId").val()
        }
        $.ajax({
            type: "DELETE",
            url: 'http://localhost:8080/goldendomemarket/api/account',
            dataType: 'json',//json
            contentType: 'application/x-www-form-urlencoded',
            data: JSON.stringify(datad),
            success: function () {
                $('#deleteAccountModal').modal('hide');    
                $("#successalert").show();
                $("#failalert").hide();   
                getAccountList();      
            }
        }).fail(function () {
            $('#deleteAccountModal').modal('hide');    
            $("#successalert").hide();
            $("#failalert").show(); 
        });
    });
});

function deleteModal(accountid,name,email){
    $("#daccountId").val(accountid);
    $("#dfullname").val(name);
    $("#demail").val(email);
    $('#deleteAccountModal').modal('show');
}
