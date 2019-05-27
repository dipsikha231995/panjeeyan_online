<%-- 
    Document   : egras_payment
    Created on : Feb 7, 2019, 10:49:02 AM
    Author     : JIT
--%>

<a style="padding: 0px 6px;" href="index.jsp"><span class="glyphicon glyphicon-backward"></span> Go Back</a>

<div class="panel panel-info"  style="margin-top: 10px;">
    <div class="panel-heading">
        <h2 class="panel-title">E-GRAS Payment</h2>
    </div>
    <div class="panel-body">
        <form class="form-horizontal" method="POST" action="egrasPayment" name="frmenquiry" enctype="multipart/form-data" >
            <div class="form-group">
                <input type="hidden" name="PAYMENT_TYPE" id="PAYMENT_TYPE" value="01" size="35" maxlength="14" />
                <input type="hidden" name="TREASURY_CODE" id="TREASURY_CODE" value="BIL" size="35" maxlength="14"  />
                <input type="hidden" name="DEPT_CODE" id="DEPT_CODE" value="LRS" size="35" maxlength="14" />
                <input type="hidden" name="REC_FIN_YEAR" id="DEPT_CODE" value="${yearRange}" size="35" maxlength="14" />
                <input id="MAJOR_HEAD" name="MAJOR_HEAD" type="hidden"  class="form-control"  value="0029"  data-date-today-highlight="true"  data-validation="required"   />
                <input type="hidden" name="HOA1" id="HOA1" value="0029-00-101-0000-000-01" size="35" maxlength="23"   />
                <input type="hidden" name="AMOUNT1" id="AMOUNT1" value="10" size="35" maxlength="10"  />
                ${appointment_failure}
                <label class="col-sm-4 control-label" for="ApplicantName">Select Office  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <select class="form-control" data-validation="required" name="OFFICE_CODE">
                        <option value="">Please select a office</option>
                        <c:forEach items="${dbusers}" var ="dbusers" varStatus="outer" >
                            <option value="${dbusers.districtCode}">${dbusers.dbname}</option>
                        </c:forEach>
                    </select> 
