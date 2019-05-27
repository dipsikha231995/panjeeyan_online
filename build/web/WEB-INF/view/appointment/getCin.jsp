<%-- 
    Document   : getCin
    Created on : Feb 26, 2019, 11:29:30 AM
    Author     : JIT
--%>

<a style="padding: 0px 6px;" href="index.jsp"><span class="glyphicon glyphicon-backward"></span> Go Back</a>

<div class="panel panel-info"  style="margin-top: 10px;">
    <div class="panel-heading">
        <h2 class="panel-title">Get CIN</h2>
        
    </div>
    <div class="panel-body">
        <form action="getCin" method="POST" class="form-horizontal">
            <input type="hidden" name="ACTION_CODE" id="ACTION_CODE" value="GETCIN" />
            <input type="hidden" name="SUB_SYSTEM" id="SUB_SYSTEM" value="LRC-EMOJNI"/>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">Mobile Number <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input type="Text" name="Mobile" id="Mobile" value="${Mobile}"  class="form-control"  onkeypress="prevent('Mobile','10',event)"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">DEPARTMENT_ID (DD_CHQ_NO) <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input type="Text" name="DEPARTMENT_ID" id="DEPARTMENT_ID" value="${DEPARTMENT_ID}"  class="form-control"  />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">CHALLAN AMOUNT  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input type="Text" name="AMOUNT" id="AMOUNT" value="${AMOUNT}"  class="form-control"  />
                </div>
            </div>
                <c:choose>
                <c:when test="${OFFICE_CODE==null}">
                    <div class="form-group">
                            <label class="col-sm-4 control-label" for="ApplicantName">Select Office  <span class="glyphicon glyphicon-star"></span></label>
                            <div class="col-sm-4">
                                <select class="form-control" data-validation="required" name="OFFICE_CODE" id="OFFICE_CODE">
                                    <option value="">Please select a office</option>
                                    <c:forEach items="${dbusers}" var ="dbusers" varStatus="outer" >
                                        <option value="${dbusers.districtCode}">${dbusers.dbname}</option>
                                    </c:forEach>
                                </select> 
                            </div>  
            </div>
                </c:when>    
                <c:otherwise>
                    <input type="hidden" name="OFFICE_CODE" id="OFFICE_CODE" value="${OFFICE_CODE}"/> 
                    <br />
                </c:otherwise>
            </c:choose>
            <div class="form-group last">
                    <div class="col-sm-offset-5 col-sm-6" style="margin-top: 19px;">
                        <button id="BtnSave" class="btn btn-success btn-sm" type="submit">Submit</button>
            </div>
            </div>
        </form>
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
                                //alert("asdasd");
                                var hostname = location.host+"/panjeeyanonline"; 
                                var jqxhr = $.post( "http://"+hostname+"/getChallanData",{ Mobile: $("input[name='Mobile']").val(),Request_from:"getCin" }, function() {
                                    //alert( "success" );
                                  })
                                    .done(function(data) {
                                      //alert(data);
                                      var obj =JSON.parse(data);
                                      $('#DEPARTMENT_ID').val(obj.DEPARTMENT_ID);
                                      $('#AMOUNT').val(obj.AMOUNT);
                                      $('#OFFICE_CODE').val(obj.OFFICE_CODE);
                                    })
                                    .fail(function() {
                                      alert( "Connection error" );
                                    })
                                    .always(function() {
                                      
                                    });
                            }
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

