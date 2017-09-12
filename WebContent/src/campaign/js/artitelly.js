// JavaScript Document
function checkWordCount(){
    s = document.getElementById("answer").value;
    s = s.replace(/(^\s*)|(\s*$)/gi,"");
    s = s.replace(/[ ]{2,}/gi," ");
    s = s.replace(/\n /,"\n");
    if (s.split(' ').length <= 100) {
       //BootstrapDialog.show("not enough words...");
    	 $('#characterLeft').text('"not enough words...');
       return false;
    }
    /*else {
    	//document.getElementById("reply").disabled = true;
    	return true;
    }*/
}


$('#answer').keyup(function () {
    var max = 4000;
    var len = $(this).val().length;
    if (len >= max) {
        $('#characterLeft').text('The maximum is 4000 character');
    } else {
        $('#characterLeft').text(len + ' characters');
    }
});

$('form').validate({
	rules: {
		textarea: {
			minlength: 1000,
			maxlength: 4000,
			required: true
		},
		phone: {
			minlength: 10,
			maxlength: 13,
			required: true
		},
		title: {
			required: true
		}
	},
	highlight: function(element) {
		$(element).closest('.form-group div').addClass('has-error');
	},
	unhighlight: function(element) {
		$(element).closest('.form-group div').removeClass('has-error');
	},
	errorElement: 'span',
	errorClass: 'help-block',
	errorPlacement: function(error, element) {
		if(element.parent('.input-group').length) {
			error.insertAfter(element.parent());
		} else {
			error.insertAfter(element);
		}
	}
});
