/** 
* @author Anene Terefe
* @author Hanna Workneh
* created April, 2020
*/
"user strict";

$( document ).ready(function() {
    //order list
    function getItemList(){
        $.get("http://localhost:8080/goldendomemarket/api/item?session="+Cookies.get('accountType')+"&user="+Cookies.get('user'), function(data) {
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
                                "<td><button type='button' onclick='changeItem(\""+d.itemId+"\",\""+d.itemName+"\",\""+d.itemPrice+"\",\""+d.itemDescription+" \");' class='btn btn-outline-primary' >Edit</button></td>"+
                                "<td><button type='button' onclick='deleteModal(\""+d.itemId+"\",\""+d.itemName+"\",\""+d.itemPrice+"\",\""+d.itemDescription+" \");' class='btn btn-outline-danger' >Delete</button></td> </tr>");
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

    $("#addItemForm").submit(function(event) {
        event.preventDefault();
        let datad = {
            "itemName": $("#aitemName").val(),
            "itemPrice": $("#aprice").val(),
            "itemDescription": $("#adescription").val()
        }
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/goldendomemarket/api/item',
            dataType: 'json',//json
            contentType: 'application/x-www-form-urlencoded',
            data: JSON.stringify(datad),
            success: function () {
                $('#addItemModal').modal('hide');    
                $("#successalert").show();
                $("#failalert").hide();   
                $("#aitemName").val("");
                $("#aprice").val("");
                $("#adescription").val("");
                getItemList();
      
            }
        }).fail(function () {
            $('#addItemModal').modal('hide');    
            $("#successalert").hide();
            $("#failalert").show(); 
        });
    });

    $("#editItemForm").submit(function(event) {
        event.preventDefault();
        let datad = {
            "itemId": $("#itemId").val(),
            "itemName": $("#itemName").val(),
            "itemPrice": $("#price").val(),
            "itemDescription": $("#description").val()
        }
        $.ajax({
            type: "PUT",
            url: 'http://localhost:8080/goldendomemarket/api/item',
            dataType: 'json',//json
            contentType: 'application/x-www-form-urlencoded',
            data: JSON.stringify(datad),
            success: function () {
                $('#editItemModal').modal('hide');    
                $("#successalert").show();
                $("#failalert").hide();    
                getItemList();
     
            }
        }).fail(function () {
            $('#editItemModal').modal('hide');    
            $("#successalert").hide();
            $("#failalert").show(); 
        });
    });
    $("#deletItemForm").submit(function(event) {
        event.preventDefault();
        let datad = {
            "itemId": $("#ditemId").val()
        }
        $.ajax({
            type: "DELETE",
            url: 'http://localhost:8080/goldendomemarket/api/item',
            dataType: 'json',//json
            contentType: 'application/x-www-form-urlencoded',
            data: JSON.stringify(datad),
            success: function () {
                $('#deleteItemModal').modal('hide');    
                $("#successalert").show();
                $("#failalert").hide();        
                getItemList();
 
            }
        }).fail(function () {
            $('#deleteItemModal').modal('hide');    
            $("#successalert").hide();
            $("#failalert").show(); 
        });
    });
});
function changeItem(itemid,name,price,dec){
    $("#itemId").val(itemid);
    $("#itemName").val(name);
    $("#price").val(price);
    $("#description").val(dec);
    $('#editItemModal').modal('show');
}
function deleteModal(itemid,name,price,dec){
    $("#ditemId").val(itemid);
    $("#ditemName").val(name);
    $("#dprice").val(price);
    $("#ddescription").val(dec);
    $('#deleteItemModal').modal('show');
}
