$(document).ready(()=>{
    console.log("ready")

    $("#addCartBtn").on("click",function (event){
        event.preventDefault()

        addToTheCart()
    })
});

function addToTheCart(){
    amount = $("#quantity"+productId).val.length
    console.log(amount)
    url = contextPath + "/cart/add/"+productId+"/"+amount;

    $.ajax({
        type:"POST",
        url:url,
        beforeSend: function (xhr){
            xhr.setRequestHeader(csrfHeaderName,csrfValue);
        }
    }).done(function (response){
        alert(response);
    }).fail(function (){
        alert("error");
    })
}