<!--   http://103.8.248.139/challan/views/frmgrnfordept.php                 <input type="Text" name="OFFICE_CODE" id="OFFICE_CODE" value="ELE000" size="35" maxlength="14" class="form-control"  />-->
                </div>  
            </div>
                <div class="" style="display:none;">
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">Select Period  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <select class="form-control" data-validation="required" name="PERIOD" id="PERIOD">
                        <option value="">Please select a option</option>
                        <option value="A" >Annual</option>
                        <option value="O" >One Time</option>
                        <option value="S" >Specific</option>
                    </select> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">From Date  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input id="FROM_DATE" name="FROM_DATE" type="text" placeholder="dd-mm-yyyy" class="form-control"  value="" data-date-format="dd/mm/yyyy" data-date-autoclose="true" data-date-today-highlight="true"  data-validation="required" data-validation-error-msg="Select Date of Registration"  />
                    <script type="text/javascript"> $('#FROM_DATE').datepicker();</script>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">To Date  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input id="TO_DATE" name="TO_DATE" type="text" placeholder="dd-mm-yyyy" class="form-control"  value="" data-date-format="dd/mm/yyyy" data-date-autoclose="true" data-date-today-highlight="true"  data-validation="required" data-validation-error-msg="Select Date of Registration"  />
                    <script type="text/javascript"> $('#TO_DATE').datepicker();</script>
                </div>
            </div>
            </div>
             <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">CHALLAN AMOUNT  <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input type="Text" name="CHALLAN_AMOUNT" id="CHALLAN_AMOUNT" value="" size="35" maxlength="14" class="form-control"  />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">Party Name <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input id="PARTY_NAME" name="PARTY_NAME" type="text"  class="form-control"  value=""  data-date-today-highlight="true"    data-validation="required" data-validation-error-msg="Party Name is required"/>
                    
                </div>
            </div>
                <div class="form-group" style="display:none;">
                <label class="col-sm-4 control-label" for="ApplicantName">Tax ID</label>
                <div class="col-sm-4">
                    <input id="TAX_ID" name="TAX_ID" type="text"  class="form-control"  value=""  data-date-today-highlight="true"     />
                    
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">Pan No </label>
                <div class="col-sm-4">
                    <input id="PAN_NO" name="PAN_NO" type="text"  class="form-control"  value=""  data-date-today-highlight="true"     />
                    
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">Block No/ Premises <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input id="ADDRESS1" name="ADDRESS1" type="text"  class="form-control"  value=""  data-date-today-highlight="true"    data-validation="required" data-validation-error-msg="Block No/ Premises is required"/>
                    
                </div>
            </div>
                <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">Locality/Road <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input id="ADDRESS2" name="ADDRESS2" type="text"  class="form-control"  value=""  data-date-today-highlight="true"   data-validation="required" data-validation-error-msg="Locality/Road is required" />
                    
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">Area/City <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input id="ADDRESS3" name="ADDRESS3" type="text"  class="form-control"  value=""  data-date-today-highlight="true"   data-validation="required" data-validation-error-msg="Area/City is required" />
                    
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">Pin No <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input id="PIN_NO" name="PIN_NO" type="text"  class="form-control"  value=""  data-date-today-highlight="true"    data-validation="required" data-validation-error-msg="Pin is required"/>
                    
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label" for="ApplicantName">Mobile No <span class="glyphicon glyphicon-star"></span></label>
                <div class="col-sm-4">
                    <input id="MOBILE_NO" name="MOBILE_NO" type="text"  class="form-control"  value=""  data-date-today-highlight="true"    data-validation="required" data-validation-error-msg="Nobile Number is required"/>
                    
                </div>
            </div>
                <div class="form-group last">
                        <div class="col-sm-offset-5 col-sm-6" style="margin-top: 19px;">
                            <button id="BtnSave" class="btn btn-success btn-sm" type="submit">Submit</button>
                    </div>
                </div>
                <input type="hidden" name="SUB_SYSTEM" id="SUB_SYSTEM" value="LRC-EMOJNI"  />
              <input type="hidden" name="DEPARTMENT_ID" id="DEPARTMENT_ID" value="Ele1979" size="35" maxlength="30" style="" />  
        </form>
    </div>
        
</div>
<script src="js/form-validator/jquery.form-validator.min.js" type="text/javascript"></script>
<script>$.validate({});</script>
<script>
    $(document).ready(function(){
        $('#PERIOD').val('O');
        $("#FROM_DATE").val("01/04/2018");
        $("#TO_DATE").val("31/03/2099");
        $("#FROM_DATE").attr('readonly',true);
        $("#TO_DATE").attr('readonly',true);
        $("#FROM_DATE").datepicker("remove");
        $("#TO_DATE").datepicker("remove");
        $('#PERIOD').change(function(){
            //console.log($(this).val());
            if($(this).val()=='A'){
                $("#FROM_DATE").val("01/04/2018");
                $("#TO_DATE").val("31/03/2019");
                $("#FROM_DATE").attr('readonly',true);
                $("#TO_DATE").attr('readonly',true);
                $("#FROM_DATE").datepicker("remove");
                $("#TO_DATE").datepicker("remove");
            }else if($(this).val()=='O'){
                $("#FROM_DATE").val("01/04/2018");
                $("#TO_DATE").val("31/03/2099");
                $("#FROM_DATE").attr('readonly',true);
                $("#TO_DATE").attr('readonly',true);
                $("#FROM_DATE").datepicker("remove");
                $("#TO_DATE").datepicker("remove");
            }else{
                 $("#FROM_DATE").val("");
                $("#TO_DATE").val("");
                $("#FROM_DATE").attr('readonly',false);
                $("#TO_DATE").attr('readonly',false);
                $("#FROM_DATE").datepicker("update");
                $("#TO_DATE").datepicker("update");
            }
            
            
            
        });
        $("#CHALLAN_AMOUNT").change(function(){
            
            console.log((parseFloat($("#CHALLAN_AMOUNT").val())).toFixed(2));
            $("#CHALLAN_AMOUNT").val((parseFloat($("#CHALLAN_AMOUNT").val())).toFixed(2));
            $("#AMOUNT1").val($("#CHALLAN_AMOUNT").val());
        });
        
    });
    
</script>
