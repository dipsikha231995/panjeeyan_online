<%-- 
    Document   : getGrn
    Created on : Mar 12, 2019, 11:06:49 AM
    Author     : JIT
--%>
<a style="padding: 0px 6px;" href="index.jsp"><span class="glyphicon glyphicon-backward"></span> Go Back</a>

<div class="panel panel-info"  style="margin-top: 10px;">
    <div class="panel-heading">
        <h2 class="panel-title">Get CIN</h2>
        
    </div>
    <div class="panel-body">
        <form action="#" method="POST" class="form-horizontal">
            <input type="hidden" name="ACTION_CODE" id="ACTION_CODE" value="GETCIN" />
            <input type="hidden" name="SUB_SYSTEM" id="SUB_SYSTEM" value="LRC-EMOJNI"/>
            
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
        $("#BtnSave").click(function(){
            console.log($("select[name='OFFICE_CODE'] option:selected").val());
            var hostname = location.host+"/panjeeyanonline"; 
//            var jqxhr = $.post( "http://"+hostname+"/getGrn",{ DEPARTMENT_ID: $("input[name='DEPARTMENT_ID']").val(),AMOUNT:$("input[name='AMOUNT']").val(),OFFICE_CODE:$("select[name='AMOUNT']:selected").val()  }, function() {
//                //alert( "success" );
//              })
//                .done(function(data) {
//                  //alert(data);
//                  var obj =JSON.parse(data);
//                  $('#DEPARTMENT_ID').val(obj.DEPARTMENT_ID);
//                  $('#AMOUNT').val(obj.AMOUNT);
//                  $('#OFFICE_CODE').val(obj.OFFICE_CODE);
//                })
//                .fail(function() {
//                  alert( "Connection error" );
//                })
//                .always(function() {
//
//                });
            return false;
        });
    });
</script>

