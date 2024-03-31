$(document).ready(function() {

  function ajaxCallRequest(f_method, f_url, f_data) {

    var f_contentType = 'application/hal+json;charset=UTF-8';
    $.ajax({
      url: f_url,
      type: f_method,
      contentType: f_contentType,
      dataType: 'json',
      data: f_data,
      success: function(data) {
        var jsonResult = JSON.stringify(data);

          $('#myModal').modal('hide');
      }
    });
  }





  $("#sendPlainJSon").click(function(event) {
    event.preventDefault();

    var form = $('#ajaxForm');
    var method = form.attr('method');
    var url = form.attr('linkurl');
    var jsonData = {};

    $.each($(form).serializeArray(), function() {
      jsonData[this.name] = this.value;
    });
    var data = JSON.stringify(jsonData);
    console.log(data);
    ajaxCallRequest(method, url, data);
  });


    


});

