/**
 * Created by Anuj on 1/5/2015.
 */

"use strict";


	$().ready(function(){
		
	$.ajax({
		url: 'app/reservations/all',
		type: 'GET',
		error: function(error){
			console.log(error);
		},
		success: function(data){
			if(data){
                var len = data.length;
                var txt = "";
                if(len > 0){
                    for(var i=0;i<len;i++){
                       // if(data[i].city && data[i].cStatus){
                            txt += "<tr><td>"+data[i].confNumber+"</td><td>"+data[i].firstName+"</td><td>"+data[i].reservationDate+" "+data[i].reservationTime+"</td><td>"+data[i].partySize+"</td><td>"+"<button class='btn btn-primary' title='Open Details'><span class='glyphicon glyphicon-share'></span><span class='btn-text' data-toggle='modal' data-target='#myModal'>Details</span></button></td></td>"+"<td><button class='btn btn-primary' title='Edit Reservation'><span class='glyphicon glyphicon-pencil'></span><span class='btn-text' data-toggle='modal' data-target='#myModal2'>Edit</span></button></td></tr>";
                        //}
                    }
                    if(txt != ""){
                        $("#table").append(txt);//.removeClass("hidden");
                    }
                }
            }
        }
	})
	

	
	




    $('#clicker').click(function() {
        $('.restaurantInfo').each(function() {
            if ($(this).attr('disabled')) {
                $(this).removeAttr('disabled');
            }
            else {
                $(this).attr({
                    'disabled': 'disabled'
                });
            }
        });
    });
});
	


function showForm() {
    $('.contactRow').slideToggle();
}

function closeForm() {
    $('.contactRow').slideUp();
}

function showConfirmation() {
    closeForm();
    
    var newReservation = {
    		confNumber: '456&8(O',
    		reservationDate: $('#reserveDate').val(),
    		reservationTime: $('#reserveTime').val(),
    		firstName: $('#reserveFirstName').val(),
    		lastName: $('#reserveLastName').val(),
    		phone: $('#reservePhone').val(),
    		email: $('#reserveEmail').val(),
    		partySize: new Number($('#reserveSize').val()),
    		specialNeed: $('#reserveSpecial').val(),
    };
    
    $.ajax({
    	url: 'app/reservations/create',
    	type: 'POST',
    	contentType: 'application/json',
    	data: JSON.stringify(newReservation),
    	error: function(error){
    		console.log(error);
    	},
    	success: function(data){
    		$('.confirmationWindow').slideToggle();
    	}
    });
    
    
}

function changeConfirmation() {
    closeForm();
    $('.changeConfirmation').slideToggle();
}

function clearAll() {
    $('.contactRow').slideUp();
    $('.changeConfirmation').slideUp();
    $('.confirmationWindow').slideUp();

}

