<%-- 
    Document   : viewChallan
    Created on : Feb 27, 2019, 2:38:49 PM
    Author     : JIT
--%>

<div class="panel panel-info"  style="margin-top: 10px;">
    <div class="panel-heading">
        <h2 class="panel-title">View Challan</h2>
        
    </div>
    <div class="panel-body">
        <form action="#" method="POST" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">Mobile Number <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input type="Text" name="Mobile" id="Mobile" value="${Mobile}"  class="form-control"  onkeypress="prevent('Mobile','10',event)"/>
                </div>
            </div>
           
        </form>
        <div class="table-responsive">          
            <table class="table">
              <thead>
                <tr>
                  <th>GRN No.</th>
                  <th>Amount</th>
                  <th>Mobile Number</th>
                  <th>Date Of Creation</th>
                  <th>Action</th>

                </tr>
              </thead>
              <tbody class="response">

              </tbody>
            </table>
        </div>
                <div class="pdf_view" style="display: none;">
                    
                </div>
    </div>
</div>
 <script>
                    $(document).ready(function(){
                        $("input[name='Mobile']").change(function(){
                            var mobile = new RegExp('^\\d{10}$');
                            if (!mobile.test($("input[name='Mobile']").val())) {
                                alert('Opps, you entered an invalid Mobile number');
                                $("input[name='Mobile']").val('');
                                return false;
                            }else{
                                var hostname = location.host+"/panjeeyanonline"; 
                                var jqxhr = $.post( "http://"+hostname+"/getChallanData",{ Mobile: $("input[name='Mobile']").val(),Request_from:"getChallan" }, function() {
                                    //alert( "success" );
                                  })
                                    .done(function(data) {
                                      //alert(data);
                                        $('.response').html(data);
                                      
                                    })
                                    .fail(function() {
                                      alert( "Connection error" );
                                    })
                                    .always(function() {
                                      
                                    });
                            }
                        });
                    });
                    $(document).on('click','a[data-id="view"]',function(){
                        $('.pdf_view').hide();
                        var group=$(this).data('group');
                        console.log($('span[data-id="grn"][data-group="'+group+'"]').text());
                        var hostname = location.host+"/panjeeyanonline"; 
                        var jqxhr = $.post( "http://"+hostname+"/verify_challan",{ GRN : $('span[data-id="grn"][data-group="'+group+'"]').text(),amount : $('span[data-id="amount"][data-group="'+group+'"]').text(),office_code : $('span[data-id="office_code"][data-group="'+group+'"]').text()}, function() {
                            //alert( "success" );
                          })
                            .done(function(data) {
                              //alert(data);
                                $('.pdf_view').show();
                                $('.pdf_view').html('<h4 style="color:#31708f"><strong> Challan View For GRN : '+$('span[data-id="grn"][data-group="'+group+'"]').text()+'</strong></h4><iframe src="'+data+'" height="700px" width="100%"></iframe>');

                            })
                            .fail(function() {
                              alert( "Connection error" );
                            })
                            .always(function() {

                            });
                        
                    });
                    function prevent(elementName,maximun,e){
                            if ($('input[name="'+elementName+'"]').val().length > parseInt(maximun-1)
                                    && e.keyCode != 46
                                    && e.keyCode != 8) {
                                    //alert($('input[name="ApplicantsPhN"]').val().length);
                                e.preventDefault();     
                                   //$(this).val(100);
                            }
                    }
                </script>               