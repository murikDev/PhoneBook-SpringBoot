$(document).ready(function (){
    $.ajaxSetup({
        headers: {
            "X-CSRF-TOKEN": document.querySelector('meta[name="_csrf"]').getAttribute('content')
        }
    });

    $.getJSON("http://localhost:8080/api/allcontacts", function (json){

        var tbody =[];

        if (json.length === 0) {
            $("#h4").show();
        } else {
            tbody.push("<tr>")
            tbody.push("<th scope=\"col\">First</th>")
            tbody.push("<th scope=\"col\">Last</th>")
            tbody.push("</tr>")

            $("#thead").append($(tbody.join('')));

            tbody =[];
        }

        var role = document.getElementById("tbody").getAttribute("role");
        if (role==="true") {
            for (let i = 0; i < json.length; i++) {
                tbody.push("<tr>");
                tbody.push("<td><a class=\"link-body-emphasis text-decoration-none\" href='/contact/"+ json[i].id +"'>"+ json[i].name +"</a></td>")
                tbody.push("<td>"+ json[i].surname +"</td>");
                tbody.push("<td><a class=\"link-body-emphasis text-decoration-none\" href='/contact/"+ json[i].id +"/edit'>Edit</a></td>");
                tbody.push('<td><button class=\'delete btn btn-outline-danger\'  id=' + json[i].id + '>Delete</button></td>');
                tbody.push("</tr>");
            }
            $("#tbody").append($(tbody.join('')));
        } else {
            for (let i = 0; i < json.length; i++) {
                tbody.push("<tr>");
                tbody.push("<td><a class=\"link-body-emphasis text-decoration-none\" href='/contact/"+ json[i].id +"'>"+ json[i].name +"</a></td>")
                tbody.push("<td>"+ json[i].surname +"</td>");
                tbody.push("<td><a class=\"link-body-emphasis text-decoration-none\" href='/contact/"+ json[i].id +"/edit'>Edit</a></td>");
                tbody.push("</tr>");
            }
            $("#tbody").append($(tbody.join('')));
        }

        console.log(tbody);


    });


   $(document).delegate('.delete', 'click', function (){
       if (confirm('Contacty udalit etmek isleyanizmi?')) {
           var id = $(this).attr('id');
           var parent = $(this).parent().parent();
           console.log(id);

           $.ajax({
               type: "DELETE",
               url: "http://localhost:8080/api/" + id,
               cache: false,
               success: function () {
                   parent.fadeOut('slow', function (){
                      $(this).remove()
                   });
                   location.reload(true)
               }
           })
       }
   });

   $("#nameOrSurname").on("keyup", function (){
      var value = $(this).val().toLowerCase();
      $("#tbody tr").filter(function (){
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
          console.log($(this).toggle($(this).text().toLowerCase().indexOf(value) > -1))
      });
   });

});
