$('.veggie-dec').click(function(){
    var label = $('.veggie-label');
    var num = (parseInt(label.text())-1).toString();
    if(num < 0) label.text(0);
    else label.text(num);   
    updateTotal();
});

$('.veggie-inc').click(function(){
    var label = $('.veggie-label');
    var num = (parseInt(label.text())+1).toString();
    label.text(num);   
    updateTotal();
});

$('.chicken-dec').click(function(){
    var label = $('.chicken-label');
    var num = (parseInt(label.text())-1).toString();
    if(num < 0) label.text(0);
    else label.text(num);   
    updateTotal();
});

$('.chicken-inc').click(function(){
    var label = $('.chicken-label');
    var num = (parseInt(label.text())+1).toString();
    label.text(num);
    updateTotal();
});

$('.beef-dec').click(function(){
    var label = $('.beef-label');
    var num = (parseInt(label.text())-1).toString();
    if(num < 0) label.text(0);
    else label.text(num);   
    updateTotal();
});

$('.beef-inc').click(function(){
    var label = $('.beef-label');
    var num = (parseInt(label.text())+1).toString();
    label.text(num);   
    updateTotal();
});

$('.chorizo-dec').click(function(){
    var label = $('.chorizo-label');
    var num = (parseInt(label.text())-1).toString();
    if(num < 0) label.text(0);
    else label.text(num); 
    updateTotal();
});

$('.chorizo-inc').click(function(){
    var label = $('.chorizo-label');
    var num = (parseInt(label.text())+1).toString();
    label.text(num);
    updateTotal();
});

function updateTotal() {
    var Order = new Object();
    Order.veggie = parseInt($('.veggie-label').text());
    Order.chicken = parseInt($('.chicken-label').text());
    Order.beef = parseInt($('.beef-label').text());
    Order.chorizo = parseInt($('.chorizo-label').text());
    
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        type: "POST",
        url: "/order",
        data: JSON.stringify(Order),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function(data) {
            if (data["success"] === "true") {
                $('.price-label').text(data["price"]);
                $('.discount-label').text(data["discount"]);
            }
        },
        error: function(e) {
            alert(e.responseText);
        }
    });
}