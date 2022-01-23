$(document).ready(()=>{
    $('.minBtn').on("click",function(event){
        event.preventDefault();
        productId = $(this).attr("prodid");
        amountInput = $("#amount"+productId);
        newValue = parseInt(amountInput.val())-1;
        if (newValue > 0) amountInput.val(newValue);
    });
    $('.plusBtn').on("click",function (event){
        event.preventDefault();
        productId = $(this).attr("prodid");
        amountInput = $("#amount"+productId);
        newValue = parseInt(amountInput.val())+1;
        if (newValue < 6) amountInput.val(newValue);
    });
});