/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/
"user strict";

$( document ).ready(function() {
    //order list
    function getOrderList(){
        $.get("http://localhost:8080/goldendomemarket/api/order?session="+getCookie('accountType')+"&user="+getCookie('user'), function(data) {
                let count=1;
                $("#data").empty();
                let value= JSON.parse(data);
                if(value.length>0){
                    value.forEach(d => {
                        $(".loader").hide();
                        $("#data").append("<tr>"+ 
                                "<th scope='row'>"+count+" </td>"+
                                "<td>"+d.account.fullName+" </td>"+
                                "<td>"+d.account.email+" </td>"+
                                "<td>"+d.item.itemName+" </td>"+
                                "<td>"+d.orderDate+" </td>"+
                                "<td>"+d.amount+" </td>"+
                                "<td>$"+parseFloat(d.item.itemPrice)*parseInt(d.amount)+"</td>"+
                                "<td>"+d.status+" </td>"+
                                "<td><button type='button' onclick='deleteModal(\""+d.orderId+"\",\""+d.account.fullName+"\",\""+d.item.itemName+"\")' class='btn btn-outline-danger' >Delete</button></td>"+
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
    getOrderList();
    $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#data tr").filter(function() {
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
      });
    
    $("#deletOrderForm").submit(function(event) {
        event.preventDefault();
        let datad = {
            "orderId": $("#dorderId").val()
        }
        $.ajax({
            type: "DELETE",
            url: 'http://localhost:8080/goldendomemarket/api/order',
            dataType: 'json',//json
            contentType: 'application/x-www-form-urlencoded',
            data: JSON.stringify(datad),
            success: function () {
                $('#deleteOrderModal').modal('hide');    
                $("#successalert").show();
                $("#failalert").hide();      
                getOrderList();   
            }
        }).fail(function () {
            $('#deleteOrderModal').modal('hide');    
            $("#successalert").hide();
            $("#failalert").show(); 
        });
    });
});

function deleteModal(orderId,customer,item){
    $('#dorderId').val(orderId);
    $("#dcustomer").val(customer);
    $("#ditem").val(item)
    $('#deleteOrderModal').modal('show');
}
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }
