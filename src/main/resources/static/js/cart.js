$(document).ready(()=>{
    console.log("ready")
    $('.minBtn').on("click",function(event){
        event.preventDefault();
        lowerAmount($(this))
    });
    $('.plusBtn').on("click",function (event){
        event.preventDefault();
        upAmount($(this))
    });

    $(".link-remove").on("click",function (event){
       event.preventDefault();
       removeFromCart($(this))
    });

    updateTotal()
});
//zal de item uit de cart halen
function removeFromCart(link){
    url = link.attr("href");

    $.ajax({
        type:"POST",
        url:url,
        beforeSend: function (xhr){
            xhr.setRequestHeader(csrfHeaderName,csrfValue);
        }
    }).done(function (response){
        console.log('hello')
        rowNum = link.attr("rownumber")
        removeProduct(rowNum);
        updateTotal();
        alert(response)
    }).fail(function (){
        alert("error");
    })
}
//zal product uitlijstverwijderen
function removeProduct(rowNumber){
    rowId = "row"+rowNumber;
    $('#'+rowId).remove();
}
//zal het cijfer van het totale hoeveelheid verhogen en de updateAmount functie oproepen
function upAmount(link) {
    productId = link.attr("prodid");
    amountInput = $("#amount" + productId);
    newValue = parseInt(amountInput.val()) + 1;
    if (newValue < 6) {
        amountInput.val(newValue)
        updateAmount(productId, newValue);
    }
}
//zal het cijfer van het totale hoeveelheid verlagen en de updateAmount functie oproepen
function lowerAmount(link){
    productId = link.attr("prodid");
    amountInput = $("#amount"+productId);
    newValue = parseInt(amountInput.val())-1;
    if (newValue > 0) {
        amountInput.val(newValue)
        updateAmount(productId,newValue);
    }
}
//zal het totaal van het product updaten in de database en dan twee functies aanroepen die het subtotaal en totaal prijs zal updaten op de front end
function updateAmount(productId,amount){
    url = contextPath + "/cart/update/"+productId+"/"+amount;

    $.ajax({
        type:"POST",
        url:url,
        beforeSend: function (xhr){
            xhr.setRequestHeader(csrfHeaderName,csrfValue);
        }
    }).done(function (response){
        updateTotalPriceItem(response,productId);
        updateTotal();
    }).fail(function (){
        alert("error");
    })
}
function updateTotalPriceItem(itemPrice,prodId){
    if (itemPrice === "you must login to perform this action."){
        alert(itemPrice);
    }else{

        $("#subTotal"+prodId).text(itemPrice);
    }
}
function updateTotal(){
    let total = 0;
    $(".totalpriceitem").each(function (index,element){
        total = total + parseFloat(element.innerHTML);
    });
    $("#totalamount").text("€"+ total);
}