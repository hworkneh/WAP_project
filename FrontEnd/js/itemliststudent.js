/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/
"user strict";

$( document ).ready(function() {
    //item list
    function getItemList(){
        $.get("http://localhost:8080/goldendomemarket/api/item", function(data) {
                let count=1;
                $("#data").empty();
                let value= JSON.parse(data);
                if(value.length>0){
                    value.forEach(d => {
                        $(".loader").hide();
                        $("#data").append("<tr>"+ 
                                "<th scope='row'>"+count+" </td>"+
                                "<td>"+d.itemName+" </td>"+
                                "<td>"+d.itemPrice+" </td>"+
                                "<td>"+d.itemDescription+" </td>"+
                                "<td><button type='button' onclick='orderItem(\""+d.itemId+"\",\""+d.itemName+"\",\""+d.itemPrice+"\",\""+d.itemDescription+"\")' class='btn btn-outline-success' >Order</button></td>"+
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
    getItemList();
    $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#data tr").filter(function() {
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
      });

    $("#orderItemForm").submit(function(event) {
        event.preventDefault();
        let day=new Date();
        let datad = {
            "account":{
                "accountId":getCookie("user")
            },
            "item":{
                "itemId":$("#itemId").val()
            },
            "orderDate": day.toLocaleDateString("en-US"),
            "status": "Ordered",
            "amount": $("#amount").val()
        }
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/goldendomemarket/api/order',
            dataType: 'json',//json
            contentType: 'application/x-www-form-urlencoded',
            data: JSON.stringify(datad),
            success: function () {
                $('#orderItemModal').modal('hide');    
                $("#successalert").show();
                $("#failalert").hide();         
            }
        }).fail(function () {
            $('#orderItemModal').modal('hide');    
            $("#successalert").hide();
            $("#failalert").show(); 
        });
    });
});
function orderItem(itemid,name,price,dec){
    $("#itemId").val(itemid);
    $("#itemName").val(name);
    $("#price").val(price);
    $("#description").val(dec);
    $('#orderItemModal').modal('show');
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
