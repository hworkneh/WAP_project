/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/
"user strict";

$( document ).ready(function() {
    //order list
    function getOrderList(){
        $.get("http://localhost:8080/goldendomemarket/api/order?session="+Cookies.get('accountType')+"&user="+Cookies.get('user'), function(data) {
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
                                "<td>"+parseFloat(d.item.itemPrice)*parseInt(d.amount)+"$</td>"+
                                "<td>"+d.status+" </td>"+
                                "<td><button type='button' onclick='changeOrderStatus(\""+d.orderId+"\",\""+d.status+"\",\""+d.account.fullName+"\",\""+d.item.itemName+"\")' class='btn btn-outline-primary' >Change Status</button></td>"+
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

    $("#changeStatusForm").submit(function(event) {
        event.preventDefault();
        let datad = {
            "orderId": $("#orderId").val(),
            "status": $("#editstatus").val()
        }
        $.ajax({
            type: "PUT",
            url: 'http://localhost:8080/goldendomemarket/api/order',
            dataType: 'json',//json
            contentType: 'application/x-www-form-urlencoded',
            data: JSON.stringify(datad),
            success: function () {
                $('#changeStatusModal').modal('hide');    
                $("#successalert").show();
                $("#failalert").hide();      
                getOrderList();   
            }
        }).fail(function () {
            $('#changeStatusModal').modal('hide');    
            $("#successalert").hide();
            $("#failalert").show(); 
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
function changeOrderStatus(orderId,status,customer,item){
    $('#orderId').val(orderId);
    $("#editstatus").val(status);
    $("#customer").val(customer);
    $("#item").val(item)
    $('#changeStatusModal').modal('show');
}
function deleteModal(orderId,customer,item){
    $('#dorderId').val(orderId);
    $("#dcustomer").val(customer);
    $("#ditem").val(item)
    $('#deleteOrderModal').modal('show');
}
