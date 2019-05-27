/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function() {
    
    
                 
   $("#appointment_date").change(function() {
       $( "#appointment_date .test_div" ).html("");
       var dob = $("#appointment_date").val();
       var arr = dob.split('-');
       var day = arr[0];
       var month = arr[1];
       var year = arr[2];
       var age = 1;
       var msg = "";
       var mydate = new Date();
       mydate.setFullYear(year,month-1,day);
       var currdate = new Date();
       if ((currdate - mydate) > 0){
         if($("#error_appointment_date").length == 0) {
           var msg ="<p id='error_appointment_date' style=color:red>Sorry, You cannot choose an older date for appointment<p>";
         }
         //$("#appointment_date").html(msg);
         $( "#appointment_date" ).after(msg);
       }
       else {
         $( "#error_appointment_date" ).remove();
       }
   });
   
   


});


